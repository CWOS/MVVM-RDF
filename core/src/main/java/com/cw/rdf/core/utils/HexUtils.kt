package com.cw.rdf.core.utils

import java.util.*

/**
 * @Description: 十六进制二进制转换
 * @Author: wanglejun
 * @CreateDate： 2020/12/9 4:47 PM
 *
 */


/**
 * @description 二进制数组转十六进制字符串
 */
fun byteArrayToHexString(bytes: ByteArray): String? {
    val sb = StringBuilder(bytes.size * 2)
    for (element in bytes) {
        val v: Int = element.toInt() and 0xff
        if (v < 16) {
            sb.append('0')
        }
        sb.append(Integer.toHexString(v))
    }
    return sb.toString().toUpperCase(Locale.US)
}

/**
 *
 * @description 十六进制字符串转二进制数组
 * @param hexString
 * @return
 */
fun hexStringToByteArray(hexString: String): ByteArray? {
    val len = hexString.length
    val data = ByteArray(len / 2)
    var i = 0
    while (i < len) {
        data[i / 2] = ((Character.digit(
            hexString[i],
            16
        ) shl 4) + Character.digit(hexString[i + 1], 16)).toByte()
        i += 2
    }
    return data
}
