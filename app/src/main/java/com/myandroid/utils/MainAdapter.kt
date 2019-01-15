package com.myandroid.utils

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.myandroid.utils.collections.ImageUtil
import com.myandroid.utils.model.news.Article
import kotlinx.android.synthetic.main.adapter_main.view.*
import android.support.v7.util.DiffUtil
import com.myandroid.utils.listener.OnItemClickListener
import com.myandroid.utils.listener.OnItemDeleteListener

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val listArticle = mutableListOf<Article>()

    // Click Listener
    private var clickListener: OnItemClickListener<Article>? = null

    fun setClickListener(clickListener: OnItemClickListener<Article>) {
        this.clickListener = clickListener
    }

    // Delete Listener
    private var deleteListener: OnItemDeleteListener<Article>? = null

    fun setDeleteListener(deleteListener: OnItemDeleteListener<Article>) {
        this.deleteListener = deleteListener
    }

    fun addList(articles: List<Article>) {
        for (i in 0 until articles.size) {
            listArticle.add(articles[i])
            notifyItemInserted(listArticle.size - 1)
        }
    }

    fun updateList(articles: List<Article>) {
        val diffCallback = ArticleDiffCallback(this.listArticle, articles)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listArticle.clear()
        this.listArticle.addAll(articles)
        diffResult.dispatchUpdatesTo(this)
    }

    fun delete(article: Article, position: Int) {
        listArticle.remove(article)
        notifyItemRemoved(position)
    }

    fun clearList() {
        this.listArticle.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val view = inflater.inflate(R.layout.adapter_main, viewGroup, false)
        return ViewHolder(view) // this adapter view holder inner class
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var ivNews: ImageView? = itemView.ivNews
        var tvTitle: TextView? = itemView.tvTitle
        var ivDelete: ImageView? = itemView.ivDelete

        fun bind(article: Article) {
            ImageUtil.thumbLoad(article.urlToImage, ivNews)
            tvTitle?.text = article.title

            itemView.setOnClickListener {
                clickListener?.onItemClick(article)
            }

            ivDelete?.setOnClickListener {
                deleteListener?.onItemDelete(article, adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(article = listArticle[position])
    }

    override fun getItemCount() = listArticle.size
}