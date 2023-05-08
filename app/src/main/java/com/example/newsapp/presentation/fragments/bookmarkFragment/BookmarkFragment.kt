package com.example.newsapp.presentation.fragments.bookmarkFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.azmiradi.news.presentation.ui_handlers.Handlers
import com.example.newsapp.R
import com.example.newsapp.Utils.showToast
import com.example.newsapp.databinding.FragmentBookmarkBinding
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.presentation.adaptors.LatestNewsAdapter
import com.example.newsapp.presentation.fragments.BaseFragment
import com.example.newsapp.presentation.fragments.homeFragment.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import java.util.logging.Handler
import javax.inject.Inject
@AndroidEntryPoint
class BookmarkFragment:BaseFragment<FragmentBookmarkBinding>(FragmentBookmarkBinding::inflate),Handlers {

    @Inject
    lateinit var latestNewsAdapter: LatestNewsAdapter

    private val viewModel: BookmarkViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdaptor()
        handleUI()
        observeData()
    }

    override fun setupAdaptor() {
        latestNewsAdapter.isBookmark = true
        latestNewsAdapter.onBookmarkClickListener {
            viewModel.deleteArticle(it)
        }
        latestNewsAdapter.setOnItemClickListener {article->
            val action= BookmarkFragmentDirections.actionBookmarkFragment3ToDetailFragment(article)
            findNavController().navigate(action)
        }
    }

    override fun handleUI() {
        binding.bookmarkRecyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.bookmarkRecyclerView.adapter = latestNewsAdapter
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