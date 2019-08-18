package com.akexorcist.complexrecyclerviewr.api

import com.akexorcist.complexrecyclerviewr.vo.ArticleCategoryResult
import com.akexorcist.complexrecyclerviewr.vo.ArticleGroupResult

object MockApi {
    fun getProfile() = MockData.getAkexorcistProfile()

    fun getArticleCategories() = ArticleCategoryResult(MockData.getArticleCategories())

    fun getArticleGroup(groupId: String?) = when (groupId) {
        "0872811069" -> ArticleGroupResult(MockData.getSleepingForLessArticleGroup())
        "9785588260" -> ArticleGroupResult(MockData.getMediumArticleGroup())
        else -> null
    }
}
