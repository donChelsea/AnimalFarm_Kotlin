package com.example.animalfarm_kotlin.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalfarm_kotlin.model.Animal
import com.example.animalfarm_kotlin.network.AnimalsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val animalsApi: AnimalsApi
): ViewModel() {

    private val _animals = MutableLiveData<List<Animal>>()
    val animals: LiveData<List<Animal>> = _animals

    suspend fun getAnimals() {
        viewModelScope.launch {
            val response = animalsApi.getAnimals()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.animals.let {
                        if (it != null) {
                            _animals.value = it
                            Log.d("MainViewModel", it.toString())
                        }
                    }
                }
            }
        }
    }
}