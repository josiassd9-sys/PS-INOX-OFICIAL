package com.psinox.data

data class Perfil(
    val nome: String,
    val peso: Double,
    val h: Double,
    val b: Double,
    val tw: Double,
    val tf: Double,
    val Ix: Double,
    val Wx: Double,
    val rx: Double,
    val Iy: Double,
    val Wy: Double,
    val ry: Double
)

val perfisData = listOf(
    Perfil("W200", 25.4, 200.0, 100.0, 6.0, 8.0, 1234.0, 234.0, 4.5, 567.0, 89.0, 3.2),
    Perfil("W250", 32.1, 250.0, 125.0, 7.0, 9.0, 2345.0, 345.0, 5.1, 678.0, 101.0, 3.8)
    // Adicione os demais perfis conforme necessário
)
