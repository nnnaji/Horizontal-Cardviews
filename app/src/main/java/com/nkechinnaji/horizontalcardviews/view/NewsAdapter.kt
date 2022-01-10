package com.nkechinnaji.horizontalcardviews.view

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.nkechinnaji.horizontalcardviews.R
import com.nkechinnaji.horizontalcardviews.model.Articles
import com.nkechinnaji.horizontalcardviews.utils.FormatUtils.formatDateWithDateOnly
import com.squareup.picasso.Picasso

class NewsAdapter(var articles: List<Articles?>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(layoutInflater.inflate(R.layout.sliding_cardview, parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val itemPosition = articles.get(position)
        holder.author.text = itemPosition?.author
        holder.desc.text = itemPosition?.description
        holder.title.text = itemPosition?.title
        holder.source.text = "Source: ${itemPosition?.source?.name}"

        val snapShotTime = formatDateWithDateOnly(itemPosition?.publishedAt)
        holder.snapshotTime.text = "Time: ${snapShotTime}"
        Picasso.get().load(itemPosition?.urlToImage).noFade().into(holder.backgroundImage)
    }

    override fun getItemCount(): Int {
        return articles.size
    }


    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var author = view.findViewById<AppCompatTextView>(R.id.author)
        var title = view.findViewById<AppCompatTextView>(R.id.title)
        var desc = view.findViewById<AppCompatTextView>(R.id.desc)
        var source = view.findViewById<AppCompatTextView>(R.id.source)
        var snapshotTime = view.findViewById<AppCompatTextView>(R.id.snapshot_time)
        var backgroundImage = view.findViewById<AppCompatImageView>(R.id.img)

    }


}