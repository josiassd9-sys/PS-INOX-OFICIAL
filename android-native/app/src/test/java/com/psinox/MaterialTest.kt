package com.psinox

import org.junit.Assert.assertEquals
import org.junit.Test
import com.psinox.data.Material

class MaterialTest {
    @Test
    fun testMaterial() {
        val material = Material(id = 1, nome = "Inox 304", tipo = "Chapa", quantidade = 50)
        assertEquals(1, material.id)
        assertEquals("Inox 304", material.nome)
        assertEquals("Chapa", material.tipo)
        assertEquals(50, material.quantidade)
    }
}
