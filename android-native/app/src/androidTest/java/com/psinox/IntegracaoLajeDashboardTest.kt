package com.psinox

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psinox.screens.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntegracaoLajeDashboardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testIntegracaoLajeDashboard() {
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
        // Perfis → Laje
        composeTestRule.onNodeWithText("Laje").performClick()
        // Preenche campos da Laje (exemplo)
        composeTestRule.onNodeWithText("Comprimento (m)").performTextInput("5.0")
        composeTestRule.onNodeWithText("Largura (m)").performTextInput("2.5")
        composeTestRule.onNodeWithText("Altura (cm)").performTextInput("15")
        composeTestRule.onNodeWithText("Calcular").performClick()
        // Volta para Home e navega até Dashboard
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
        composeTestRule.onNodeWithText("Dashboard").performClick()
        // Valida se resultado da Laje aparece no Dashboard (exemplo: "Laje: 5.0m x 2.5m x 15cm")
        composeTestRule.onNodeWithText("Laje: 5.0m x 2.5m x 15cm").assertIsDisplayed()
    }
}
