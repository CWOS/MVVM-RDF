package com.cw.rdf.app.repository

import com.cw.rdf.app.http.ApiService
import com.cw.rdf.app.model.*

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/9/11 7:48 AM
 *
 */
class DataRepository(private val apiService: ApiService) {


    /**
     *
     * @description 登录
     * @param userName 用户名
     * @param passwd 密码
     * @return
     *
     */
    suspend fun login(userName: String, passwd: String): UserInfo? {
        return apiService.login(userName, passwd).data
    }

    /**
     *
     * @description 注册账号
     * @param userName 用户名
     * @param passwd 密码
     * @param repasswd 确认密码
     * @return
     *
     */
    suspend fun register(userName: String,passwd: String,repasswd:String):UserInfo?{
        return apiService.register(userName,passwd,repasswd).data
    }

    /**
     *
     * @description 退出登录
     * @return
     *
     */
    suspend fun logout():Boolean = apiService.logout().errorCode == 0


    /**
     *
     * @description 获取公众号文章列表
     * @return
     *
     */
    suspend fun getWXArticleChapters(): List<ArticleChapter>? {
        return apiService.getWXArticleChapters().data
    }

    /**
     *
     * @description 获取首页 Banners
     * @return
     *
     */
    suspend fun getBanners(): List<Banner>? {
        return apiService.getBanner().data
    }

    /**
     *
     * @description 获取首页文章列表
     * @param pageIndex 当前页码
     * @return
     *
     */
    suspend fun getIndexArticleList(pageIndex: Int): PageData<List<Article>>? {
        return apiService.getIndexArticleList(pageIndex).data
    }

    /**
     *
     * @description 获取广场文章列表
     * @param pageIndex 当前页码
     * @return
     *
     */
    suspend fun getMaidanArticleList(pageIndex: Int): PageData<List<Article>>? {
        return apiService.getMaidanArticleList(pageIndex).data
    }

    /**
     *
     * @description 获取项目分类
     * @return 项目分类列表
     *
     */
    suspend fun getProjectTree(): List<ProjectTree>? {
        return apiService.getProjectTree().data
    }

    /**
     *
     * @description 获取项目分类下项目列表
     * @param
     * @return
     *
     */
    suspend fun getProjects(pageIndex: Int, cid: Int): PageData<List<Project>>? {
        return apiService.getProjects(pageIndex, cid).data
    }

    /**
     *
     * @description 获取收藏文章列表
     * @param pageIndex 页码
     * @return 已收藏文章列表
     *
     */
    suspend fun getCollectList(pageIndex: Int):PageData<List<Article>>?{
        return apiService.getCollectList(pageIndex).data
    }
}