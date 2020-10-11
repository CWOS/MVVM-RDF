package com.cw.rdf.app.vm

import androidx.lifecycle.MutableLiveData
import com.cw.rdf.app.model.Article
import com.cw.rdf.app.repository.DataRepository
import com.cw.rdf.core.base.BaseViewModel

/**
 * @Description:文章 ViewModel
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:10 AM
 *
 */
class ArticleVm(private val dataRepository: DataRepository):BaseViewModel() {
    val articleList = MutableLiveData<List<Article>>()

    init {
        getMaidanArticleList(0)
    }
    fun getMaidanArticleList(pageIndex:Int) = launch {
        articleList.value = dataRepository.getMaidanArticleList(pageIndex)?.datas
    }
}