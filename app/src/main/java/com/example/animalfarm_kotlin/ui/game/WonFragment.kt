package com.example.animalfarm_kotlin.ui.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.animalfarm_kotlin.R
import com.example.animalfarm_kotlin.databinding.FragmentHomeBinding
import com.example.animalfarm_kotlin.databinding.FragmentWonBinding

class WonFragment : Fragment() {
    private lateinit var binding: FragmentWonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            playAgainButton.setOnClickListener {
                view.findNavController().navigate(R.id.action_wonFragment_to_gameFragment)
            }

            homeButton.setOnClickListener {
                view.findNavController().navigate(R.id.action_wonFragment_to_homeFragment)
            }
        }
    }

}