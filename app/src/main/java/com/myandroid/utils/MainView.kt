package com.myandroid.utils

import com.myandroid.utils.model.news.Article

interface MainView {

    fun showLoading()

    fun hideLoading()

    fun showNews(articles: List<Article>)
}
