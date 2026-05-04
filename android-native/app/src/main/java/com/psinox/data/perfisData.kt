package com.psinox.data

data class Perfil(
    val id: Int,
    val nome: String,
    val descricao: String,
    val wxCm3: Double, // Módulo de resistência à flexão (cm³)
    val areaCm2: Double // Área da seção transversal (cm²)
)

val perfisData = listOf(
    Perfil(1, "Perfil A", "Descrição do Perfil A", 200.0, 20.0),
    Perfil(2, "Perfil B", "Descrição do Perfil B", 350.0, 35.0),
    Perfil(3, "Perfil C", "Descrição do Perfil C", 500.0, 50.0)
)
