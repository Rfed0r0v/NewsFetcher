package com.example.newsfetcher.feature.bookmarks.data.local

import com.example.newsfetcher.feature.bookmarks.data.local.model.BookmarkEntity

class BookmarksLocalSource(private val bookmarksDao: BookmarksDao) {
    fun create(entity: BookmarkEntity) {
        bookmarksDao.create(entity)
    }

    fun read(): List<BookmarkEntity> {
        return bookmarksDao.read()
    }

    fun update(entity: BookmarkEntity) {
        bookmarksDao.update(entity)
    }

    fun delete(entity: BookmarkEntity) {
        bookmarksDao.delete(entity)
    }
}