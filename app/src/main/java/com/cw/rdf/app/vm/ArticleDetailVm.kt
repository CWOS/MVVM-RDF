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
 * @Description:文章详情 ViewModel
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:10 AM
 *
 */
class ArticleDetailVm(private val dataRepository: DataRepository):BaseViewModel() {
    var title = MutableLiveData<String>()
}