package com.example.assigment2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MyAdapter(val context: Context, val movieList: List<MovieDataItem>, private val listener: OnItemClickListener): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(movieView: View, private val listener: OnItemClickListener, private val movieList: List<MovieDataItem>): RecyclerView.ViewHolder(movieView) {
        var movieImage: ImageView
        var title: TextView

        init {
            movieImage = movieView.findViewById(R.id.movie_image)
            title = movieView.findViewById(R.id.title)

            movieView.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(movieList[position])
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false)
        return ViewHolder(itemView, listener, movieList)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]

        Glide.with(holder.itemView.context).load(movie.image)
            .into(holder.movieImage)
        holder.title.text = movie.title
    }
}