package com.psinox

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psinox.screens.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntegracaoVigaDashboardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testIntegracaoVigaPrincipalDashboard() {
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
        composeTestRule.onNodeWithText("Comprimento (m)").performTextInput("6.0")
        composeTestRule.onNodeWithText("Largura (cm)").performTextInput("25")
        composeTestRule.onNodeWithText("Altura (cm)").performTextInput("60")
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
        // Valida se resultado da Viga Principal aparece no Dashboard (exemplo: "Viga Principal: 6.0m x 25cm x 60cm")
        composeTestRule.onNodeWithText("Viga Principal: 6.0m x 25cm x 60cm").assertIsDisplayed()
    }
}
