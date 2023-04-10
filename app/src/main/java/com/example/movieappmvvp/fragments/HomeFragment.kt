package com.example.movieappmvvp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappmvvp.R
import com.example.movieappmvvp.adapter.MovieAdapter
import com.example.movieappmvvp.databinding.FragmentHomeBinding
import com.example.movieappmvvp.viewmodel.MainVewModelFactory
import com.example.movieappmvvp.viewmodel.MainViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val movieAdapter by lazy { MovieAdapter() }
    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity(), MainVewModelFactory(requireActivity().application))[MainViewModel::class.java]

        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = movieAdapter
        }
        viewModel.getAllMovies().observe(viewLifecycleOwner){
            movieAdapter.submitList(it)
        }

        movieAdapter.onClick = {
            val bundle = bundleOf("result" to it)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }

        binding.btnSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        binding.btnLiked.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_likedFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}