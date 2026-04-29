package com.psinox

import org.junit.Assert.assertEquals
import org.junit.Test
import com.psinox.data.AISettings

class AISettingsTest {
    @Test
    fun testAISettingsMock() {
        val mock = AISettings(modelo = "GPT-4.1", temperatura = 0.9f, maxTokens = 1024)
        assertEquals("GPT-4.1", mock.modelo)
        assertEquals(0.9f, mock.temperatura, 0.001f)
        assertEquals(1024, mock.maxTokens)
    }
}
