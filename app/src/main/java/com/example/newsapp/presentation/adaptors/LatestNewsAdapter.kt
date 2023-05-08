package com.example.newsapp.presentation.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.newsapp.R
import com.example.newsapp.Utils.convertDateFormate
import com.example.newsapp.data.model.Article
import com.example.newsapp.databinding.LatestNewsBinding
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton



@FragmentScoped
class LatestNewsAdapter @Inject constructor()
    :RecyclerView.Adapter<LatestNewsAdapter.NewsViewHolder> (){
    var isBookmark: Boolean = false
    private val callback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = LatestNewsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int =differ.currentList.size




    inner class NewsViewHolder(
        val binding:LatestNewsBinding
        ) :RecyclerView.ViewHolder(binding.root){
            fun bind(article: Article){
                binding.isBookmark=isBookmark
                binding.article=article
                binding.date.text= article.publishedAt.toString().convertDateFormate()
                Glide.with(binding.image.context).load(article.urlToImage)
                    .placeholder(R.drawable.ic_loading).error(R.drawable.ic_no_image)
                    .transform(CenterCrop(), RoundedCorners(15))
                    .into(binding.image)
                binding.root.setOnClickListener {
                    onItemClick?.let {
                        it(article)
                    }
                    }
                binding.bookmark.setOnClickListener {
                    onBookmarkClick?.let {
                        it(article)
                    }
                }
                }
            }
    private var onBookmarkClick: ((Article) -> Unit)? = null

    private var onItemClick: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClick = listener
    }

    fun onBookmarkClickListener(listener: (Article) -> Unit) {
        onBookmarkClick = listener
    }

    }

