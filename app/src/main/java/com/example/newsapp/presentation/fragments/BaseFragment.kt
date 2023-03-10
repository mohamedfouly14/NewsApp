package com.example.newsapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.databinding.ViewDataBindingKtx
import androidx.fragment.app.Fragment

typealias Inflate <T> =(LayoutInflater,ViewGroup?,Boolean)->T
abstract class BaseFragment<VB: ViewDataBinding>(
    private val inflate: Inflate<VB>
):Fragment(){
    private var _binding:VB?=null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding= inflate.invoke(inflater, container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}