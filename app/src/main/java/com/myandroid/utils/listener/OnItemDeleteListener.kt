package com.myandroid.utils.listener

interface OnItemDeleteListener<T> {
    fun onItemDelete(item: T, position: Int)
}