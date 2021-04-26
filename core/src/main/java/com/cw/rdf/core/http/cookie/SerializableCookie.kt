package com.cw.rdf.core.http.cookie

import android.util.Log
import okhttp3.Cookie
import java.io.*


/**
 * @Description:序列化 cookie 对象
 * @Author: wanglejun
 * @CreateDate： 2020/10/28 7:10 PM
 *
 */
class SerializableCookie : Serializable {

    companion object {
        private const val serialVersionUID = 6374381828722046732L
        private const val TAG = "SerializableCookie"
        private const val NON_VALID_EXPIRES_AT= -1L
    }

    @Transient
    private var cookie:Cookie?=null

    /** 将cookie写到对象流中  */
    @Throws(IOException::class)
    private fun writeObject(out: ObjectOutputStream) {
        out.writeObject(cookie?.name())
        out.writeObject(cookie?.value())
        if(cookie?.persistent()!!){
            out.writeLong(cookie?.expiresAt()!!)
        }else{
            out.writeLong(NON_VALID_EXPIRES_AT)
        }
        out.writeObject(cookie?.domain())
        out.writeObject(cookie?.path())
        cookie?.secure()?.let { out.writeBoolean(it) }
        cookie?.httpOnly()?.let { out.writeBoolean(it) }
        cookie?.hostOnly()?.let { out.writeBoolean(it) }
    }

    /** 从对象流中构建cookie对象  */
    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(`in`: ObjectInputStream) {
        val name = `in`.readObject() as String
        val value = `in`.readObject() as String
        val expiresAt = `in`.readLong()
        val domain = `in`.readObject() as String
        val path = `in`.readObject() as String
        val secure = `in`.readBoolean()
        val httpOnly = `in`.readBoolean()
        val hostOnly = `in`.readBoolean()
        var builder = Cookie.Builder()
            .name(name)
            .value(value)
            .domain(domain)
            .path(path)

        if(expiresAt != NON_VALID_EXPIRES_AT){
            builder.expiresAt(expiresAt)
        }
        builder = if (hostOnly) builder.hostOnlyDomain(domain) else builder
        builder = if (secure) builder.secure() else builder
        builder = if (httpOnly) builder.httpOnly() else builder
        cookie = builder.build()
    }


    /**
     *
     * @description 序列化 Cookie 对象到 String
     * @param cookie 需要序列化的 Cookie
     * @return 序列化 cookie 得到的 String
     *
     */
    fun encode(cookie: Cookie?): String? {
        this.cookie = cookie!!
        val byteArrayOutputStream = ByteArrayOutputStream()
        var objectOutputStream: ObjectOutputStream? = null
        try {
            objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
            objectOutputStream.writeObject(this)
        } catch (e: IOException) {
            Log.d(Companion.TAG, "IOException in encodeCookie", e)
            return null
        } finally {
            if (objectOutputStream != null) {
                try {
                    // Closing a ByteArrayOutputStream has no effect, it can be used later (and is used in the return statement)
                    objectOutputStream.close()
                } catch (e: IOException) {
                    Log.d(Companion.TAG, "Stream not closed in encodeCookie", e)
                }
            }
        }
        return com.cw.rdf.core.utils.byteArrayToHexString(byteArrayOutputStream.toByteArray())
    }


    /**
     *
     * @description 将 cookie 字符串反序列化成 Cookie 对象
     * @param encodedCookie 需要反序列化的 cookie 字符串
     * @return 序列化后得到的 Cookie 对象
     *
     */
    fun decode(encodedCookie: String): Cookie? {
        val bytes =
            com.cw.rdf.core.utils.hexStringToByteArray(encodedCookie)
        val byteArrayInputStream = ByteArrayInputStream(
            bytes
        )
        var cookie: Cookie? = null
        var objectInputStream: ObjectInputStream? = null
        try {
            objectInputStream = ObjectInputStream(byteArrayInputStream)
            cookie = (objectInputStream.readObject() as SerializableCookie).cookie
        } catch (e: IOException) {
            Log.d(TAG, "IOException in decodeCookie", e)
        } catch (e: ClassNotFoundException) {
            Log.d(TAG, "ClassNotFoundException in decodeCookie", e)
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close()
                } catch (e: IOException) {
                    Log.d(TAG, "Stream not closed in decodeCookie", e)
                }
            }
        }
        return cookie
    }
}