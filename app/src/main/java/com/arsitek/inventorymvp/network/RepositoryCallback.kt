package com.arsitek.inventorymvp.network

interface RepositoryCallback<T> {
    fun onDataLoaded(data: List<T>?)
    fun onDataError()
}