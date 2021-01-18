package com.sylko.news.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsInfoListOfData (
        @SerializedName("articles")
        @Expose
        val articles : List<News>
)
