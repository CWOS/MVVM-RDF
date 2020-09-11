package com.cw.rdf.app.http

import com.cw.rdf.app.model.ApiResponse
import com.cw.rdf.app.model.ArticleChapter
import retrofit2.http.GET

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/9/11 7:30 AM
 *
 */
interface ApiService {
    @GET("/wxarticle/chapters/json ")
    suspend fun getWXArticleChapters():ApiResponse<List<ArticleChapter>>
}