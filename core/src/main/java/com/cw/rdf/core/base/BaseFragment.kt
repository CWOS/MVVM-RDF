package com.cw.rdf.core.base

import android.content.Intent
import androidx.fragment.app.Fragment
import com.cw.rdf.core.helper.RequestPermissionHelper
import pub.devrel.easypermissions.EasyPermissions

/**
 * @Description: 最基的 Fragment
 * @Author: wanglejun
 * @CreateDate： 2020/8/3 12:15 AM
 *
 */
open class BaseFragment:Fragment(), EasyPermissions.PermissionCallbacks,
EasyPermissions.RationaleCallbacks {
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
        context?.let {
            RequestPermissionHelper.onActivityResult(it,requestCode,requestCode,data)
        }
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
    }
}