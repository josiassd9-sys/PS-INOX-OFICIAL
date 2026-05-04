package com.psinox

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psinox.screens.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FluxoCompletoVigaPrincipalTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testFluxoCompletoVigaPrincipal() {
        // Inicia na Home
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToPerfis = {},
                onNavigateToMateriais = {},
                onNavigateToDashboard = {},
                onNavigateToAISettings = {},
                onNavigateToSobre = {},
                onNavigateToGauge = {},
                onNavigateToConfiguracoes = {}
            )
        }
        // Home → Perfis
        composeTestRule.onNodeWithText("Calculadoras de Perfis").performClick()
        // Perfis → Viga Principal
        composeTestRule.onNodeWithText("Viga Principal").performClick()
        // Preenche campos da Viga Principal (exemplo)
        composeTestRule.onNodeWithText("Comprimento (m)").performTextInput("5.0")
        composeTestRule.onNodeWithText("Largura (cm)").performTextInput("20")
        composeTestRule.onNodeWithText("Altura (cm)").performTextInput("50")
        composeTestRule.onNodeWithText("Calcular").performClick()
        // Navega para Visualizacao
        composeTestRule.onNodeWithText("Visualizar Resultado").performClick()
        // Valida se resultado esperado é exibido (exemplo: "Momento Fletor Máximo")
        composeTestRule.onNodeWithText("Momento Fletor Máximo").assertIsDisplayed()
    }
}
