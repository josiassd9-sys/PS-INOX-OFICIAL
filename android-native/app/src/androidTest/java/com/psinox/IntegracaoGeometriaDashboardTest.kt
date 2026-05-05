package com.psinox

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psinox.screens.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntegracaoGeometriaDashboardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testIntegracaoGeometriaDashboard() {
        // Inicia na Home
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToPerfis = {},
                onNavigateToMateriais = {},
                onNavigateToDashboard = {},
                onNavigateToAISettings = {},
                onNavigateToSobre = {},
                onNavigateToGauge = {},
                onNavigateToConfiguracoes = {},
                onNavigateToTubosODSchedule = {},
                onNavigateToMetalons = {},
                onNavigateToChapas = {},
                onNavigateToListaMateriais = {},
                onNavigateToTabelaSucata = {},
                onNavigateToListaSucatas = {},
                onNavigateToConfiguracoesFerramentas = {}
            )
        }
        // Home → Perfis
        composeTestRule.onNodeWithText("Calculadoras de Perfis").performClick()
        // Perfis → Geometria
        composeTestRule.onNodeWithText("Geometria").performClick()
        // Preenche campos da Geometria (exemplo)
        composeTestRule.onNodeWithText("Base (cm)").performTextInput("40")
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
                onNavigateToConfiguracoes = {},
                onNavigateToTubosODSchedule = {},
                onNavigateToMetalons = {},
                onNavigateToChapas = {},
                onNavigateToListaMateriais = {},
                onNavigateToTabelaSucata = {},
                onNavigateToListaSucatas = {},
                onNavigateToConfiguracoesFerramentas = {}
            )
        }
        composeTestRule.onNodeWithText("Dashboard").performClick()
        // Valida se resultado da Geometria aparece no Dashboard (exemplo: "Geometria: 40cm x 60cm")
        composeTestRule.onNodeWithText("Geometria: 40cm x 60cm").assertIsDisplayed()
    }
}
