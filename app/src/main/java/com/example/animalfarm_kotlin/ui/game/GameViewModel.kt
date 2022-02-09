package com.example.animalfarm_kotlin.ui.game

import android.content.Context
import android.content.res.TypedArray
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animalfarm_kotlin.R
import com.example.animalfarm_kotlin.model.Animal
import dagger.hilt.android.internal.Contexts.getApplication
import java.util.*

class GameViewModel : ViewModel() {

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long> = _currentTime

    private val _isGameFinished = MutableLiveData<Boolean>()
    val isGameFinished: LiveData<Boolean> = _isGameFinished

    private val rand = Random()
    private val timer: CountDownTimer
    var correctPicks = 0

    init {
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / ONE_SECOND
                Log.d("vm", _currentTime.value.toString())
            }

            override fun onFinish() {
                _currentTime.value = GameViewModel.DONE
                _isGameFinished.value = true
            }
        }
        timer.start()

        _isGameFinished.value = false
    }

    fun getRandomAnimals(animals: MutableList<Animal>): MutableList<Animal> {
        val newList = mutableListOf<Animal>()
        repeat(3) {
            val randomIndex = rand.nextInt(animals.size)
            newList.add(animals[randomIndex])
            animals.removeAt(randomIndex)
        }
        return newList
    }

    fun getRandomColor(context: Context): Int {
        val colorArray: TypedArray =
            getApplication(context).resources.obtainTypedArray(R.array.rainbow)
        val rainbow = IntArray(colorArray.length())
        for (i in 0 until colorArray.length()) {
            rainbow[i] = colorArray.getColor(i, 0)
        }
        colorArray.recycle()
        val randomIndex: Int = rand.nextInt(rainbow.size)
        return rainbow[randomIndex]
    }

    companion object {
        private const val DONE = 0L
        private const val ONE_SECOND = 1000L
        private const val COUNTDOWN_TIME = 6000L
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }


}