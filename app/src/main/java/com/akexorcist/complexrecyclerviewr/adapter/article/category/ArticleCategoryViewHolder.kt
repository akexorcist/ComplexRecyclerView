package com.akexorcist.complexrecyclerviewr.adapter.article.category

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_article_category.*

class ArticleCategoryViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun setName(name: String?) {
        textViewCategory.text = name ?: "-"
    }

    fun setSelected(isSelected: Boolean) {
        itemView.isSelected = isSelected
    }

    fun setItemClick(body: () -> Unit) {
        itemView.setOnClickListener { body.invoke() }
    }
}
