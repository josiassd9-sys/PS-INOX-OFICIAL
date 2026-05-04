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

// ...dados de metalons migrados para MetalonsData.kt
// ...dados de chapas migrados para ChapasData.kt
