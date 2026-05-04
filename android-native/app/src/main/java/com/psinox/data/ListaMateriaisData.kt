package com.psinox.data

data class MaterialItem(
    val id: Int,
    val descricao: String,
    val peso: Double,
    val unidade: String
)

val listaMateriaisData = listOf(
    MaterialItem(1, "Perfil W200x15", 15.0, "kg/m"),
    MaterialItem(2, "Tubo OD 21,3mm SCH10S", 1.09, "kg/m"),
    MaterialItem(3, "Chapa 1,2mm x 1000x2000mm", 18.9, "kg"),
    MaterialItem(4, "Metalon 20x20x1,2mm", 0.68, "kg/m")
)
