package com.psinox.data

data class Material(
    val nome: String,
    val quantidade: Double,
    val unidade: String
)

val materiaisData = listOf(
    Material("Chapa Inox 304", 10.0, "m²"),
    Material("Barra Redonda 20mm", 5.0, "m"),
    Material("Tubo 2"", 12.0, "m")
    // Adicione mais materiais conforme necessário
)
