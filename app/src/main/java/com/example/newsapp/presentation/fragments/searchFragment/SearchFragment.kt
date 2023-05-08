package com.example.newsapp.presentation.fragments.searchFragment

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.azmiradi.news.presentation.ui_handlers.HandlerRequest
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.databinding.FragmentSearchBinding
import com.example.newsapp.presentation.adaptors.LatestNewsAdapter
import com.example.newsapp.presentation.fragments.BaseFragment
import com.example.newsapp.presentation.fragments.homeFragment.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment:BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate),
    HandlerRequest{
    private val viewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var latestNewsAdapter: LatestNewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleUI()
        observeData()
        setupAdaptor()

    }


    override fun setupAdaptor() {
        latestNewsAdapter.setOnItemClickListener {
            val action= HomeFragmentDirections.actionHomeFragment3ToDetailFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun handleUI() {
        binding.recycler.adapter = latestNewsAdapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (it.isNotEmpty())
                        viewModel.Search(it)
                    else
                        latestNewsAdapter.differ.submitList(ArrayList())
                }
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isNotEmpty()) {
                        viewModel.Search(it)
                    } else
                        latestNewsAdapter.differ.submitList(ArrayList())
                }
                return false

            }

        })
    }

    override fun observeData() {
        viewModel.allNewsState.observe(requireActivity()){
            if(it.isLoading)
                startRequest()
            if(it.error.isNotEmpty())
                endRequest(it.error)
            it.data?.let {
                if(it.isEmpty()){
                    endRequest(getString(R.string.not_found_result))
                    latestNewsAdapter.differ.submitList(ArrayList())
                }
                else{
                    endRequest()
                    latestNewsAdapter.differ.submitList(it)
                }

            }
        }
    }

    override fun startRequest() {
        binding.errorText.visibility = View.GONE
        binding.progress.visibility = View.VISIBLE
    }

    override fun endRequest(errorMessage: String?) {
        binding.progress.visibility = View.GONE
        errorMessage.let {
            binding.errorText.text = it
            binding.errorText.visibility = View.VISIBLE
        }
    }
}