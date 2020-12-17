package com.cw.rdf.app.vm

import androidx.lifecycle.MutableLiveData
import com.cw.rdf.app.repository.DataRepository
import com.cw.rdf.core.base.BaseViewModel

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/10/17 11:00 PM
 *
 */
class LoginVm(private val dataRepository: DataRepository) : BaseViewModel() {

    var userName = MutableLiveData<String>("")
    var passwd = MutableLiveData<String>("")

    /**
     * @description 登录
     */
    fun login() = launch {
        dataRepository.login(userName.value?:"",passwd.value?:"")
    }

    /**
     * @description 退出登录
     */
    fun logout() = launch {
        dataRepository.logout()
    }
}