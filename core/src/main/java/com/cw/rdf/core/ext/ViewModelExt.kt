package com.cw.rdf.core.ext

import android.content.Context
import androidx.lifecycle.Observer
import com.cw.rdf.core.base.BaseBindingViewModelActivity
import com.cw.rdf.core.base.BaseBindingViewModelFragment
import com.cw.rdf.core.base.BaseViewModel
import org.jetbrains.anko.toast

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/8/13 6:21 AM
 *
 */

fun BaseViewModel.bind(context: Context) {
    if (context is BaseBindingViewModelActivity<*, *>) {
        hintText.observe(context, Observer {
            val content = getHintText()
            if (!content.isNullOrBlank()) {
                context.toast(content)
            }
        })
        hintTextRes.observe(context,
            Observer {
                val contentRes = getHintRes()
                if (contentRes > 0) {
                    context.toast(contentRes)
                }
            })

        event.observe(context, Observer {
            val eventId = getEventId()
            if (eventId >= 0) {
                context.onViewModelEvent(eventId)
            }
        })
    }

}

fun BaseViewModel.bind(fragment: BaseBindingViewModelFragment<*, *>) {
    hintText.observe(fragment, Observer {
        val content = getHintText()
        if (!content.isNullOrBlank()) {
            fragment.context?.toast(content)
        }
    })
    hintTextRes.observe(fragment,
        Observer {
            val contentRes = getHintRes()
            if (contentRes > 0) {
                fragment.context?.toast(contentRes)
            }
        })

    event.observe(fragment, Observer {
        val eventId = getEventId()
        if (eventId >= 0) {
            fragment.onViewModelEvent(eventId)
        }
    })

}