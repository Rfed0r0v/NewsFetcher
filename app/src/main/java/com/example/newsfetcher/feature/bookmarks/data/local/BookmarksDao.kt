package com.example.newsfetcher.feature.bookmarks.data.local

import androidx.room.*
import com.example.newsfetcher.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsfetcher.feature.bookmarks.di.BOOKMARKS_TABLE

@Dao
interface BookmarksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(entity: BookmarkEntity)

    @Query("SELECT * FROM $BOOKMARKS_TABLE")
    fun read(): List<BookmarkEntity>

    @Update
    fun update(entity: BookmarkEntity)

    @Delete
    fun delete(entity: BookmarkEntity)
}