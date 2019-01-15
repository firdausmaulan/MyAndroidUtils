package com.myandroid.utils

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jakewharton.rxbinding2.widget.RxTextView
import com.myandroid.utils.collections.EndlessOnScrollListener
import com.myandroid.utils.collections.KeyboardUtil
import com.myandroid.utils.dataSource.remote.repository.Repository
import com.myandroid.utils.listener.OnItemClickListener
import com.myandroid.utils.listener.OnItemDeleteListener
import com.myandroid.utils.model.news.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(), MainView {

    private lateinit var presenter: MainPresenter

    // Data
    private var pageNumber = 1
    private var searchQuery: String? = null

    // View
    private lateinit var adapterMain: AdapterMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this, Repository())

        initData()
        initView()
        initAction()
    }

    private fun initData() {
        presenter.getNewsData(pageNumber, searchQuery)
    }

    private fun initView() {
        adapterMain = AdapterMain()
        recyclerView.adapter = adapterMain
        recyclerView.addOnScrollListener(scrollListener())
    }

    @SuppressLint("CheckResult")
    private fun initAction() {
        RxTextView.textChanges(etSearch)
                .skip(1)
                .debounce(1, TimeUnit.SECONDS)
                .map { charSequence -> charSequence.toString() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { query ->
                    searchQuery = query
                    searchQuery?.let {
                        if (it.isEmpty() || it.length > 2) {
                            adapterMain.clearList()
                            pageNumber = 1
                            presenter.getNewsData(pageNumber, searchQuery)
                            recyclerView.addOnScrollListener(scrollListener())
                        }
                    }
                }

        refreshLayout.setOnRefreshListener {
            adapterMain.clearList()
            pageNumber = 1
            presenter.getNewsData(pageNumber, searchQuery)
            recyclerView.addOnScrollListener(scrollListener())
        }

        adapterMain.setClickListener(object : OnItemClickListener<Article> {
            override fun onItemClick(item: Article) {
                openNewsOnBrowser(item.url)
            }
        })

        adapterMain.setDeleteListener(object : OnItemDeleteListener<Article> {
            override fun onItemDelete(item: Article, position: Int) {
                adapterMain.delete(item, position)
            }
        })

        KeyboardUtil.hideKeyboardOnScroll(recyclerView)
    }

    private fun scrollListener(): EndlessOnScrollListener {
        return object : EndlessOnScrollListener() {
            override fun onLoadMore() {
                Log.d("onLoadMore", "true")
                pageNumber++
                presenter.getNewsData(pageNumber, searchQuery)
            }
        }
    }

    override fun showLoading() {
        refreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        refreshLayout.isRefreshing = false
    }

    override fun showNews(articles: List<Article>) {
        adapterMain.addList(articles)
        Log.d("list size", adapterMain.itemCount.toString())
    }
}
