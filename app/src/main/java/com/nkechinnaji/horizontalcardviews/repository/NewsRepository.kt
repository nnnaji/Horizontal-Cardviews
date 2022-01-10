package com.nkechinnaji.horizontalcardviews.repository

import com.nkechinnaji.horizontalcardviews.service.NewsApiInterface


class NewsRepository constructor(private val newsApiInterface: NewsApiInterface) {

    suspend fun getNews() = newsApiInterface.getHeadlineNews(
        apiKey = "",
        country = "us",
        category = "entertainment"
    )
}