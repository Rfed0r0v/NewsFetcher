package com.example.newsfetcher.feature.bookmarks.data.local

import com.example.newsfetcher.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsfetcher.feature.domain.ArticleModel

interface BookmarksRepository {

    fun create(entity: ArticleModel)

    fun read(): List<ArticleModel>

    fun update(entity: ArticleModel)

    fun delete(entity: ArticleModel)
}