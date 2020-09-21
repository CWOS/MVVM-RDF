package com.cw.rdf.app.vm

import androidx.lifecycle.MutableLiveData
import com.cw.rdf.app.model.ArticleChapter
import com.cw.rdf.app.repository.DataRepository
import com.cw.rdf.core.base.BaseViewModel

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/8/19 7:18 AM
 *
 */
class MainVm(private val dataRepository: DataRepository):BaseViewModel() {
    val count = MutableLiveData<String>("999")
    val articleChapters = MutableLiveData<List<ArticleChapter>>()
    fun getWXArticleChapters() = launch {
        articleChapters.value = dataRepository.getWXArticleChapters()
    }
}