package com.akexorcist.complexrecyclerviewr.adapter.article.group

import com.akexorcist.complexrecyclerviewr.vo.ArticleGroupResult
import com.akexorcist.library.complexrecyclerview.core.ComplexAdapter

class ArticleGroupItem(
    data: ArticleGroupResult.Group?,
    state: ArticleGroupState,
    viewHolder: Class<ArticleGroupViewHolder>
) : ComplexAdapter.Item<ArticleGroupResult.Group?, ArticleGroupState, ArticleGroupViewHolder>(
    data,
    state,
    viewHolder
)
