package com.psinox.data

data class TuboODSchedule(
    val id: Int,
    val odMm: Double,
    val schedule: String,
    val espessuraMm: Double,
    val pesoKgM: Double
)

val tubosODScheduleData = listOf(
    TuboODSchedule(1, 21.3, "SCH10S", 2.11, 1.09),
    TuboODSchedule(2, 21.3, "SCH40S", 2.77, 1.38),
    TuboODSchedule(3, 26.7, "SCH10S", 2.11, 1.39),
    TuboODSchedule(4, 26.7, "SCH40S", 2.87, 1.83),
    TuboODSchedule(5, 33.4, "SCH10S", 2.77, 2.21),
    TuboODSchedule(6, 33.4, "SCH40S", 3.38, 2.65)
)

// Metalons

data class Metalon(
    val id: Int,
    val larguraMm: Double,
    val alturaMm: Double,
    val espessuraMm: Double,
    val pesoKgM: Double
)

val metalonsData = listOf(
    Metalon(1, 20.0, 20.0, 1.2, 0.68),
    Metalon(2, 30.0, 20.0, 1.2, 0.82),
    Metalon(3, 40.0, 20.0, 1.5, 1.18)
)

// Chapas

data class Chapa(
    val id: Int,
    val espessuraMm: Double,
    val larguraMm: Double,
    val comprimentoMm: Double,
    val pesoKg: Double
)

val chapasData = listOf(
    Chapa(1, 0.8, 1000.0, 2000.0, 12.6),
    Chapa(2, 1.2, 1000.0, 2000.0, 18.9),
    Chapa(3, 2.0, 1000.0, 2000.0, 31.5)
)
