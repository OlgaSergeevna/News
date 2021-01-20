package com.sylko.news.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sylko.news.R
import com.sylko.news.databinding.ItemNewsBinding
import com.sylko.news.pojo.News

class NewsAdapter(
    private  val newsList: List<News>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)

        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList[position]

        holder.title.text = news.title
        Picasso.get().load(news.urlToImage).into(holder.image)
    }

    override fun getItemCount() = newsList.size

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        private val binding = ItemNewsBinding.bind(view)

        val title = binding.tvTitle
        val image = binding.ivPicture
        private val cv = binding.root

        init {
            cv.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position : Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    fun getId(position: Int): String {
        return newsList[position].url
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}
