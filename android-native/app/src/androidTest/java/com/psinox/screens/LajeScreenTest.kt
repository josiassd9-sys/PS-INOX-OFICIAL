package com.psinox.screens

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LajeScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testCalculoLajeMostraResultado() {
        composeTestRule.setContent {
            LajeScreen(onBack = {})
        }
        // Preenche campos obrigatórios
        composeTestRule.onAllNodesWithText("Tipo de chapa (ex: MD40)")[0].performTextInput("MD40")
        composeTestRule.onAllNodesWithText("Espessura da chapa (mm)")[0].performTextInput("75")
        composeTestRule.onAllNodesWithText("Carga total (kgf/m²)")[0].performTextInput("1200")
        composeTestRule.onNodeWithText("Calcular Laje").performClick()
        // Verifica se resultado aparece
        composeTestRule.onNodeWithText("Resultado da Análise:").assertIsDisplayed()
        composeTestRule.onNodeWithText("Chapa: MD40").assertIsDisplayed()
    }
}
