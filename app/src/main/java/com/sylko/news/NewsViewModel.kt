package com.sylko.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sylko.news.api.ApiFactory
import com.sylko.news.database.AppDatabase
import com.sylko.news.pojo.News
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val allNews = db.newsDao().getAllNews()

    init {
        loadData()
    }

    fun getDetailNews(url: String): LiveData<News>{
        return db.newsDao().getSelectedNews(url)
    }

    fun update(){
        loadData()
    }

    private fun loadData() {
        val disposable = ApiFactory.apiService.getArticles("b6869a57147642bf8ef94a7c6e3ed9b9")
            .delaySubscription(10,TimeUnit.MINUTES)//десятиминутный интервал загрузки
            .repeat()//-автозагрузка/автообновление, работает до тех пор, пока все загружается успешно.
            .retry()//-здесь загружка продолжается даже после ошибки
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