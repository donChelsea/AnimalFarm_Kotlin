package com.example.animalfarm_kotlin.network

import com.example.animalfarm_kotlin.model.AnimalResponse
import retrofit2.Response
import retrofit2.http.GET

interface AnimalsApi {
    @GET("/donChelsea/6619c69def9a1933ed4665c28dbf8452/raw/21557f6ece03b79fbf4ffba2f54e95f3e37cb563/animals2.json")
    fun getAnimals(): Response<AnimalResponse>

    companion object {
        const val BASE_URL = "https://gist.githubusercontent.com"
    }
}