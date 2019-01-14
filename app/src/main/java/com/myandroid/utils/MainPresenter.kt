package com.myandroid.utils

import com.myandroid.utils.dataSource.remote.repository.Repository
import com.myandroid.utils.dataSource.remote.repository.RepositoryCallback
import com.myandroid.utils.model.news.News

class MainPresenter internal constructor(
        private val view: MainView,
        private val repository: Repository
) {

    fun getNewsData(page: Int?, query: String?) {
        view.showLoading()

        repository.getNewsData(page, query, object : RepositoryCallback<News> {
            override fun onDataLoaded(response: News) {
                view.showNews(response.articles)
                view.hideLoading()
            }

            override fun onDataError(error: String?) {
                view.hideLoading()
            }
        })
    }
}
