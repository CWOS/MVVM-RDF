package com.cw.rdf.core.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

/**
 * @Description: 最基础的 Activity Base
 * @Author: wanglejun
 * @CreateDate： 2020/7/30 11:59 PM
 *
 */
open class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStatusBar()
    }

    /**
     *
     * @description 初始化状态栏
     * @param
     * @return void
     *
     */
    private fun initStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && isTransparentStatusBar()) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.TRANSPARENT
        }
        // 如果亮色，设置状态栏文字为黑色
        var systemUiVisibility =
            if (isLightStatusBar() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_VISIBLE
            }

        if (isFullscreen()) {
            systemUiVisibility = systemUiVisibility or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        window.decorView.systemUiVisibility = systemUiVisibility
    }

    /**
     *
     * @description 设置状态栏是否是亮色
     * @param
     * @return boolean
     *
     */
    open fun isLightStatusBar() = true

    /**
     *
     * @description 设置状态栏是否透明
     * @param
     * @return boolean
     *
     */
    open fun isTransparentStatusBar() = true

    /**
     *
     * @description 设置是否全屏
     * @param
     * @return boolean
     *
     */
    open fun isFullscreen() = true
}