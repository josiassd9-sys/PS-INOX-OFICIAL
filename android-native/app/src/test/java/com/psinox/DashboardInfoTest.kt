package com.psinox

import org.junit.Assert.assertEquals
import org.junit.Test
import com.psinox.data.DashboardInfo

class DashboardInfoTest {
    @Test
    fun testDashboardInfoMock() {
        val mock = DashboardInfo(totalPerfis = 10, totalMateriais = 20, usuario = "Teste")
        assertEquals(10, mock.totalPerfis)
        assertEquals(20, mock.totalMateriais)
        assertEquals("Teste", mock.usuario)
    }
}
