package com.psinox.data

data class TipoAco(
    val nome: String,
    val fy: Double // Limite de escoamento em MPa
)

val tiposAco = listOf(
    TipoAco("Aço 235", 235.0),
    TipoAco("Aço 355", 355.0)
)
