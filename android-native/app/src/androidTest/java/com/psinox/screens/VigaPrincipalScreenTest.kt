package com.psinox.screens

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VigaPrincipalScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testCalculoVigaPrincipalMostraResultado() {
        composeTestRule.setContent {
            VigaPrincipalScreen(onBack = {})
        }
        // Preenche campos obrigatórios
        composeTestRule.onAllNodesWithText("Perfil (ex: W200)")[0].performTextInput("Perfil A")
        composeTestRule.onAllNodesWithText("Carga distribuída (kgf/m)")[0].performTextInput("1000")
        composeTestRule.onAllNodesWithText("Vão Livre (m)")[0].performTextInput("5")
        composeTestRule.onNodeWithText("Calcular Viga Principal").performClick()
        // Verifica se resultado aparece
        composeTestRule.onNodeWithText("Resultado da Análise:").assertIsDisplayed()
        composeTestRule.onNodeWithText("Perfil: Perfil A").assertIsDisplayed()
    }
}
