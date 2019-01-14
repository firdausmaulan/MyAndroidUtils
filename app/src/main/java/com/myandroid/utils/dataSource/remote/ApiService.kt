package com.myandroid.utils.dataSource.remote

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.http.*
import java.util.concurrent.TimeUnit
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.myandroid.utils.BaseApp
import com.myandroid.utils.BuildConfig
import com.myandroid.utils.model.news.News
import com.readystatesoftware.chuck.ChuckInterceptor
import retrofit2.converter.gson.GsonConverterFactory


interface ApiService {

    companion object Factory {
        fun create(): ApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://newsapi.org/v2/")

            val client = OkHttpClient.Builder()
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)

            if (BuildConfig.DEBUG) {
                client.networkInterceptors().add(ChuckInterceptor(BaseApp.context))
                client.networkInterceptors().add(StethoInterceptor())
            }

            return retrofit.client(client.build()).build().create(ApiService::class.java)
        }
    }

    @GET("top-headlines")
    fun getNewsData(
        @Query("apiKey") apiKey: String?,
        @Query("country") country: String?,
        @Query("page") page: Int?,
        @Query("q") query : String?
    ): Call<News>
}