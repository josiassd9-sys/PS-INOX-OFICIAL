package com.psinox.data

data class DashboardInfo(
    val totalPerfis: Int,
    val totalMateriais: Int,
    val usuario: String
)

val dashboardInfoMock = DashboardInfo(
    totalPerfis = 2,
    totalMateriais = 3,
    usuario = "Usuário Demo"
)
