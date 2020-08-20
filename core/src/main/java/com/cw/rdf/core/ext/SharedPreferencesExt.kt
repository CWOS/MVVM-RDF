package com.cw.rdf.core.ext

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

/**
 * @Description: SharedPreferences 相关扩展方法
 * @Author: wanglejun
 * @CreateDate： 2020/8/21 7:57 AM
 *
 */
/**
 * 获取SharedPreferences
 * @receiver Context
 * @param name String
 * @return SharedPreferences
 */
fun Context.getSharedPreference(name: String? = null): SharedPreferences {
    if(name.isNullOrEmpty()){
        return getDefaultSharedPreference()
    }
    return getSharedPreferences(name, Context.MODE_PRIVATE)
}

/**
 * 获取默认SharedPreferences
 * @receiver Context
 * @return SharedPreferences
 */
fun Context.getDefaultSharedPreference(): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(this)
}

/**
 * 根据key获取值
 * @receiver SharedPreferences
 * @param key String
 * @param default T
 * @return T
 */
inline fun <reified T> SharedPreferences.get(key: String, default: T? = null): T {
    return when (T::class) {
        Int::class -> getInt(key, (default as? Int) ?: 0) as T
        Float::class -> getFloat(key, (default as? Float) ?: 0f) as T
        Long::class -> getLong(key, (default as? Long) ?: 0) as T
        Boolean::class -> getBoolean(key, (default as? Boolean) ?: false) as T
        String::class -> getString(key, (default as? String) ?: "") as T
        else -> throw RuntimeException("class type not support")
    }
}


/**
 * 保存对应的值
 * @receiver SharedPreferences
 * @param key String
 * @param value T
 */
inline fun <reified T> SharedPreferences.put(key: String, value: T) {
    edit {
        when (T::class) {
            Int::class -> putInt(key, value as Int)
            Float::class -> putFloat(key, value as Float)
            Long::class -> putLong(key, value as Long)
            Boolean::class -> putBoolean(key, value as Boolean)
            String::class -> putString(key, value as String)
        }
    }
}

/**
 * 删除对应key的值
 * @receiver SharedPreferences
 * @param key String
 */
fun SharedPreferences.remove(key: String) {
    edit {
        remove(key)
    }
}

/**
 * 清空所有数据
 * @receiver SharedPreferences
 */
fun SharedPreferences.clear(){
    edit {
        clear()
    }
}

inline fun <reified T> Context.getSP(key: String, default: T?, name : String? = null) : T{
    return getSharedPreference(name).get(key, default)
}

inline fun <reified T> Context.putSP(key: String, value: T, name : String? = null){
    getSharedPreference(name).put(key, value)
}

fun Context.removeSP(key: String, name : String? = null){
    getSharedPreference(name).remove(key)
}

fun Context.clearSP(name : String? = null){
    getSharedPreference(name).clear()
}
