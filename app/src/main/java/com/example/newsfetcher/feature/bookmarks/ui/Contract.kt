package com.example.newsfetcher.feature.bookmarks.ui

import com.example.newsfetcher.feature.domain.ArticleModel

data class ViewState(
    val bookmarksArticle:List<ArticleModel>
)

sealed class UiEvent()
sealed class DataEvent()
