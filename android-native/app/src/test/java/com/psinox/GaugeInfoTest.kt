package com.psinox

import org.junit.Assert.assertEquals
import org.junit.Test
import com.psinox.data.GaugeInfo

class GaugeInfoTest {
    @Test
    fun testGaugeInfoMock() {
        val mock = GaugeInfo(valor = 42.0f, descricao = "Teste Gauge")
        assertEquals(42.0f, mock.valor, 0.001f)
        assertEquals("Teste Gauge", mock.descricao)
    }
}
