package com.cw.rdf.app.vm

import androidx.lifecycle.MutableLiveData
import com.cw.rdf.core.base.BaseViewModel

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/8/19 7:18 AM
 *
 */
class MainVm:BaseViewModel() {
    val count = MutableLiveData<String>("999")
}