package com.myandroid.utils

import android.annotation.SuppressLint
import android.graphics.Color
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
    private lateinit var mainAdapter: MainAdapter

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
        setRecyclerView()
        mainAdapter = MainAdapter()
        recyclerView.adapter = mainAdapter
        recyclerView.addOnScrollListener(scrollListener())
    }

    private fun setRecyclerView() {
        refreshLayout.setColorSchemeColors(Color.WHITE, Color.WHITE)
        refreshLayout.setWaveColor(Color.argb(255, 33, 150, 243))
        refreshLayout.setMaxDropHeight(75)
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
                            mainAdapter.clearList()
                            pageNumber = 1
                            presenter.getNewsData(pageNumber, searchQuery)
                            setRecyclerView()
                        }
                    }
                }

        refreshLayout.setOnRefreshListener {
            mainAdapter.clearList()
            pageNumber = 1
            presenter.getNewsData(pageNumber, searchQuery)
            setRecyclerView()
        }

        mainAdapter.setClickListener(object : OnItemClickListener<Article> {
            override fun onItemClick(item: Article) {
                openNewsOnBrowser(item.url)
            }
        })

        mainAdapter.setDeleteListener(object : OnItemDeleteListener<Article> {
            override fun onItemDelete(item: Article, position: Int) {
                mainAdapter.delete(item, position)
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
        mainAdapter.addList(articles)
        Log.d("list size", mainAdapter.itemCount.toString())
    }
}
