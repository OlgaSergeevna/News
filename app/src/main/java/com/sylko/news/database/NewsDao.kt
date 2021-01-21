package com.sylko.news.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sylko.news.pojo.News

@Dao
interface NewsDao {
    @Query("SELECT * FROM all_articles ORDER BY publishedAt DESC")
    fun getAllNews(): LiveData<List<News>>

    @Query("SELECT * FROM all_articles WHERE url == :url LIMIT 1")
    fun getSelectedNews(url: String): LiveData<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<News>)
}