package com.psinox.data

data class Chapa(
    val id: String,
    val descricao: String,
    val peso: Double,
    val unidade: String = "kg/un"
)

val chapasData = listOf(
    Chapa("chapa-0.40-1250x2000", "Chapa Inox 0.40x1250x2000 mm", 7.9),
    Chapa("chapa-0.50-1250x2000", "Chapa Inox 0.50x1250x2000 mm", 9.9),
    Chapa("chapa-0.60-1250x2000", "Chapa Inox 0.60x1250x2000 mm", 11.9),
    Chapa("chapa-0.80-1250x2000", "Chapa Inox 0.80x1250x2000 mm", 15.8),
    Chapa("chapa-1.00-1250x2000", "Chapa Inox 1.00x1250x2000 mm", 19.8),
    Chapa("chapa-1.20-1250x2000", "Chapa Inox 1.20x1250x2000 mm", 23.8),
    Chapa("chapa-1.50-1250x2000", "Chapa Inox 1.50x1250x2000 mm", 29.7),
    Chapa("chapa-2.00-1250x2000", "Chapa Inox 2.00x1250x2000 mm", 39.6),
    Chapa("chapa-2.50-1250x2000", "Chapa Inox 2.50x1250x2000 mm", 49.5),
    Chapa("chapa-3.00-1250x2000", "Chapa Inox 3.00x1250x2000 mm", 59.4)
    // ...adicione o restante conforme necessário
)
