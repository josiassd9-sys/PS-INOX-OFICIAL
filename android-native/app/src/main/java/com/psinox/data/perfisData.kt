package com.psinox.data

data class Perfil(
    val id: Int,
    val nome: String,
    val descricao: String
)

val perfisData = listOf(
    Perfil(1, "Perfil A", "Descrição do Perfil A"),
    Perfil(2, "Perfil B", "Descrição do Perfil B"),
    Perfil(3, "Perfil C", "Descrição do Perfil C")
)
