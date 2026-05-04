package com.psinox.screens

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GeometriaScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testAnaliseGeometriaMostraResultado() {
        composeTestRule.setContent {
            GeometriaScreen(onBack = {})
        }
        // Preenche campos obrigatórios
        composeTestRule.onAllNodesWithText("Vão em X (principal)")[0].performTextInput("8")
        composeTestRule.onAllNodesWithText("Vão em Y (secundário)")[0].performTextInput("4")
        composeTestRule.onAllNodesWithText("Balanço em X (esquerda)")[0].performTextInput("1")
        composeTestRule.onAllNodesWithText("Balanço em X (direita)")[0].performTextInput("1")
        composeTestRule.onAllNodesWithText("Balanço em Y (superior)")[0].performTextInput("0.5")
        composeTestRule.onAllNodesWithText("Balanço em Y (inferior)")[0].performTextInput("0.5")
        composeTestRule.onNodeWithText("Analisar Geometria").performClick()
        // Verifica se resultado aparece
        composeTestRule.onNodeWithText("Resultado da Análise:").assertIsDisplayed()
        composeTestRule.onNodeWithText("Análise da Geometria da Laje:").assertIsDisplayed()
    }
}
