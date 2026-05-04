package com.psinox.screens

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VigaSecundariaScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testCalculoVigaSecundariaMostraResultado() {
        composeTestRule.setContent {
            VigaSecundariaScreen(onBack = {})
        }
        // Preenche campos obrigatórios
        composeTestRule.onAllNodesWithText("Perfil IPE (ex: IPE200)")[0].performTextInput("IPE200")
        composeTestRule.onAllNodesWithText("Carga na Viga (kgf)")[0].performTextInput("800")
        composeTestRule.onAllNodesWithText("Espaçamento entre Vigas (m)")[0].performTextInput("4")
        composeTestRule.onNodeWithText("Calcular Viga Secundária").performClick()
        // Verifica se resultado aparece
        composeTestRule.onNodeWithText("Resultado da Análise:").assertIsDisplayed()
        composeTestRule.onNodeWithText("Perfil: IPE200").assertIsDisplayed()
    }
}
