package com.example.flags.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.flags.PhotoAdapter
import com.example.flags.databinding.FragmentOverViewBinding

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOverViewBinding.inflate(inflater)
        binding.animationView.repeatCount = 1

        viewModel.status.observe(this.viewLifecycleOwner) { status ->
            when (status) {
                ApiStatus.LOADING -> binding.animationView.visibility = View.VISIBLE
                else -> binding.animationView.visibility = View.GONE
            }
        }

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.photosGrid.adapter = PhotoAdapter()

        return binding.root
    }
}