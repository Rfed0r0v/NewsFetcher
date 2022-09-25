package com.example.newsfetcher.feature.detailednews.ui

import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.domain.ArticleModel

data class ViewState(
    val articleAuthor: String?,
    val articleTitle: String?,
    val articleDescription: String?,
    val articleContent: String?,
    val articleUrlToImage: String?,
    val articleUrl: String?,
    val articlePublishedAt: String?
)

sealed class UiEvent() : Event {}

sealed class DataEvent() : Event {
    data class ShowArticle(val currentArticle: ArticleModel) : DataEvent()

}
