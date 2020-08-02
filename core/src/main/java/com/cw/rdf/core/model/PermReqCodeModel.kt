package com.cw.rdf.core.model

/**
 * @Description: 权限申请 RequestCode Model
 * @Author: wanglejun
 * @CreateDate： 2020/8/2 5:19 PM
 *
 */
class PermReqCodeModel(val permissions: MutableList<String>, val onDenied:()->Unit, val onGranted:()->Unit) {
    companion object{
        private var permissionsRequestCode = 20212
    }

    val requestCode = permissionsRequestCode ++
}