package com.cw.rdf.app.vm

import com.cw.rdf.app.repository.DataRepository
import com.cw.rdf.core.base.BaseViewModel

/**
 * @Description:我的 ViewModel
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:10 AM
 *
 */
class MineVm(private val dataRepository: DataRepository):BaseViewModel() {
    /**
     *
     * @description 获取收藏列表
     * @param pageIndex 页码
     * @return
     *
     */
    fun getCollectList(pageIndex:Int) = launch {
        dataRepository.getCollectList(0)
    }

}