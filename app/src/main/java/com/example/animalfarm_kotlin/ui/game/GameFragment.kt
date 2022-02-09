package com.example.animalfarm_kotlin.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.animalfarm_kotlin.R
import com.example.animalfarm_kotlin.databinding.FragmentGameBinding
import com.example.animalfarm_kotlin.model.Animal
import com.example.animalfarm_kotlin.viewmodels.MainViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val gameViewModel: GameViewModel by viewModels()

    private val random = Random()
    private var imageViews = mutableListOf<ImageView>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        binding.apply {
            imageViews = mutableListOf(buttonOne, buttonTwo, buttonThree)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.animals.observe(viewLifecycleOwner) { animals ->
            loadGameImages(animals)
        }

        gameViewModel.currentTime.observe(viewLifecycleOwner) { time ->
            binding.timerText.text = time.toString()
        }


        gameViewModel.isGameFinished.observe(viewLifecycleOwner) { gameFinished ->
            if (gameFinished) {
                view.findNavController().navigate(R.id.action_gameFragment_to_lostFragment)
            }
        }
    }

    private fun loadGameImages(animals: List<Animal>) {
        val randomIndex = random.nextInt(animals.size)
        binding.constraintLayout.setBackgroundColor(gameViewModel.getRandomColor(requireActivity().applicationContext))
        val winner = animals[randomIndex]
        binding.animalNameText.text = winner.name
        for (i in imageViews.indices) {
            Picasso.get().load(animals[i].image).into(imageViews[i])
            if (animals[i] === winner) {
                imageViews[i].setOnClickListener {
                    correctGuess(animals)
                    gameViewModel.correctPicks++
                    if (gameViewModel.correctPicks == 3) {
                        gameWon()
                    }
                }
            } else {
                imageViews[i].setOnClickListener { gameLost() }
            }
        }
    }

    private fun gameWon() {
        view?.findNavController()?.navigate(R.id.action_gameFragment_to_wonFragment)
    }

    private fun correctGuess(animals: List<Animal>) {
        loadGameImages(animals)
    }

    private fun gameLost() {
        view?.findNavController()?.navigate(R.id.action_gameFragment_to_lostFragment)
    }
}