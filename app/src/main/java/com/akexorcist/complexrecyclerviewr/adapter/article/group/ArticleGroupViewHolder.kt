package com.akexorcist.complexrecyclerviewr.adapter.article.group

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_article_group.*

class ArticleGroupViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun setName(name: String?) {
        textViewGroup.text = name ?: "-"
    }

    fun setExpandState(isExpand: Boolean) {
        imageViewExpandIndicator.isSelected = isExpand
    }

    fun setGroupClick(body: () -> Unit) {
        itemView.setOnClickListener { body.invoke() }
    }
}
