package com.myandroid.utils

import android.support.v7.util.DiffUtil

import com.myandroid.utils.model.news.Article

class ArticleDiffCallback(
        private val oldList: List<Article>,
        private val newList: List<Article>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].url == newList[newItemPosition].url
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

}