package com.test.mynewsapp.ui.headlines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.mynewsapp.databinding.ListItemHeadLinesBinding
import com.test.mynewsapp.ui.data.Article

/**
 * Adapter for the [RecyclerView] in [HeadLinesFragment].
 */
class HeadLinesAdapter :
    ListAdapter<Article, HeadLinesAdapter.ViewHolder>(DiffCallback()) {
    companion object {
        var count: Int = 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsArticle = getItem(position)
        holder.apply {
            bind(newsArticle)
            itemView.tag = newsArticle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = ListItemHeadLinesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(itemView)

    }
//adapter.getItemViewType(position)==7
//    private fun createOnClickListener(id: Int, name: String): View.OnClickListener {
//        return View.OnClickListener {
//            val direction = LegoThemeFragmentDirections.actionThemeFragmentToSetsFragment(id, name)
//            it.findNavController().navigate(direction)
//        }
//    }

    class ViewHolder(
        private val binding: ListItemHeadLinesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Article) {
            binding.apply {
                //clickListener = listener
                newsArticle = item
                executePendingBindings()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position % 7 * 2
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