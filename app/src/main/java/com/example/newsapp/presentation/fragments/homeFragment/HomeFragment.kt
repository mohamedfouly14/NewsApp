package com.example.newsapp.presentation.fragments.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.azmiradi.news.presentation.ui_handlers.HandlerRequest
import com.example.newsapp.Utils.NewsSections
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.presentation.adaptors.LatestNewsAdapter
import com.example.newsapp.presentation.fragments.BaseFragment
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    HandlerRequest {
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var latestNewsAdapter: LatestNewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdaptor()
        handleUI()
        observeData()

    }





    override fun handleUI() {
        binding.latestNewsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.latestNewsRecycler.adapter = latestNewsAdapter
    }

    override fun observeData() {
        viewModel.allNewsState.observe(requireActivity()) {
            if (it.isLoading) {
                startRequest()
            }
            if (it.error.isNotEmpty()) {
                endRequest(it.error)
            }
            it.data?.let { data ->
                endRequest()
                data[NewsSections.LatestNews.name]?.let {
                    latestNewsAdapter.differ.submitList(it)
                }
            }

        }
    }
    override fun setupAdaptor() {
    }
    override fun startRequest() {
        binding.latestNews.visibility = View.GONE
        binding.errorText.visibility = View.GONE
        binding.newsTitle.visibility=View.GONE
        binding.progress.visibility=View.VISIBLE

    }

    override fun endRequest(errorMessage: String?) {
        binding.progress.visibility=View.GONE
        binding.newsTitle.visibility=View.VISIBLE
        binding.latestNews.visibility=View.GONE
        errorMessage?.let{
            binding.errorText.visibility=View.VISIBLE
            binding.errorText.text=it
        }
    }
}
