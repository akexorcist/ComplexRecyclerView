package com.akexorcist.complexrecyclerviewr.adapter.article.topic

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_article_topic.*

class ArticleTopicViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun setTitle(title: String?) {
        textViewTitle.text = title ?: "-"
    }

    fun setTopicClick(body: () -> Unit) {
        itemView.setOnClickListener { body.invoke() }
    }
}
