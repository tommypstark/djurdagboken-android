package com.example.djurdagboken

data class Animal(
    val name: String,
    val species: String,
    val isMale: Boolean = true,
    val isCaredForToday: Boolean = false
)