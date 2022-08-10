package com.example.newsfetcher.feature.mainscreen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.bookmarks.domain.BookmarksInteractor
import com.example.newsfetcher.feature.domain.ArticlesInteractor
import com.example.newsfetcher.feature.domain.DataEvent
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val interactor: ArticlesInteractor,
    private val bookmarksInteractor: BookmarksInteractor
) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadArticles)
    }

    override fun initialViewState(): ViewState = ViewState(articles = emptyList())

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is DataEvent.LoadArticles -> {
                viewModelScope.launch {
                    interactor.getArticles().fold(
                        onError = {
                            Log.e("ERROR", it.localizedMessage)
                        }, onSuccess = {
                            processDataEvent(DataEvent.OnLoadArticlesSucceed(it))
                        })
                }
                return null
            }
            is DataEvent.OnLoadArticlesSucceed -> {
                return previousState.copy(articles = event.articles)
            }
            is UiEvent.OnArticleClicked -> {
                viewModelScope.launch {
                    bookmarksInteractor.create(previousState.articles[event.index])
                }
                return null
            }
            else -> return null
        }
    }
}