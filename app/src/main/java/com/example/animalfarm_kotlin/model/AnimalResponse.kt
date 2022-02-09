package com.example.animalfarm_kotlin.model

import com.google.gson.annotations.SerializedName

data class AnimalResponse(
    @SerializedName("animals")
    val animals: List<Animal>
)