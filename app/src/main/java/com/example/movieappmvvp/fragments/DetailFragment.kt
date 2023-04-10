package com.example.movieappmvvp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieappmvvp.R
import com.example.movieappmvvp.databinding.FragmentDetailBinding
import com.example.movieappmvvp.viewmodel.MainVewModelFactory
import com.example.movieappmvvp.viewmodel.MainViewModel

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity(), MainVewModelFactory(requireActivity().application))[MainViewModel::class.java]
        val movie = arguments?.getParcelable<com.example.movieappmvvp.model.Result>("result")!!

        with(binding){
            Glide.with(imageView).load("https://image.tmdb.org/t/p/w500/${movie.backdrop_path}").into(imageView)
            textDesc.text = movie.overview
            textTitle.text = movie.title
        }

        binding.btnSave.setOnClickListener {
            viewModel.saveMovie(movie)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}