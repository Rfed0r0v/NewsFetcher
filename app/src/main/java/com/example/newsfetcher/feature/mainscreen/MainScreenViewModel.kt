package com.example.newsfetcher.feature.mainscreen

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewModelScope
import com.example.newsfetcher.MainActivity
import com.example.newsfetcher.R
import com.example.newsfetcher.SecondActivity
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

    override fun initialViewState(): ViewState = ViewState(
        articleList = emptyList(),
        articlesShown = emptyList(),
        isSearchEnabled = false
    )

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
                return previousState.copy(
                    articleList = event.articles,
                    articlesShown = event.articles
                )
            }
            is UiEvent.OnArticleClicked -> {
               // viewModelScope.launch {
               //     bookmarksInteractor.create(previousState.articlesShown[event.index])
                //}
                val intent = Intent(MainActivity::class as Context,SecondActivity::class.java)
                startActivity(MainActivity::class as Context,intent,null)

            return null
            }

            is UiEvent.OnSearchButtonClicked -> {
                return previousState.copy(
                    articlesShown = if (previousState.isSearchEnabled) previousState.articleList else previousState.articlesShown,
                    isSearchEnabled = !previousState.isSearchEnabled
                )
            }
            is UiEvent.OnSearchEdit -> {
                return previousState.copy(articlesShown = previousState.articleList.filter {
                    it.title.contains(
                        event.text
                    )
                })
            }
            else -> return null
        }
    }
}