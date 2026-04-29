package com.psinox

import org.junit.Assert.assertEquals
import org.junit.Test
import com.psinox.data.Perfil

class PerfilTest {
    @Test
    fun testPerfil() {
        val perfil = Perfil(id = 1, nome = "Perfil U", tipo = "Estrutural", comprimento = 6000)
        assertEquals(1, perfil.id)
        assertEquals("Perfil U", perfil.nome)
        assertEquals("Estrutural", perfil.tipo)
        assertEquals(6000, perfil.comprimento)
    }
}
