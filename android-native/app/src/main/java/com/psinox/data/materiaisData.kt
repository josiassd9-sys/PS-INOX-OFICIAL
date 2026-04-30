package com.psinox.data

data class Material(
    val id: Int,
    val nome: String,
    val tipo: String
)

val materiaisData = listOf(
    Material(1, "Aço Inox 304", "Chapa"),
    Material(2, "Aço Inox 316", "Barra"),
    Material(3, "Alumínio", "Tubo")
)
