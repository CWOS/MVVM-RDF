package com.cw.rdf.app

import android.os.Bundle
import com.cw.rdf.core.base.BaseActivity
/**
 *
 * @Description: sample code 首页
 * @Author: wanglejun
 * @CreateDate：2020/7/31 12:24 AM
 *
 */
class MainActivity : BaseActivity() {

    override fun isLightStatusBar() = true
//
//    override fun isFullscreen() = true
//
//    override fun isTransparentStatusBar() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}