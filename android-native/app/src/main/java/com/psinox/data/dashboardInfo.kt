package com.psinox.data

data class DashboardInfo(
    val totalPerfis: Int,
    val totalMateriais: Int
)

val dashboardInfo = DashboardInfo(
    totalPerfis = perfisData.size,
    totalMateriais = materiaisData.size
)
