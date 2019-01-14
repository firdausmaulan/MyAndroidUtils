package com.myandroid.utils.dataSource.remote.repository

// Required for Unit Test
interface RepositoryCallback<T> {
    fun onDataLoaded(response: T)
    fun onDataError(error: String?)
}