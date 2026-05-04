package com.psinox.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import org.junit.Rule
import org.junit.Test

class VigaPrincipalScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCalculoVigaPrincipalMostraResultado() {
        composeTestRule.setContent {
            VigaPrincipalScreen(onBack = {})
        }
        // Preenche campos obrigatórios
        composeTestRule.onAllNodes(hasText("Perfil (ex: W200)"))[0].performTextInput("Perfil A")
        composeTestRule.onAllNodes(hasText("Carga distribuída (kgf/m)"))[0].performTextInput("1000")
        composeTestRule.onAllNodes(hasText("Vão Livre (m)"))[0].performTextInput("5")
        // Seleciona tipo de aço padrão (já selecionado)
        composeTestRule.onNodeWithText("Calcular Viga Principal").performClick()
        // Verifica se resultado aparece
        composeTestRule.onNodeWithText("Resultado da Análise:").assertIsDisplayed()
        composeTestRule.onNodeWithText("Perfil: Perfil A").assertIsDisplayed()
    }
}
