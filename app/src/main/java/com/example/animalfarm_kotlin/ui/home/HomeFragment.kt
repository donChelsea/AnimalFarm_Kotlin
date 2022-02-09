package com.example.animalfarm_kotlin.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.animalfarm_kotlin.R
import com.example.animalfarm_kotlin.databinding.FragmentHomeBinding
import com.example.animalfarm_kotlin.ui.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.animals.observe(viewLifecycleOwner) {
            Log.d("HomeFragment", it.toString())
        }

        binding.playBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_gameFragment)
        }
    }

}