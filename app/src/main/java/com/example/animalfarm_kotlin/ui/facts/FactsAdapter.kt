package com.example.animalfarm_kotlin.ui.facts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animalfarm_kotlin.R
import com.example.animalfarm_kotlin.databinding.AnimalListViewBinding
import com.example.animalfarm_kotlin.model.Animal
import com.squareup.picasso.Picasso

class FactsAdapter(private val animals: List<Animal>) :
    RecyclerView.Adapter<FactsAdapter.AnimalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        return AnimalViewHolder(
            AnimalListViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.bind(animals[position])
    }

    override fun getItemCount() = animals.size

    inner class AnimalViewHolder(private val binding: AnimalListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(animal: Animal) {
            binding.apply {
                nameTv.text = animal.name
                funFactText.text = animal.fact

                Picasso.get().load(animal.image).into(imageView)

                likeButton.setOnClickListener { v: View -> v.setBackgroundResource(R.drawable.heart_red) }
            }

        }
    }
}