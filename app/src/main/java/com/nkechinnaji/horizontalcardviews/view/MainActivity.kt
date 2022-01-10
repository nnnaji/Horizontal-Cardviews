package com.nkechinnaji.horizontalcardviews.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nkechinnaji.horizontalcardviews.base.Status
import com.nkechinnaji.horizontalcardviews.base.ViewModelFactory
import com.nkechinnaji.horizontalcardviews.databinding.ActivityMainBinding
import com.nkechinnaji.horizontalcardviews.model.Articles
import com.nkechinnaji.horizontalcardviews.modelview.NewsApiViewModel
import com.nkechinnaji.horizontalcardviews.repository.NewsRepository
import com.nkechinnaji.horizontalcardviews.service.NewsApiInterface
import com.nkechinnaji.horizontalcardviews.service.RetrofitClient
import com.nkechinnaji.horizontalcardviews.utils.FormatUtils
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var newsAdapter: NewsAdapter? = null

    private lateinit var viewModel: NewsApiViewModel
    private var articlesList = ArrayList<Articles>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpViewModel()
        setUpUI()
        setUpObserver()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory( NewsRepository(
                RetrofitClient.retrofitClient()
                    .create(NewsApiInterface::class.java)
            ))
        )
            .get(NewsApiViewModel::class.java)
    }


    private fun setUpUI() {
        val horizontalLayoutManager = LinearLayoutManager(
            this@MainActivity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.recyclerView.layoutManager = horizontalLayoutManager
        newsAdapter = articlesList.let { NewsAdapter(it) }

        binding.recyclerView.adapter = newsAdapter

    }

    private fun setUpObserver() {
        viewModel.getNews().observe(this, Observer {
            it?.let{
                resources ->
                when(resources.status) {
                    Status.SUCCESS ->{
                        resources.data?.articles.let{
                            articles -> getArticles(articles)
                        }
                    }
                }
            }
        })
    }

    private fun getArticles(articles: List<Articles>?){
        if(articles?.size != 0){
            if (articles != null) {
                articlesList.addAll(articles)
                val dayTime = FormatUtils.formatDateWithDayOnly(articlesList[0].publishedAt)
                binding.headlineLabel.text = "Trending News... ${dayTime}"
                newsAdapter?.notifyDataSetChanged()
            }
        }

    }
}