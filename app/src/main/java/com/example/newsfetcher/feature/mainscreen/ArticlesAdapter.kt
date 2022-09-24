package com.example.newsfetcher.feature.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.domain.ArticleModel

class ArticlesAdapter(
    val onItemClicked: (ArticleModel) -> Unit,
    val onAddToBookmarksClicked: (Int) -> Unit
) : RecyclerView
.Adapter<ArticlesAdapter.ViewHolder>() {
    private var articlesData: List<ArticleModel> = emptyList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textTitle: TextView
        val textData: TextView
        val yesBookmark: ImageView
        val noBookmarks: ImageView

        init {
            textTitle = view.findViewById(R.id.tvTitle)
            textData = view.findViewById(R.id.tvDate)
            yesBookmark = view.findViewById(R.id.ivBookmarkYes)
            noBookmarks = view.findViewById<ImageView?>(R.id.ivBookmarksNo)
                .also { it.visibility = ImageView.VISIBLE }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_article, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.noBookmarks.setOnClickListener {
            onAddToBookmarksClicked.invoke(position)
            notifyDataSetChanged()
        }

        viewHolder.itemView.setOnClickListener {
            onItemClicked.invoke(articlesData[position])
        }

        viewHolder.textTitle.text = articlesData[position].title
        viewHolder.textData.text = articlesData[position].publishedAt
        viewHolder.yesBookmark.visibility =
            if (articlesData[position].bookmarkFlag) ImageView.VISIBLE else ImageView.GONE
    }

    override fun getItemCount() = articlesData.size

    fun setData(articles: List<ArticleModel>) {
        articlesData = articles
        notifyDataSetChanged()
    }
}