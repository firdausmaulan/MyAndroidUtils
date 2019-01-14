package com.myandroid.utils.dataSource.remote.repository

import com.myandroid.utils.collections.Constants
import com.myandroid.utils.dataSource.remote.ApiService
import com.myandroid.utils.model.news.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val service = ApiService.create()

    fun getNewsData(page: Int?, query: String?, callback: RepositoryCallback<News>) {
        service.getNewsData(Constants.apiKey, Constants.country, page, query)
            .enqueue(object : Callback<News> {
                override fun onResponse(call: Call<News>?, response: Response<News>?) {
                    response?.body()?.let { callback.onDataLoaded(it) }
                }

                override fun onFailure(call: Call<News>?, t: Throwable?) {
                    callback.onDataError(t?.message.toString())
                }
            })
    }
}