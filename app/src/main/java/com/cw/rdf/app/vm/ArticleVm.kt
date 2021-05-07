package com.cw.rdf.app.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cw.rdf.app.model.Article
import com.cw.rdf.app.repository.DataRepository
import com.cw.rdf.core.base.BaseViewModel
import com.cw.rdf.recycle.paging.PageKeyedDataSourceFactory
import kotlinx.coroutines.CoroutineScope


/**
 * @Description:文章 ViewModel
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:10 AM
 *
 */
class ArticleVm(private val dataRepository: DataRepository):BaseViewModel() {
    private val TAG = "ArticleVm"
    private lateinit var articlePageData:LiveData<PagedList<Article>>
    var articleList = MutableLiveData<PagedList<Article>>()



    private val block :suspend CoroutineScope.(Int,Int)->List<Article> = {
            pageNum, pageSize ->
        Log.d(TAG,"loadAfter==params.key==${pageNum}===pageSize$pageSize")
        dataRepository.getMaidanArticleList(pageNum).datas!!
    }


    private val pageKeyedDataSource = PageKeyedDataSourceFactory(block)
    private val config = PagedList.Config.Builder()
        .setPageSize(20)
        .setInitialLoadSizeHint(15)
        .setPrefetchDistance(5)
        .setEnablePlaceholders(false)
        .build()

    fun getMaidanArticleList(){
        articlePageData = LivePagedListBuilder<Int,Article>(pageKeyedDataSource,config).build()
        articlePageData.observeForever(Observer {
            Log.d(TAG,"getMaidanArticleList===articlePageData====${it.size}")
            articleList.value = it
            Log.d(TAG,"getMaidanArticleList====articleList===${articleList.value?.size}")
        })
    }

    fun onRefresh(){
        pageKeyedDataSource.invalidate()
    }
}