package com.cw.rdf.core.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

/**
 * @Description: ImageView 加载网络图片 binding adapter，
 * 图片加载使用开源图片加载库 Glide
 * @Author: chengminghui
 * @CreateDate： 2020/8/21 7:08 AM
 *
 */

/**
 *
 * @description 根据 url 加载图片显示到目标 ImageView
 * @param imageView 图片加载 ImageView 对象
 * @param url 图片 url
 * @return
 *
 * app:imageUrl="@{vm.imageUrl}"
 */
@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}

/**
 * 
 * @description 根据 url 加载图片显示到目标 ImageView，并设置图片圆角
 * @param imageView 图片加载 ImageView 对象
 * @param url 图片 url
 * @param round 图片圆角
 * @return 
 *
 * app:imageUrl="@{vm.imageUrl}"
 * app:round="@{@dimen/dp8}"
 */
@BindingAdapter("imageUrl", "round")
fun loadImage(imageView: ImageView, url: String?, round: Float) {
    Glide.with(imageView.context)
        .load(url)
        .transform(CenterCrop(), RoundedCorners(round.toInt()))
        .into(imageView)
}

/**
 * 
 * @description 根据 url 加载图片显示到目标 ImageView，并设置图片圆角、占位图
 * @param imageView 图片加载 ImageView 对象
 * @param url 图片 url
 * @param round 图片圆角
 * @param holderDrawable 占位图
 * @return
 *
 * app:imageUrl="@{vm.imageUrl}"
 * app:round="@{@dimen/dp8}"
 * app:placeHolder="@{@drawable/img_holder}"
 */
@BindingAdapter("imageUrl", "round", "placeHolder")
fun loadImage(imageView: ImageView, url: String?, round: Float, holderDrawable: Drawable) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(holderDrawable)
        .transform(CenterCrop(), RoundedCorners(round.toInt()))
        .into(imageView)
}

/**
 *
 * @description 根据 url 加载图片显示到目标 ImageView，并设置图片圆角、占位图、size
 * @param imageView 图片加载 ImageView 对象
 * @param url 图片 url
 * @param round 图片圆角
 * @param holderDrawable 占位图
 * @param size 图片大小
 * @return
 *
 * app:imageUrl="@{vm.imageUrl}"
 * app:round="@{@dimen/dp8}"
 * app:placeHolder="@{@drawable/img_holder}"
 * app:imageSize="@{@dimen/dp120}"
 */
@BindingAdapter("imageUrl", "round", "placeHolder", "imageSize")
fun loadImage(imageView: ImageView, url: String?, round: Float, holderDrawable: Drawable, size : Float) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(holderDrawable)
        .override(size.toInt(), size.toInt())
        .transform(CenterCrop(), RoundedCorners(round.toInt()))
        .into(imageView)
}

/**
 *
 * @description 根据 url 加载图片显示到目标 ImageView，并设置图片占位图、加载失败占位图
 * @param imageView 图片加载 ImageView 对象
 * @param url 图片 url
 * @param holderDrawable 占位图
 * @param errorDrawable 加载失败占位图
 * @return
 *
 * app:imageUrl="@{vm.imageUrl}"
 * app:placeHolder="@{@drawable/img_holder}"
 * app:error="@{@drawable/img_error}"
 */
@BindingAdapter("imageUrl", "placeHolder", "error")
fun loadImage(
    imageView: ImageView,
    url: String?,
    holderDrawable: Drawable,
    errorDrawable: Drawable
) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(holderDrawable)
        .error(errorDrawable)
        .into(imageView)
}

/**
 *
 * @description 根据 url 加载图片显示到目标 ImageView，并设置图片加载失败占位图
 * @param imageView 图片加载 ImageView 对象
 * @param url 图片 url
 * @param errorDrawable 加载失败占位图
 * @return
 *
 * app:imageUrl="@{vm.imageUrl}"
 * app:error="@{@drawable/img_error}"
 */
@BindingAdapter("imageUrl", "error")
fun loadImageError(imageView: ImageView, url: String?, errorDrawable: Drawable) {
    Glide.with(imageView.context)
        .load(url)
        .error(errorDrawable)
        .into(imageView)
}

/**
 *
 * @description 根据 url 加载图片显示到目标 ImageView，并设置图片加载占位图
 * @param imageView 图片加载 ImageView 对象
 * @param url 图片 url
 * @param holderDrawable 加载占位图
 * @return
 *
 * app:imageUrl="@{vm.imageUrl}"
 * app:placeHolder="@{@drawable/img_holder}"
 */
@BindingAdapter("imageUrl", "placeHolder")
fun loadImage(imageView: ImageView, url: String?, holderDrawable: Drawable) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(holderDrawable)
        .into(imageView)
}