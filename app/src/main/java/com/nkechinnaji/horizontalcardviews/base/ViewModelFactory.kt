package com.nkechinnaji.horizontalcardviews.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nkechinnaji.horizontalcardviews.modelview.NewsApiViewModel
import com.nkechinnaji.horizontalcardviews.repository.NewsRepository
import com.nkechinnaji.horizontalcardviews.service.NewsApiInterface

class ViewModelFactory (private val apiService: NewsRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(NewsApiViewModel::class.java)){
            return NewsApiViewModel(apiService) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }
}