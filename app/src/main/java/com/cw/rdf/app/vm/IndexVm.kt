package com.cw.rdf.app.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cw.rdf.app.model.Article
import com.cw.rdf.app.model.Banner
import com.cw.rdf.app.repository.DataRepository
import com.cw.rdf.core.base.BaseViewModel

/**
 * @Description:首页 ViewModel
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:10 AM
 *
 */
class IndexVm(private val dataRepository: DataRepository):BaseViewModel() {
    val banners = MutableLiveData<List<Banner>>()
    val articleList = MutableLiveData<List<Article>>()

    /**
     *
     * @description 获取首页 banner
     * @return
     *
     */
    fun getBanners() = launch {
        banners.value = dataRepository.getBanners()
    }

    fun getArticle(pageIndex:Int) = launch {
        articleList.value = dataRepository.getIndexArticleList(pageIndex)?.datas
    }
}