package com.cw.rdf.app.vm

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.cw.rdf.app.model.Article
import com.cw.rdf.app.repository.DataRepository
import com.cw.rdf.core.base.BaseViewModel

/**
 * @Description:我的收藏 ViewModel
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:10 AM
 *
 */
class MyCollectVm(private val dataRepository: DataRepository) :BaseViewModel() {
    var collectList = MutableLiveData<List<Article>>()

    /**
     *
     * @description 获取收藏列表
     * @param pageIndex 页码
     * @return
     *
     */
    fun getCollectList(pageIndex:Int) = launch {
        collectList.value = dataRepository.getCollectList(0)?.datas
    }
}