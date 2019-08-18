package com.akexorcist.complexrecyclerviewr.adapter.article.topic

import com.akexorcist.complexrecyclerviewr.vo.ArticleGroupResult
import com.akexorcist.library.complexrecyclerview.core.ComplexAdapter

class ArticleTopicItem(
    data: ArticleGroupResult.Topic?,
    state: ArticleTopicState,
    viewHolder: Class<ArticleTopicViewHolder>
) : ComplexAdapter.Item<ArticleGroupResult.Topic?, ArticleTopicState, ArticleTopicViewHolder>(
    data,
    state,
    viewHolder
)
