package com.akexorcist.complexrecyclerviewr.adapter.article.category

import com.akexorcist.complexrecyclerviewr.vo.ArticleCategoryResult
import com.akexorcist.library.complexrecyclerview.core.ComplexAdapter

class ArticleCategoryItem(
    data: ArticleCategoryResult.Category?,
    state: ArticleCategoryState,
    viewHolder: Class<ArticleCategoryViewHolder>
) : ComplexAdapter.Item<ArticleCategoryResult.Category?, ArticleCategoryState, ArticleCategoryViewHolder>(
    data,
    state,
    viewHolder
)
