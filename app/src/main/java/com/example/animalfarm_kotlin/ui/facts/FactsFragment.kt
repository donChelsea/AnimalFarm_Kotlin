package com.example.animalfarm_kotlin.ui.facts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.animalfarm_kotlin.R
import com.example.animalfarm_kotlin.databinding.FragmentFactsBinding
import com.example.animalfarm_kotlin.model.Animal
import com.example.animalfarm_kotlin.viewmodels.MainViewModel
import kotlinx.coroutines.launch

class FactsFragment : Fragment() {
    private lateinit var binding: FragmentFactsBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFactsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.animals.observe(viewLifecycleOwner) { animals ->
            binding.recyclerView.adapter = FactsAdapter(animals)
        }

    }

}