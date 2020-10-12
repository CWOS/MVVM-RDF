package com.cw.rdf.app.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cw.rdf.app.model.PageData
import com.cw.rdf.app.model.Project
import com.cw.rdf.app.model.ProjectTree
import com.cw.rdf.app.repository.DataRepository
import com.cw.rdf.core.base.BaseViewModel

/**
 * @Description:项目 ViewModel
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:10 AM
 *
 */
class ProjectVm(private val dataRepository: DataRepository) : BaseViewModel() {
    var projectTreeList = MutableLiveData<List<ProjectTree>>()
    var projectList = MutableLiveData<PageData<List<Project>>>()

    fun getProjectTree() = launch {
        projectTreeList.value = dataRepository.getProjectTree()
        projectTreeList.value?.let {
            it.get(0).isSelect = false
            getProjects(0,it.get(0).id)
        }

        Log.e("ProjectVm", "projectTreeList===${projectTreeList.value?.size}")
    }

    fun getProjects(pageIndex: Int, cid: Int) = launch {
        projectList.value = dataRepository.getProjects(pageIndex, cid)
    }
}