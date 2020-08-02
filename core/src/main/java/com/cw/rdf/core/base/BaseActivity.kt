package com.cw.rdf.core.base

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.cw.rdf.core.helper.RequestPermissionHelper
import com.cw.rdf.core.helper.RequestPermissionHelper.requestPermissions
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.helper.PermissionHelper

/**
 * @Description: 最基础的 Activity Base
 * @Author: wanglejun
 * @CreateDate： 2020/7/30 11:59 PM
 *
 */
open class BaseActivity: AppCompatActivity(), EasyPermissions.PermissionCallbacks,
EasyPermissions.RationaleCallbacks{
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

    /**
     *
     * @description  请求权限
     * @param
     * @return
     *
     */
    fun requestPermissions(
        permissions: Array<out String>,
        onDenied: (() -> Unit)? = null,
        onGranted: () -> Unit
    ) {
        RequestPermissionHelper.requestPermissions(this, permissions, onDenied, onGranted)
    }

    /**
     * 
     * @description 原生权限申请结果回调
     * @param requestCode
     * @param permissions 权限列表
     * @param grantResults 申请结果数组
     * @return 
     * 
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //权限申请结果委托给 EasyPermission 处理
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        RequestPermissionHelper.onActivityResult(this,requestCode,requestCode,data)
    }

    /**
     * 
     * @description PermissionCallbacks 权限申请失败回调
     * @param requestCode
     * @param perms 权限列表
     * @return 
     * 
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        RequestPermissionHelper.onPermissionsDenied(this,requestCode,perms)
    }

    /**
     * 
     * @description PermissionCallbacks 权限申请成功回调
     * @param requestCode 
     * @param perms 权限列表
     * @return 
     * 
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        RequestPermissionHelper.onGranted(requestCode, perms)
    }

    /**
     * 
     * @description RationaleCallbacks 权限申请弹出框点击拒绝回调
     * @param requestCode
     * @return 
     * 
     */
    override fun onRationaleDenied(requestCode: Int) {
        RequestPermissionHelper.onRationaleDenied(requestCode)
    }

    /**
     * 
     * @description RationaleCallbacks 权限申请弹出框点击确定回调
     * @param requestCode
     * @return 
     * 
     */
    override fun onRationaleAccepted(requestCode: Int) {
        TODO("Not yet implemented")
    }


}