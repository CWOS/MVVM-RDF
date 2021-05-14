package com.cw.rdf.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.cw.rdf.app.databinding.FragmentArticleDetailBinding
import com.cw.rdf.app.model.Article
import com.cw.rdf.app.vm.ArticleDetailVm
import com.cw.rdf.app.vm.ArticleVm
import com.cw.rdf.core.base.BaseBindingViewModelFragment
import com.just.agentweb.AgentWeb
import kotlinx.android.synthetic.main.fragment_article_detail.*


/**
 * @Description:文章详情
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:05 AM
 *
 */
class ArticleDetailFragment:BaseBindingViewModelFragment<FragmentArticleDetailBinding, ArticleDetailVm>() {
    override fun initDataBinding(binding: FragmentArticleDetailBinding) {
        super.initDataBinding(binding)
        binding.lifecycleOwner = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString("title")
        val link = arguments?.getString("link")
        viewModel.title.value = title ?: ""
        AgentWeb.with(this)
            .setAgentWebParent(content, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go(link)
    }
}