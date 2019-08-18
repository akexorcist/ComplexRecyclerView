package com.akexorcist.complexrecyclerviewr.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.complexrecyclerviewr.R
import com.akexorcist.complexrecyclerviewr.adapter.article.category.ArticleCategoryItem
import com.akexorcist.complexrecyclerviewr.adapter.article.group.ArticleGroupItem
import com.akexorcist.complexrecyclerviewr.adapter.article.group.ArticleGroupState
import com.akexorcist.complexrecyclerviewr.adapter.article.group.ArticleGroupViewHolder
import com.akexorcist.complexrecyclerviewr.adapter.article.topic.ArticleTopicItem
import com.akexorcist.complexrecyclerviewr.adapter.article.topic.ArticleTopicState
import com.akexorcist.complexrecyclerviewr.adapter.article.topic.ArticleTopicViewHolder
import com.akexorcist.complexrecyclerviewr.vo.ArticleGroupResult
import com.akexorcist.library.complexrecyclerview.core.ComplexAdapter
import com.akexorcist.library.complexrecyclerview.core.ViewTypeGenerator
import com.akexorcist.library.complexrecyclerview.state.StateHandler

class ArticleGroupFactory(
    stateHandler: StateHandler
) : ComplexAdapter.Factory<ArticleGroupResult.Group>(stateHandler) {
    private var listener: Listener? = null

    override fun build(
        data: ArticleGroupResult.Group,
        oldItemList: ArrayList<in ComplexAdapter.Item<ArticleGroupResult.Group, ComplexAdapter.State, RecyclerView.ViewHolder>>?
    ): ArrayList<in ComplexAdapter.Item<ArticleGroupResult.Group, ComplexAdapter.State, RecyclerView.ViewHolder>> {
        val itemList: ArrayList<Any> = arrayListOf()
        if (data.topicList?.isNotEmpty() == true) {
            val expandState = if (oldItemList != null) (oldItemList[0] as ArticleGroupItem).state.isExpand else false
            itemList.add(ArticleGroupItem(data, ArticleGroupState(expandState), ArticleGroupViewHolder::class.java))
            if (oldItemList != null && (oldItemList[0] as ArticleGroupItem).state.isExpand) {
                data.topicList?.forEach { topic ->
                    itemList.add(ArticleTopicItem(topic, ArticleTopicState(data.id), ArticleTopicViewHolder::class.java))
                }
            }
        }
        return itemList
    }

    override fun getDataClass(position: Int): Class<out Any> = when (itemList[position]) {
        is ArticleGroupItem -> ArticleGroupItem::class.java
        is ArticleTopicItem -> ArticleTopicItem::class.java
        else -> ArticleCategoryItem::class.java
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder? {
        return when {
            ViewTypeGenerator.validate(viewType, ArticleGroupItem::class.java, this) ->
                ArticleGroupViewHolder(inflater.inflate(R.layout.item_article_group, parent, false))
            ViewTypeGenerator.validate(viewType, ArticleTopicItem::class.java, this) ->
                ArticleTopicViewHolder(inflater.inflate(R.layout.item_article_topic, parent, false))
            else ->
                null
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item: Any? = itemList[position]) {
            is ArticleGroupItem -> setupGroupView(holder as ArticleGroupViewHolder, item)
            is ArticleTopicItem -> setupTopicView(holder as ArticleTopicViewHolder, item)
        }
    }

    override fun onSaveState(state: Bundle) {
    }

    override fun onRestoreState(state: Bundle) {
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    fun toggleGroup() {
        updateGroup(
            onChange = { item -> !item.state.isExpand },
            onChanged = { refresh() }
        )
    }

    fun expandGroup() {
        updateGroup(
            onChange = { true },
            onChanged = { refresh() }
        )
    }

    fun collapseGroup() {
        updateGroup(
            onChange = { false },
            onChanged = { refresh() }
        )
    }

    private fun updateGroup(onChange: (ArticleGroupItem) -> Boolean, onChanged: () -> Unit) {
        getArticleGroupItem()?.let { item ->
            item as ArticleGroupItem
            val newState = onChange(item)
            if (item.state.isExpand != newState) {
                item.state.isExpand = newState
                onChanged.invoke()
            }
        }
    }

    private fun getArticleGroupItem() = itemList.find { item -> item is ArticleGroupItem }

    private fun setupGroupView(holder: ArticleGroupViewHolder, item: ArticleGroupItem) {
        holder.setName(item.data?.name)
        holder.setExpandState(item.state.isExpand)
        holder.setGroupClick { listener?.onGroupClick(item, this) }
    }

    private fun setupTopicView(holder: ArticleTopicViewHolder, item: ArticleTopicItem) {
        holder.setTitle(item.data?.title)
        holder.setTopicClick { listener?.onTopicClick(item) }
    }

    interface Listener {
        fun onGroupClick(item: ArticleGroupItem, factory: ArticleGroupFactory)
        fun onTopicClick(item: ArticleTopicItem)
    }
}
