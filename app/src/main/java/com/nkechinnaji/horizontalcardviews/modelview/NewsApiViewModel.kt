package com.nkechinnaji.horizontalcardviews.modelview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nkechinnaji.horizontalcardviews.base.LoadingState
import com.nkechinnaji.horizontalcardviews.model.Articles
import com.nkechinnaji.horizontalcardviews.repository.NewsRepository
import kotlinx.coroutines.Dispatchers

class NewsApiViewModel(private val apiService: NewsRepository): ViewModel() {


    fun getNews() = liveData(Dispatchers.IO) {
        emit(LoadingState.loading(data = null))
        try{
            emit(LoadingState.success(data = apiService.getNews()))
        }catch(exception: Exception){
            emit(LoadingState.error(data = null, message = exception.message?: "Error Occurred"))
        }
    }

}