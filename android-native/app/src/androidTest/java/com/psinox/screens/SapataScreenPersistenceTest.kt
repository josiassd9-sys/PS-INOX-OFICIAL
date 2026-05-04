package com.psinox.screens

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SapataScreenPersistenceTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testPersistenciaSapata() {
        // Primeira abertura: preencher campos e calcular
        composeTestRule.setContent {
            SapataScreen(onBack = {})
        }
        composeTestRule.onAllNodesWithText("Carga do Pilar (kgf)")[0].performTextInput("54321")
        // Seleciona o segundo tipo de solo
        composeTestRule.onNodeWithText("Tipo de Solo:").performClick()
        composeTestRule.onNodeWithText("Argila Dura").performClick()
        composeTestRule.onNodeWithText("Calcular Sapata").performClick()
        composeTestRule.onNodeWithText("Resultado da Análise:").assertIsDisplayed()

        // Simular navegação: desmontar e remontar a tela
        composeTestRule.setContent {
            SapataScreen(onBack = {})
        }
        // Verificar se os campos foram restaurados automaticamente
        composeTestRule.onAllNodesWithText("Carga do Pilar (kgf)")[0].assertTextContains("54321")
        composeTestRule.onNodeWithText("Argila Dura").assertIsDisplayed()
    }
}
