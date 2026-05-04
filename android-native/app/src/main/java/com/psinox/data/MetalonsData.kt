package com.psinox.data

data class Metalon(
    val id: String,
    val descricao: String,
    val peso: Double,
    val unidade: String = "kg/m"
)

val metalonsData = listOf(
    Metalon("m-10x10-1.0", "Metalon 10x10 x 1.00mm", 0.28),
    Metalon("m-10x10-1.2", "Metalon 10x10 x 1.20mm", 0.33),
    Metalon("m-15x15-1.0", "Metalon 15x15 x 1.00mm", 0.41),
    Metalon("m-15x15-1.2", "Metalon 15x15 x 1.20mm", 0.48),
    Metalon("m-15x15-1.5", "Metalon 15x15 x 1.50mm", 0.58),
    Metalon("m-20x20-1.0", "Metalon 20x20 x 1.00mm", 0.55),
    Metalon("m-20x20-1.2", "Metalon 20x20 x 1.20mm", 0.65),
    Metalon("m-20x20-1.5", "Metalon 20x20 x 1.50mm", 0.78),
    Metalon("m-20x20-2.0", "Metalon 20x20 x 2.00mm", 1.01),
    Metalon("m-25x25-1.0", "Metalon 25x25 x 1.00mm", 0.70),
    Metalon("m-25x25-1.2", "Metalon 25x25 x 1.20mm", 0.82),
    Metalon("m-25x25-1.5", "Metalon 25x25 x 1.50mm", 0.99),
    Metalon("m-25x25-2.0", "Metalon 25x25 x 2.00mm", 1.28),
    Metalon("m-30x30-1.2", "Metalon 30x30 x 1.20mm", 0.99),
    Metalon("m-30x30-1.5", "Metalon 30x30 x 1.50mm", 1.20),
    Metalon("m-30x30-2.0", "Metalon 30x30 x 2.00mm", 1.56)
    // ...adicione o restante conforme necessário
)
