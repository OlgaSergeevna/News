package com.sylko.news.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sylko.news.pojo.News

@Database(entities = [News::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "news.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                        Room.databaseBuilder(
                                context,
                                AppDatabase::class.java,
                                DB_NAME
                        ).build()
                db = instance
                return instance
            }
        }
    }

    abstract fun newsDao(): NewsDao
}