package com.cw.rdf.core.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.cw.rdf.core.R
import com.cw.rdf.core.model.PermReqCodeModel
import org.jetbrains.anko.toast
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

/**
 * @Description: 权限申请 Helper 类
 * @Author: wanglejun
 * @CreateDate： 2020/8/1 11:45 PM
 *
 */
object RequestPermissionHelper {
    private val permReqCodes = ArrayList<PermReqCodeModel>()

    /**
     *
     * @description 添加一个 requestcode 到 permReqCodes。本地维护一个 requestcode 列表用于同时申请多个权限场景下的回调处理
     * @param permReqCode requestcode
     * @return
     *
     */
    private fun addPermReqCodeModel(permReqCode: PermReqCodeModel){
        permReqCodes.add(permReqCode)
    }

    /**
     *
     * @description 权限拒绝的处理
     * @param requestCode
     * @param perms 权限列表
     * @return void
     *
     */
    private fun onDenied(requestCode: Int, perms: List<String>) {
        val requestModel = getRequestModel(requestCode)
        requestModel?.let {
            it.permissions.removeAll(perms)
            if(it.permissions.isEmpty()){
                it.onDenied.invoke()
                permReqCodes.remove(it)
            }
        }

    }

    /**
     *
     * @description 权限拒绝的处理
     * @param requestCode
     * @return void
     *
     */
    private fun onDenied(requestCode: Int) {
        val requestModel = getRequestModel(requestCode)
        requestModel?.let {
            it.onDenied.invoke()
            permReqCodes.remove(it)
        }

    }

    /**
     *
     * @description 权限申请失败
     * @param activity
     * @param requestCode
     * @param perms 权限列表
     * @return void
     *
     */
    fun onPermissionsDenied(activity: Activity, requestCode: Int, perms: MutableList<String>) {

        getRequestModel(requestCode)?.let {
            //用户选择了不再提醒则引导用户去设置界面开启权限
            if (EasyPermissions.somePermissionPermanentlyDenied(activity, perms)) {
                AppSettingsDialog.Builder(activity)
                    .setRequestCode(requestCode)
                    .build().show()
            } else {
                onDenied(requestCode, perms)
            }
        }
    }

    /**
     *
     * @description  权限申请失败
     * @param fragment
     * @param requestCode
     * @param perms 权限列表
     * @return void
     *
     */
    fun onPermissionsDenied(fragment: Fragment, requestCode: Int, perms: MutableList<String>) {
        getRequestModel(requestCode)?.let {
            //用户选择了不再提醒则引导用户去设置界面开启权限
            if (EasyPermissions.somePermissionPermanentlyDenied(fragment, perms)) {
                AppSettingsDialog.Builder(fragment)
                    .setRequestCode(requestCode)
                    .build().show()
            } else {
                onDenied(requestCode, perms)
            }
        }

    }

    /**
     *
     * @description 权限申请成功
     * @param requestCode
     * @param perms 权限列表
     * @return void
     *
     */
    fun onGranted(requestCode: Int, perms: List<String>) {
        val requestModel = getRequestModel(requestCode)
        requestModel?.let {
            it.permissions.removeAll(perms)
            if(it.permissions.isEmpty()){
                it.onGranted.invoke()
                permReqCodes.remove(it)
            }
        }
    }



    /**
     *
     * @description 用户选择了拒绝不再提醒后引导去设置界面开启权限后返回界面的处理
     * @param context
     * @param requestCode
     * @param data
     * @return void
     *
     */
    fun onActivityResult(context: Context, requestCode: Int, resultCode: Int, data: Intent?) {
        //判断设置返回后权限是否开启
        getRequestModel(requestCode)?.let{
            if (EasyPermissions.hasPermissions(context, *it.permissions.toTypedArray())) {
                onGranted(requestCode, it.permissions)
            } else {
                onDenied(requestCode)
            }
        }
    }

    /**
     *
     * @description 根据 requestCode 获取对应的 PermReqCodeModel
     * @param
     * @return
     *
     */
    private fun getRequestModel(requestCode: Int) =
        permReqCodes.find { it.requestCode == requestCode }


    /**
     *
     * @description 申请权限
     * @param
     * @return
     *
     */
    fun requestPermissions(
        activity: Activity,
        permissions: Array<out String>,
        onDenied: (() -> Unit)?,
        onGranted: () -> Unit
    ) {
        //判断是否有权限
        if (EasyPermissions.hasPermissions(activity, *permissions)) {
            onGranted()
        } else {
            val requestModel = PermReqCodeModel(
                mutableListOf(*permissions),
                onDenied ?: { defaultDeniedHandle(activity) },
                onGranted
            )
            addPermReqCodeModel(requestModel)
            EasyPermissions.requestPermissions(activity, activity.getString(R.string.permission_request_hint), requestModel.requestCode, *permissions)
        }
    }

    /**
     *
     * @description 申请权限
     * @param
     * @return
     *
     */
    fun requestPermissions(
        fragment: Fragment,
        permissions: Array<out String>,
        onDenied: (() -> Unit)?,
        onGranted: () -> Unit
    ) {
        fragment.context?.let {
            if (EasyPermissions.hasPermissions(it, *permissions)) {
                onGranted()
            } else {
                val requestModel = PermReqCodeModel(
                    mutableListOf(*permissions),
                    onDenied ?: { defaultDeniedHandle(it) },
                    onGranted
                )
                addPermReqCodeModel(requestModel)
                EasyPermissions.requestPermissions(fragment, it.getString(R.string.permission_request_hint), requestModel.requestCode, *permissions)
            }
        }

    }


    /**
     *
     * @description 默认的权限申请被拒绝的处理
     * @param context
     * @return void
     *
     */
    private fun defaultDeniedHandle(context: Context?){
        context?.toast("无相应权限可能导致某些功能无法正常使用！")
    }


    /**
     *
     * @description 权限申请提示弹出框点击拒绝
     * @param requestCode
     * @return void
     *
     */
    fun onRationaleDenied(requestCode: Int) {
        onDenied(requestCode)
    }
}