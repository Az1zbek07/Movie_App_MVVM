package com.example.movieappmvvp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieappmvvp.databinding.MovieLayoutBinding
import com.example.movieappmvvp.model.Result

class MovieAdapter: ListAdapter<com.example.movieappmvvp.model.Result, MovieAdapter.VHolder>(DiffCallback()) {
    lateinit var onClick: (result: com.example.movieappmvvp.model.Result) -> Unit
    private class DiffCallback: DiffUtil.ItemCallback<com.example.movieappmvvp.model.Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(MovieLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VHolder(private val binding: MovieLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(result: com.example.movieappmvvp.model.Result){
            binding.textView.text = result.title
            with(binding){
                Glide.with(imageView).load("https://image.tmdb.org/t/p/w500/${result.backdrop_path}").into(imageView)
            }
            itemView.setOnClickListener {
                onClick(result)
            }
        }
    }
}