package com.psinox.data

data class ConfigFerramenta(
    val id: Int,
    val nome: String,
    val valor: String
)

val configuracoesFerramentasData = listOf(
    ConfigFerramenta(1, "Markup padrão (%)", "50"),
    ConfigFerramenta(2, "Preço base do kg sucata (R$)", "7.50"),
    ConfigFerramenta(3, "Unidade padrão de material", "kg/m")
)
