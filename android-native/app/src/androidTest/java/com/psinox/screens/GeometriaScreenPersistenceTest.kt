package com.psinox.screens

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GeometriaScreenPersistenceTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testPersistenciaGeometria() {
        // Primeira abertura: preencher campos e calcular
        composeTestRule.setContent {
            GeometriaScreen(onBack = {})
        }
        composeTestRule.onAllNodesWithText("Vão em X (principal)")[0].performTextInput("10")
        composeTestRule.onAllNodesWithText("Vão em Y (secundário)")[0].performTextInput("5")
        composeTestRule.onAllNodesWithText("Balanço em X (esquerda)")[0].performTextInput("1.2")
        composeTestRule.onAllNodesWithText("Balanço em X (direita)")[0].performTextInput("1.1")
        composeTestRule.onAllNodesWithText("Balanço em Y (superior)")[0].performTextInput("0.7")
        composeTestRule.onAllNodesWithText("Balanço em Y (inferior)")[0].performTextInput("0.6")
        composeTestRule.onNodeWithText("Analisar Geometria").performClick()
        composeTestRule.onNodeWithText("Análise da Geometria da Laje:").assertIsDisplayed()

        // Simular navegação: desmontar e remontar a tela
        composeTestRule.setContent {
            GeometriaScreen(onBack = {})
        }
        // Verificar se os campos foram restaurados automaticamente
        composeTestRule.onAllNodesWithText("Vão em X (principal)")[0].assertTextContains("10")
        composeTestRule.onAllNodesWithText("Vão em Y (secundário)")[0].assertTextContains("5")
        composeTestRule.onAllNodesWithText("Balanço em X (esquerda)")[0].assertTextContains("1.2")
        composeTestRule.onAllNodesWithText("Balanço em X (direita)")[0].assertTextContains("1.1")
        composeTestRule.onAllNodesWithText("Balanço em Y (superior)")[0].assertTextContains("0.7")
        composeTestRule.onAllNodesWithText("Balanço em Y (inferior)")[0].assertTextContains("0.6")
    }
}
