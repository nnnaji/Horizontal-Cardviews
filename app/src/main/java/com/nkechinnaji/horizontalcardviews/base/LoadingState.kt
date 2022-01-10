package com.nkechinnaji.horizontalcardviews.base

data class LoadingState<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T): LoadingState<T> =
            LoadingState(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String?): LoadingState<T> =
            LoadingState(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): LoadingState<T> =
            LoadingState(status = Status.LOADING, data = data, message = null)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
