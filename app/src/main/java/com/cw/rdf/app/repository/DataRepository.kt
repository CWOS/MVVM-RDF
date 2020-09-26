package com.cw.rdf.app.repository

import com.cw.rdf.app.http.ApiService
import com.cw.rdf.app.model.ArticleChapter
import com.cw.rdf.app.model.Banner

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/9/11 7:48 AM
 *
 */
class DataRepository(private val apiService: ApiService) {
    suspend fun getWXArticleChapters(): List<ArticleChapter>? {
        return apiService.getWXArticleChapters().data
    }

    suspend fun getBanners():List<Banner>?{
        return apiService.getBanner().data
    }
}