package com.example.weber.githubusersdemo.network

import kotlinx.coroutines.*

fun <T> request(
    scope: CoroutineScope,
    callback: RequestCallback<T>? = null,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    handler: CoroutineExceptionHandler? = null,
    block: suspend CoroutineScope.() -> T
): Job = scope.launch(dispatcher) {
    try {
        val result = block()
        withContext(Dispatchers.Main) {
            callback?.onSuccess(ResponseSuccess(result))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        withContext(Dispatchers.Main) {
            handler?.handleException(this.coroutineContext, e)
            callback?.onError(ResponseError(e))
        }
    }
}

interface RequestCallback<T> {
    fun onSuccess(result: ResponseSuccess<T>)
    fun onError(error: ResponseError)
}