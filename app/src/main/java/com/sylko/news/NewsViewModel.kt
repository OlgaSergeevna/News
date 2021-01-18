package com.sylko.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.sylko.news.api.ApiFactory
import com.sylko.news.database.AppDatabase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val allNews = db.newsDao().getAllNews()

    fun loadData() {
        val disposable = ApiFactory.apiService.getArticles("b6869a57147642bf8ef94a7c6e3ed9b9")
            //.map { it.articles?.map { it.title }?.joinToString(",") }//так можно было получить только
            //заголовки через запятую
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.newsDao().insertNews(it.articles)
            },
                {
                    it.message?.let { it1 -> Log.d("TEST_OF_LOADING", it1) }
                })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}