package com.example.djurdagboken

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*

class AnimalViewModel : ViewModel() {

    var uiState by mutableStateOf(
        AnimalUiState(
            animals = sampleAnimals
        )
    )
        private set

    fun addAnimal(animal: Animal) {
        uiState = uiState.copy(
            animals = uiState.animals + animal
        )
    }
}