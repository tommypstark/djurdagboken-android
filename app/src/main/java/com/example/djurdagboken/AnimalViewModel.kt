package com.example.djurdagboken

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*

class AnimalViewModel : ViewModel() {

    var uiState by mutableStateOf(
        AnimalUiState(
            animals = sampleAnimals
        )
    )
        private set
}