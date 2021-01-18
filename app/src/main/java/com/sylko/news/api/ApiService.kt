package com.sylko.news.api

import com.sylko.news.pojo.NewsInfoListOfData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

//здесь хранятся все методы по работе с сетью
interface ApiService {

    @GET("top-headlines") //это эндпоинт
    fun getArticles(
            @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
            @Query(QUERY_PARAM_COUNTRY) country: String = COUNTRY
    ): Single<NewsInfoListOfData>

    companion object {
        private const val QUERY_PARAM_API_KEY = "apiKey"
        private const val QUERY_PARAM_COUNTRY = "country"

        private const val COUNTRY = "ru"
    }
}