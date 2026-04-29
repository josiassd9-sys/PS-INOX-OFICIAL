package com.psinox.data

data class GaugeInfo(
    val valor: Float = 0f,
    val descricao: String = ""
)

val gaugeInfoMock = GaugeInfo(
    valor = 75.5f,
    descricao = "Mock do Gauge - valor padrão"
)
