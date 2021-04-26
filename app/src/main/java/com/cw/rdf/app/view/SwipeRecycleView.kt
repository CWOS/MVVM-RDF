package com.cw.rdf.app.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cw.rdf.app.R

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 3/9/21 10:50 PM
 *
 */
class SwipeRecycleView: RecyclerView {
    constructor(context: Context) : super(context) {}
    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        var startX = 0f
        var startY = 0f
        when(ev?.action){
            MotionEvent.ACTION_DOWN->{
                startX = x
                startY = y
                parent.requestDisallowInterceptTouchEvent(true)
            }

            MotionEvent.ACTION_MOVE->{
                val endX = x
                val endY = y
                val disX = Math.abs(endX - startX)
                val disY = Math.abs(endY - startY)
                if (disX < disY){
                    parent.requestDisallowInterceptTouchEvent(canScrollVertically((startY - endY).toInt()))
                }else{
                    var isConsume = false
                    // 取得Banner的标签
                    // 取得Banner的标签
//                    val bannerView =
//                        findViewById<View>(R.id.header_viewpager2)
//                    if (bannerView != null) {
//                        val bannerTag = bannerView.tag.toString()
//                        // 判断是否由当前布局消耗Touch事件
//                        isConsume = childInterceptEvent(
//                            this,
//                            bannerTag,
//                            ev.rawX.toInt(),
//                            ev.rawY.toInt()
//                        )
//                    }
                    // isConsume == true 父布局放行，当前布局消耗Touch事件
                    // isConsume == false 父布局不放行，父布局消耗Touch事件
                    // isConsume == true 父布局放行，当前布局消耗Touch事件
                    // isConsume == false 父布局不放行，父布局消耗Touch事件
                    this.parent.requestDisallowInterceptTouchEvent(isConsume)
                }
            }

            MotionEvent.ACTION_UP->{
                parent.requestDisallowInterceptTouchEvent(false)
            }

            MotionEvent.ACTION_CANCEL->{
                parent.requestDisallowInterceptTouchEvent(false)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    // 递归遍历当前View树
    private fun childInterceptEvent(
        parentView: ViewGroup,
        targetViewTag: String,
        touchX: Int,
        touchY: Int
    ): Boolean {
        var isConsume = false
        for (i in parentView.childCount - 1 downTo 0) {
            val childView = parentView.getChildAt(i)
            if (!childView.isShown) {
                continue
            }
            // 判断view是否在触摸区域内
            val isTouchView = isTouchView(touchX, touchY, childView)
            // 消耗Touch事件的条件
            if (isTouchView && childView.tag != null && targetViewTag == childView.tag
                    .toString()
            ) {
                isConsume = true
                break
            }
            // 如果前面都不满足，继续递归遍历
            if (childView is ViewGroup) {
                if (!isTouchView) {
                    continue
                } else {
                    isConsume = childInterceptEvent(childView, targetViewTag, touchX, touchY)
                    if (isConsume) {
                        break
                    }
                }
            }
        }
        return isConsume
    }

    // 判断view是否在触摸区域内
    private fun isTouchView(
        touchX: Int,
        touchY: Int,
        view: View
    ): Boolean {
        val rect = Rect()
        view.getGlobalVisibleRect(rect)
        return rect.contains(touchX, touchY)
    }
}