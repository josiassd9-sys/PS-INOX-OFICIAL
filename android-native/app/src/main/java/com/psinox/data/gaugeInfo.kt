package com.psinox.data

data class GaugeInfo(
    val id: Int,
    val valor: Double,
    val unidade: String
)

val gaugeInfo = listOf(
    GaugeInfo(1, 12.5, "mm"),
    GaugeInfo(2, 8.0, "mm"),
    GaugeInfo(3, 20.0, "mm")
)
