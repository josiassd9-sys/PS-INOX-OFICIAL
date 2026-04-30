package com.psinox

import com.psinox.data.perfisData
import org.junit.Assert.assertEquals
import org.junit.Test

class PerfilTest {
    @Test
    fun testPerfisDataCount() {
        assertEquals(3, perfisData.size)
    }

    @Test
    fun testPerfilContent() {
        val perfil = perfisData[0]
        assertEquals("Perfil A", perfil.nome)
        assertEquals("Descrição do Perfil A", perfil.descricao)
    }
}
