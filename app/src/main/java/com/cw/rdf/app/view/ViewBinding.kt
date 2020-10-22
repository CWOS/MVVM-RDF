package com.cw.rdf.app.view

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.MutableLiveData

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/10/21 12:30 AM
 *
 */

@BindingAdapter("contentText")
fun setInputViewContent(inputView: InputView,contentText:String){
    if(inputView.contentText.trim() == contentText.trim()){
        return
    }
    inputView.contentText = contentText
}

@InverseBindingAdapter(attribute = "contentText",event = "textAttrChanged")
fun getInputViewContent(inputView: InputView):String{
    return inputView.contentText
}

@BindingAdapter("textAttrChanged",requireAll = false)
fun inputViewAttrChange(inputView: InputView,listener: InverseBindingListener){
    listener?.let {
        inputView.textChangeListener = object :InputView.ITextChangeListener{
           override fun onChange() {
               it.onChange()
           }
       }
    }
}