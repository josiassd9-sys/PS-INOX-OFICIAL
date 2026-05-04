package com.psinox.screens

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GaugeScreenPersistenceTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testPersistenciaGauge() {
        // Primeira abertura: preencher campos e calcular
        composeTestRule.setContent {
            GaugeScreen(onBack = {})
        }
        composeTestRule.onAllNodesWithText("Carga (kgf/m²)")[0].performTextInput("1800")
        composeTestRule.onNodeWithText("Uso:").performClick()
        composeTestRule.onNodeWithText("Cobertura").performClick()
        composeTestRule.onNodeWithText("Calcular Gauge").performClick()
        composeTestRule.onNodeWithText("Espessura mínima recomendada:").assertIsDisplayed()

        // Simular navegação: desmontar e remontar a tela
        composeTestRule.setContent {
            GaugeScreen(onBack = {})
        }
        // Verificar se os campos foram restaurados automaticamente
        composeTestRule.onAllNodesWithText("Carga (kgf/m²)")[0].assertTextContains("1800")
        composeTestRule.onNodeWithText("Cobertura").assertIsDisplayed()
    }
}
