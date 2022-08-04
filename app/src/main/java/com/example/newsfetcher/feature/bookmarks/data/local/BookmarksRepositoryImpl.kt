package com.example.newsfetcher.feature.bookmarks.data.local

import com.example.newsfetcher.feature.bookmarks.data.toDomain
import com.example.newsfetcher.feature.bookmarks.data.toEntity
import com.example.newsfetcher.feature.domain.ArticleModel

class BookmarksRepositoryImpl(private val bookmarksLocalSource: BookmarksLocalSource) :
    BookmarksRepository {

    override fun create(model: ArticleModel) {
        bookmarksLocalSource.create(model.toEntity())
    }

    override fun read(): List<ArticleModel> {
        return bookmarksLocalSource.read().map { it.toDomain() }
    }

    override fun update(model: ArticleModel) {
        bookmarksLocalSource.update(model.toEntity())
    }

    override fun delete(model: ArticleModel) {
        bookmarksLocalSource.delete(model.toEntity())
    }
}