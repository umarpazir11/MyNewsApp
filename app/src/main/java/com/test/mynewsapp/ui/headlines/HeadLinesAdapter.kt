package com.test.mynewsapp.ui.headlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.mynewsapp.R
import com.test.mynewsapp.databinding.ListItemHeadLinesBinding
import com.test.mynewsapp.ui.data.model.Article
import com.test.mynewsapp.ui.headlinesdetails.DetailsFragment

/**
 * Adapter for the [RecyclerView] in [HeadLinesFragment].
 */
class HeadLinesAdapter :
    PagedListAdapter<Article, HeadLinesAdapter.ViewHolder>(DiffCallback()) {
    private lateinit var navController: NavController

    companion object {
        const val NEWS_ARTICLES_SET = 7
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsArticle = getItem(position)
        holder.apply {
            bind(createOnClickListener(newsArticle!!), newsArticle)
            itemView.tag = newsArticle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = ListItemHeadLinesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(itemView)

    }

    private fun createOnClickListener(article: Article): View.OnClickListener {
        return View.OnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("articleTitle", article)
            it.findNavController().navigate(R.id.action_headLinesFragment_to_detailsFragment,bundle)
        }
    }

    class ViewHolder(
        private val binding: ListItemHeadLinesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Article) {
            binding.apply {
                clickListener = listener
                newsArticle = item
                executePendingBindings()
            }
        }
    }

    /**
     * This will find out every 1st out of 7 news articles.
     */
    override fun getItemViewType(position: Int): Int {
        return position % NEWS_ARTICLES_SET * 2
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}