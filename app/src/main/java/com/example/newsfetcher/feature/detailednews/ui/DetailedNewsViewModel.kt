package com.example.newsfetcher.feature.detailednews.ui

import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event


class DetailedNewsViewModel: BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState(
        articleAuthor = "",
        articleTitle = "",
        articleDescription = "",
        articleContent = "",
        articleUrlToImage = "",
        articleUrl = "",
        articlePublishedAt = ""
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {

        when (event) {
            is DataEvent.ShowArticle -> {
                return previousState.copy(
                    articleAuthor = event.currentArticle.author,
                    articleTitle = event.currentArticle.title,
                    articleDescription = event.currentArticle.description,
                    articleUrlToImage = event.currentArticle.urlToImage,
                    articleUrl = event.currentArticle.url,
                    articlePublishedAt = event.currentArticle.publishedAt,
                    articleContent = event.currentArticle.content

                )
            }
        }
        return null
    }
}