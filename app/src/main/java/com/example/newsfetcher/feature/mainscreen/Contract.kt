package com.example.newsfetcher.feature.mainscreen

import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.bookmarks.ui.UiEvent
import com.example.newsfetcher.feature.domain.ArticleModel

data class ViewState(
    val isSearchEnabled: Boolean,
    val articlesShown: List<ArticleModel>,
    val articleList: List<ArticleModel>
)

sealed class UiEvent : Event {
    data class OnArticleClicked(val index: Int) : DataEvent()
    object OnSearchButtonClicked : com.example.newsfetcher.feature.mainscreen.UiEvent()
    data class OnSearchEdit(val text:String):com.example.newsfetcher.feature.mainscreen.UiEvent()
}

sealed class DataEvent : Event {
    object LoadArticles : DataEvent()
    data class OnLoadArticlesSucceed(val articles: List<ArticleModel>) : DataEvent()

}