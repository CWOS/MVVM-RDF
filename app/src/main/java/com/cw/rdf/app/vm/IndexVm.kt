package com.cw.rdf.app.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cw.rdf.app.model.Article
import com.cw.rdf.app.model.Banner
import com.cw.rdf.app.repository.DataRepository
import com.cw.rdf.core.base.BaseViewModel
import com.cw.rdf.recycle.paging.PageKeyedDataSourceFactory
import kotlinx.coroutines.CoroutineScope

/**
 * @Description:首页 ViewModel
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:10 AM
 *
 */
class IndexVm(private val dataRepository: DataRepository):BaseViewModel() {
    private val TAG = "IndexVm"
    val banners = MutableLiveData<List<Banner>>()
    val articleList = MutableLiveData<PagedList<Article>>()
    private lateinit var articlePageData:LiveData<PagedList<Article>>
    private val config = PagedList.Config.Builder()
            //分页加载数
        .setPageSize(20)
            //初始加载数
        .setInitialLoadSizeHint(15)
        .setPrefetchDistance(5)
        .setEnablePlaceholders(false)
        .build()

    private val block :suspend CoroutineScope.(Int,Int)->List<Article> = {
            pageNum, pageSize ->
        Log.d(TAG,"loadAfter==params.key==${pageNum}===pageSize$pageSize")
        dataRepository.getIndexArticleList(pageNum).datas!!
    }

    private val pageKeyedDataSource = PageKeyedDataSourceFactory(block)

    fun getArticle(){
        articlePageData = LivePagedListBuilder<Int,Article>(pageKeyedDataSource,config).build()
        articlePageData.observeForever(Observer {
            Log.d(TAG,"getArticle===articlePageData====${it.size}")
            articleList.value = it
            Log.d(TAG,"getArticle====articleList===${articleList.value?.size}")

        })
    }

    /**
     *
     * @description 获取首页 banner
     * @return
     *
     */
    fun getBanners() = launch {
        banners.value = dataRepository.getBanners()
    }

    fun onRefresh(){
        pageKeyedDataSource.invalidate()
    }
}