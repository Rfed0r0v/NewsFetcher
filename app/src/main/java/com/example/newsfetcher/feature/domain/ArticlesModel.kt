package com.example.newsfetcher.feature.domain

import com.example.newsfetcher.base.Event


data class ArticlesModel(
    val articlesList: List<ArticleModel>
)

sealed class DataEvent : Event {
    object LoadArticles : DataEvent()
    data class OnLoadArticlesSucceed(val articles: List<ArticleModel>):DataEvent()
}