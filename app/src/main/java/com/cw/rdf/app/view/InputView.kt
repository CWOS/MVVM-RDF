package com.cw.rdf.app.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import com.cw.rdf.app.R
import com.cw.rdf.app.databinding.LayoutInputviewBinding
import org.jetbrains.anko.px2sp

/**
 * @Description:输入控件
 * @Author: wanglejun
 * @CreateDate： 2020/10/20 12:30 AM
 *
 */
class InputView : RelativeLayout {
    private  var binding:LayoutInputviewBinding

    init {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.layout_inputview,
            null,
            false
        )
    }
    //标题
    private var title = ""

    //输入框hint
    private var hint = ""

    //输入框文本
    var contentText: String
        set(value) {
            binding.contentEdit.setText(value)
        }
        get() = binding.contentEdit.text.toString()


    //标题文字大小
    private var titleTextSize = px2sp(16)

    //输入框文字大小
    private var contentTextSize = px2sp(16)

    var textChangeListener: ITextChangeListener? = null

    constructor(context: Context) : super(context)
    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        init(attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.InputView)
        title = typedArray.getString(R.styleable.InputView_title).toString()
        hint = typedArray.getString(R.styleable.InputView_hint).toString()
        contentText = typedArray.getString(R.styleable.InputView_contentText).toString()
        titleTextSize = typedArray.getDimension(R.styleable.InputView_titleTextSize, px2sp(16))
        contentTextSize = typedArray.getDimension(R.styleable.InputView_contentTextSize, px2sp(16))


        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.layout_inputview,
            null,
            false
        )

        binding.titleText.text = title
        binding.titleText.textSize = px2sp(titleTextSize.toInt())
        contentText?.let {
            binding.contentEdit.setText(it)
        }
        binding.contentEdit.hint = hint
        binding.contentEdit.textSize = px2sp(contentTextSize.toInt())

        addView(binding.root)

        binding.contentEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.bottomLine.setBackgroundColor(resources.getColor(R.color.blue))
            } else {
                binding.bottomLine.setBackgroundColor(resources.getColor(R.color.gray1))
            }
        }

        binding.contentEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textChangeListener?.let {
                    it.onChange()
                }
            }
        })
    }


    interface ITextChangeListener {
        fun onChange()
    }
}