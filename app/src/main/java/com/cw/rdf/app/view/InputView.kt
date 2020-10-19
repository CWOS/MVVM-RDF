package com.cw.rdf.app.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import com.cw.rdf.app.R
import com.cw.rdf.app.databinding.LayoutEdittextBinding

/**
 * @Description:输入控件
 * @Author: wanglejun
 * @CreateDate： 2020/10/20 12:30 AM
 *
 */
class InputView : RelativeLayout {
    private lateinit var binding: LayoutEdittextBinding

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
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.layout_edittext,
            null,
            false
        )

        addView(binding.root)

        binding.contentEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.bottomLine.setBackgroundColor(resources.getColor(R.color.blue))
            } else {
                binding.bottomLine.setBackgroundColor(resources.getColor(R.color.gray1))
            }
        }
    }
}