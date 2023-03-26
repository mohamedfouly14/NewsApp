package com.example.newsapp.presentation.fragments.bookmarkFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.azmiradi.news.presentation.ui_handlers.Handlers
import com.example.newsapp.R
import com.example.newsapp.Utils.showToast
import com.example.newsapp.databinding.FragmentBookmarkBinding
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.presentation.adaptors.LatestNewsAdapter
import com.example.newsapp.presentation.fragments.BaseFragment
import java.util.logging.Handler
import javax.inject.Inject

class BookmarkFragment:BaseFragment<FragmentBookmarkBinding>(FragmentBookmarkBinding::inflate),Handlers {

    @Inject
    lateinit var latestNewsAdapter: LatestNewsAdapter

    private val viewModel: BookmarkViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setupAdaptor() {
        TODO("Not yet implemented")
    }

    override fun handleUI() {
        TODO("Not yet implemented")
    }

    override fun observeData() {
        viewModel.allNewsStates.observe(requireActivity()){
            if (it.isEmpty()) {
                binding.errorMassage.visibility = View.VISIBLE
                latestNewsAdapter.differ.submitList(ArrayList())
            } else {
                binding.errorMassage.visibility = View.GONE
                latestNewsAdapter.differ.submitList(it)
            }
        }
        viewModel.deleteArticle.observe(requireActivity()) {
            if (it) {
                viewModel.geBookmark()
                requireContext().showToast(getString(R.string.article_deleted))
            } else {
                requireContext().showToast(getString(R.string.article_not_deleted))
            }
        }
    }
}