package com.example.newsapp.presentation.fragments.detailFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.azmiradi.news.presentation.ui_handlers.BaseHandlers
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.Utils.convertDateFormate
import com.example.newsapp.Utils.showToast
import com.example.newsapp.data.model.Article
import com.example.newsapp.databinding.FragmentDetailsBinding
import com.example.newsapp.presentation.fragments.BaseFragment

class DetailFragment :BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate),BaseHandlers{

    private val viewModel:DetailsViewModel by viewModels()
    lateinit var article: Article

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        handleUI()
    }

    override fun handleUI() {
        binding.article=article
        binding.viewModel=viewModel
        binding.lifecycleOwner=this
        binding.date.text = article.publishedAt.toString().convertDateFormate()
        Glide.with(binding.image.image.context).load(article.urlToImage)
            .placeholder(R.drawable.ic_loading).error(R.drawable.ic_no_image)
            .into(binding.image.image)
    }

    override fun observeData() {
        val args : DetailFragmentArgs by navArgs()
        article = args.selectedItem
        viewModel.saveArticle.observe(requireActivity()) {
            if (it) {
                requireContext().showToast(getString(R.string.article_saved))
            } else {
                requireContext().showToast(getString(R.string.article_not_saved))
            }
        }
    }
}