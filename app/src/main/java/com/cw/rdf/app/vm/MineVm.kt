package com.cw.rdf.app.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cw.rdf.app.EVENT_LOGIN_SUCCESS
import com.cw.rdf.app.repository.DataRepository
import com.cw.rdf.core.base.BaseViewModel
import com.cw.rdf.core.http.cookie.CookieStore
import okhttp3.HttpUrl

/**
 * @Description:我的 ViewModel
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:10 AM
 *
 */
class MineVm(private val dataRepository: DataRepository,private val cookieStore: CookieStore):BaseViewModel() {
    private val TAG = "MineVm"
    var userName = MutableLiveData<String>("")
    var passwd = MutableLiveData<String>("")

    init {
        userName.value = getUserNameForCookie()
    }
    /**
     * @description 登录
     */
    fun login() = launch {
        val result = dataRepository.login(userName.value?:"",passwd.value?:"")
        result?.let {
            if (it.errorCode == 0){
                getUserNameForCookie()?.let { it1-> postEvent(EVENT_LOGIN_SUCCESS)}
            }else{
                it.errorMsg?.let { it2 -> postHintText(it2) }
            }
        }

    }

    /**
     * @description 退出登录
     */
    fun logout() = launch {
        dataRepository.logout()
        userName.value = ""
        passwd.value = ""
    }

    /**
     *
     * @description 获取收藏列表
     * @param pageIndex 页码
     * @return
     *
     */
    fun getCollectList(pageIndex:Int) = launch {
        dataRepository.getCollectList(0)
    }

    /**
     *
     * @description 获取cookie中的用户信息
     * @return
     *
     */
    private fun getUserNameForCookie():String?{
        val httpUrl = HttpUrl.Builder().scheme("https").host("www.wanandroid.com").encodedPath("/user/login").build()
        val cookies = cookieStore.getCookies(httpUrl)
        for (cookie in cookies){
            if(cookie.name().equals("loginUserName")) return cookie.value()
        }
        return null
    }
}