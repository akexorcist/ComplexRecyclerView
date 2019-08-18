package com.akexorcist.complexrecyclerviewr.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.complexrecyclerviewr.R
import com.akexorcist.complexrecyclerviewr.adapter.article.category.ArticleCategoryItem
import com.akexorcist.complexrecyclerviewr.adapter.article.category.ArticleCategoryState
import com.akexorcist.complexrecyclerviewr.adapter.article.category.ArticleCategoryViewHolder
import com.akexorcist.complexrecyclerviewr.vo.ArticleCategoryResult
import com.akexorcist.library.complexrecyclerview.core.ComplexAdapter
import com.akexorcist.library.complexrecyclerview.core.ViewTypeGenerator
import com.akexorcist.library.complexrecyclerview.state.StateHandler

class ArticleCategoryFactory(
    stateHandler: StateHandler
) : ComplexAdapter.Factory<ArticleCategoryResult>(stateHandler) {
    private var listener: Listener? = null

    override fun build(
        data: ArticleCategoryResult,
        oldItemList: ArrayList<in ComplexAdapter.Item<ArticleCategoryResult, ComplexAdapter.State, RecyclerView.ViewHolder>>?
    ): ArrayList<in ComplexAdapter.Item<ArticleCategoryResult, ComplexAdapter.State, RecyclerView.ViewHolder>> {
        return ArrayList(data.categoryList?.map { category ->
            ArticleCategoryItem(category, ArticleCategoryState(false), ArticleCategoryViewHolder::class.java)
        }?.toList() ?: listOf())
    }

    override fun getDataClass(position: Int): Class<out Any> = when (itemList[position]) {
        is ArticleCategoryItem -> ArticleCategoryItem::class.java
        else -> ArticleCategoryItem::class.java
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder? {
        return when {
            ViewTypeGenerator.validate(viewType, ArticleCategoryItem::class.java, this) ->
                ArticleCategoryViewHolder(inflater.inflate(R.layout.item_article_category, parent, false))
            else ->
                null
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item: Any? = itemList[position]) {
            is ArticleCategoryItem -> setupCategoryView(holder as ArticleCategoryViewHolder, item)
        }
    }

    override fun getColumnCount(item: Any?): Float = 100f / getCount()

    override fun onSaveState(state: Bundle) {
    }

    override fun onRestoreState(state: Bundle) {
    }

    fun setCategorySelected(position: Int) {
        updateCategory { index, _ -> index == position }
    }

    fun setCategorySelected(selectedItem: ArticleCategoryItem) {
        updateCategory { _, item -> item == selectedItem }
    }

    private fun updateCategory(condition: (Int, ArticleCategoryItem) -> Boolean) {
        itemList.forEachIndexed { index, item ->
            (item as ArticleCategoryItem).state.isSelected = condition(index, item)
        }
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    private fun setupCategoryView(holder: ArticleCategoryViewHolder, item: ArticleCategoryItem) {
        holder.setName(item.data?.name)
        holder.setSelected(item.state.isSelected)
        holder.setItemClick { listener?.onCategorySelected(item) }
    }

    interface Listener {
        fun onCategorySelected(item: ArticleCategoryItem)
    }
}
