package com.sylko.news.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "all_articles")
data class News (
    @SerializedName("title")
    @Expose
     val title: String?,

    @SerializedName("description")
    @Expose
     val description: String?,

    @PrimaryKey
    @SerializedName("url")
    @Expose
     val url: String,

    @SerializedName("urlToImage")
    @Expose
     val urlToImage: String?,

    @SerializedName("publishedAt")
    @Expose
     val publishedAt: String?
)