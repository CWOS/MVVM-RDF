package com.cw.rdf.app.http

import com.cw.rdf.app.model.*
import retrofit2.http.*

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/9/11 7:30 AM
 *
 */
interface ApiService {


    /**
     *
     * @description 登录
     * @param username 用户名
     * @param password 密码
     * @return
     *
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@Field("username") username:String,@Field("password") password:String):ApiResponse<UserInfo>

    /**
     *
     * @description 注册
     * @param username 用户名
     * @param password 密码
     * @param repassword 确定密码
     * @return
     *
     */
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(@Field("username") username:String,@Field("password") password:String,
                         @Field("repassword") repassword:String):ApiResponse<UserInfo>


    /**
     *
     * @description 获取首页 banner
     * @return
     *
     */
    @GET("/banner/json")
    suspend fun getBanner():ApiResponse<List<Banner>>


    /**
     *
     * @description 获取首页文章列表
     * @param pageIndex 页码
     * @return
     *
     */
    @GET("/article/list/{pageIndex}/json")
    suspend fun getIndexArticleList(@Path("pageIndex") pageIndex:Int):ApiResponse<PageData<List<Article>>>


    /**
     *
     * @description 获取广场文章列表
     * @param pageIndex 页码
     * @return
     *
     */
    @GET("/user_article/list/{pageIndex}/json")
    suspend fun getMaidanArticleList(@Path("pageIndex") pageIndex: Int):ApiResponse<PageData<List<Article>>>


    /**
     *
     * @description 获取项目分类
     * @return 项目分类列表
     *
     */
    @GET("/project/tree/json")
    suspend fun getProjectTree():ApiResponse<List<ProjectTree>>


    /**
     *
     * @description 获取项目分类下项目列表
     * @param
     * @return
     *
     */
    @GET("/project/list/{pageIndex}/json")
    suspend fun getProjects(@Path("pageIndex") pageIndex:Int,@Query("cid") cid:Int):ApiResponse<PageData<List<Project>>>

    /**
     *
     * @description 获取微信公众号列表
     * @return
     *
     */
    @GET("/wxarticle/chapters/json ")
    suspend fun getWXArticleChapters():ApiResponse<List<ArticleChapter>>
}