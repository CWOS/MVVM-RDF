package com.cw.rdf.core.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * @Description: View 相关属性 BindingAdapter
 * @Author: wanglejun
 * @CreateDate： 2020/8/21 7:22 AM
 *
 */

@BindingAdapter("android:visibility")
fun setVisibility(view: View, isVisibility: Boolean) {
    view.visibility = if (isVisibility) View.VISIBLE else View.GONE
}

@BindingAdapter("android:invisibility")
fun setInVisibility(view: View, isVisibility: Boolean) {
    view.visibility = if (isVisibility) View.VISIBLE else View.INVISIBLE
}


@BindingAdapter("android:src")
fun setImage(imageView: ImageView, imgRes: Int) {
    imageView.setImageResource(imgRes)
}
