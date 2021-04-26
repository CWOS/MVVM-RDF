package com.cw.rdf.app.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cw.rdf.app.R

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 3/9/21 11:52 PM
 *
 */
class RDFSwipeRefreshLayout:SwipeRefreshLayout {
    private var startY = 0f
    private var startX = 0f
    // 记录viewPager是否拖拽的标记
    private var mIsVpDragger = false
    private var mTouchSlop = 0
    constructor(context: Context) : super(context) {}
    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs){
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action){
            MotionEvent.ACTION_DOWN->{
                startX = x
                startY = y
                mIsVpDragger = false
            }

            MotionEvent.ACTION_MOVE->{
                if (mIsVpDragger) {
                    return false
                }
                val endX = x
                val endY = y
                val disX = Math.abs(endX - startX)
                val disY = Math.abs(endY - startY)
                Log.d("wang","onInterceptTouchEvent.....mTouchSlop:${mTouchSlop}...disX${disX}...disY${disY}...")

                if (disX > mTouchSlop && disX > disY) {
                    mIsVpDragger = true
                    return false
                }
            }

            MotionEvent.ACTION_UP->{
                mIsVpDragger = false
            }

            MotionEvent.ACTION_CANCEL->{
                mIsVpDragger = false
            }
        }
        return super.onInterceptTouchEvent(ev)

    }
}