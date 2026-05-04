package com.psinox.screens

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GaugeScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testCalculoGaugeMostraResultado() {
        composeTestRule.setContent {
            GaugeScreen(onBack = {})
        }
        // Preenche campos obrigatórios
        composeTestRule.onAllNodesWithText("Carga (kgf/m²)")[0].performTextInput("1200")
        composeTestRule.onNodeWithText("Uso:").performClick()
        composeTestRule.onNodeWithText("Piso").performClick() // Seleciona "Piso"
        composeTestRule.onNodeWithText("Calcular Gauge").performClick()
        // Verifica se resultado aparece
        composeTestRule.onNodeWithText("Espessura mínima recomendada:").assertIsDisplayed()
        composeTestRule.onNodeWithText("Gauge").assertIsDisplayed()
    }
}
