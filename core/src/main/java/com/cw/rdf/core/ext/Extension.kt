package com.cw.rdf.core.ext

import android.annotation.SuppressLint
import android.app.Application
import java.lang.reflect.InvocationTargetException

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/8/17 6:11 AM
 *
 */

/**
 *
 * @description 反射获取 ActivityThread Application
 * @return Application
 *
 */
fun getApplicationByReflect(): Application? {
    try {
        @SuppressLint("PrivateApi") val activityThread =
            Class.forName("android.app.ActivityThread")
        val thread = activityThread.getMethod("currentActivityThread").invoke(null)
        val app = activityThread.getMethod("getApplication").invoke(thread)
            ?: throw NullPointerException("you should init first")
        return app as Application
    } catch (e: NoSuchMethodException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    } catch (e: InvocationTargetException) {
        e.printStackTrace()
    } catch (e: ClassNotFoundException) {
        e.printStackTrace()
    }
    throw NullPointerException("you should init first")
}