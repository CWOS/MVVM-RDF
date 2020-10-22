package com.cw.rdf.app.view

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.cw.rdf.app.R


/**
 * @Description: 带清除按钮带EditText
 * @Author: wanglejun
 * @CreateDate： 2020/10/19 11:29 PM
 *
 */

class ClearEditText : AppCompatEditText, View.OnFocusChangeListener, TextWatcher {
    private var clearDrawable: Drawable? = null

    constructor(context: Context) : super(context) {}
    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        //未设置使用默认清除图标
        clearDrawable = compoundDrawables[2]
        if (clearDrawable == null) {
            clearDrawable = resources.getDrawable(R.mipmap.icon_clear)
        }
        clearDrawable?.let {
            it.bounds = Rect(0, 0, it.intrinsicWidth/2, it.intrinsicHeight/2)
        }

        onFocusChangeListener = this
        addTextChangedListener(this)

        setDrawableVisible(false)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP) {
            if (compoundDrawables[2] != null) {
                val start = width - totalPaddingRight + paddingRight
                val end = width
                if (event.x > start && event.x < end) {
                    setText("")
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        text?.let {
            setDrawableVisible(it.isNotEmpty())
        }
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        text?.let {
            if (hasFocus && it.isNotEmpty()) {
                setDrawableVisible(true)
            } else {
                setDrawableVisible(false)
            }
        }

    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    private fun setDrawableVisible(visible: Boolean) {
        var rightDrawable: Drawable? = null
        if (visible) {
            rightDrawable = clearDrawable
        }
        setCompoundDrawables(
            compoundDrawables[0],
            compoundDrawables[1],
            rightDrawable,
            compoundDrawables[3]
        );
    }

}