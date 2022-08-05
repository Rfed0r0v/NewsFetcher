package com.example.newsfetcher.feature.bookmarks.ui

import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.bookmarks.domain.BookmarksInteractor

class BookmarksScreenViewModel(private val interactor: BookmarksInteractor):BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState = ViewState(bookmarksArticle = emptyList())

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        return null
    }

}