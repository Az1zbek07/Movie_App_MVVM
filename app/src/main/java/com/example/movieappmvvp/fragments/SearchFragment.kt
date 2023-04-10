package com.example.movieappmvvp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappmvvp.R
import com.example.movieappmvvp.adapter.MovieAdapter
import com.example.movieappmvvp.databinding.FragmentSearchBinding
import com.example.movieappmvvp.viewmodel.MainVewModelFactory
import com.example.movieappmvvp.viewmodel.MainViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private val movieAdapter by lazy { MovieAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity(), MainVewModelFactory(requireActivity().application))[MainViewModel::class.java]

        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = movieAdapter
        }

        binding.btnSearch.setOnClickListener {
            val text = binding.editText.text.toString().trim()

            if (text.isNotBlank()){
                viewModel.searchMovie(text).observe(viewLifecycleOwner){
                    movieAdapter.submitList(it)
                }
            }
        }

        movieAdapter.onClick = {
            val bundle = bundleOf("result" to it)
            findNavController().navigate(R.id.action_searchFragment_to_detailFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}