package com.psinox.data

data class TuboOD(
    val id: String,
    val descricao: String,
    val peso: Double,
    val unidade: String = "kg/m"
)

val tubosODData = listOf(
    TuboOD("t-1/4-0.70", "Tubo OD 1/4\" 6.35x0.70", 0.032),
    TuboOD("t-1/4-1.00", "Tubo OD 1/4\" 6.35x1.00", 0.037),
    TuboOD("t-1/4-1.20", "Tubo OD 1/4\" 6.35x1.20", 0.040),
    TuboOD("t-1/4-1.50", "Tubo OD 1/4\" 6.35x1.50", 0.045),
    TuboOD("t-3/8-0.70", "Tubo OD 3/8\" 9.52x0.70", 0.049),
    TuboOD("t-3/8-1.00", "Tubo OD 3/8\" 9.52x1.00", 0.057),
    TuboOD("t-3/8-1.20", "Tubo OD 3/8\" 9.52x1.20", 0.061),
    TuboOD("t-3/8-1.50", "Tubo OD 3/8\" 9.52x1.50", 0.068),
    TuboOD("t-1/2-1.00", "Tubo OD 1/2\" 12.70x1.00", 0.077),
    TuboOD("t-1/2-1.20", "Tubo OD 1/2\" 12.70x1.20", 0.082),
    TuboOD("t-1/2-1.50", "Tubo OD 1/2\" 12.70x1.50", 0.089),
    TuboOD("t-5/8-1.00", "Tubo OD 5/8\" 15.88x1.00", 0.097),
    TuboOD("t-5/8-1.20", "Tubo OD 5/8\" 15.88x1.20", 0.103),
    TuboOD("t-5/8-1.50", "Tubo OD 5/8\" 15.88x1.50", 0.112),
    TuboOD("t-3/4-1.00", "Tubo OD 3/4\" 19.05x1.00", 0.117),
    TuboOD("t-3/4-1.20", "Tubo OD 3/4\" 19.05x1.20", 0.124),
    TuboOD("t-3/4-1.50", "Tubo OD 3/4\" 19.05x1.50", 0.134)
    // ...adicione o restante conforme necessário
)
