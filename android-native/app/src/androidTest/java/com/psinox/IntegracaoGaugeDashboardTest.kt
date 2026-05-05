package com.psinox

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psinox.screens.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntegracaoGaugeDashboardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testIntegracaoGaugeDashboard() {
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
        // Home → Gauge
        composeTestRule.onNodeWithText("Seleção de Gauge/Espessura").performClick()
        // Preenche campos do Gauge (exemplo)
        composeTestRule.onNodeWithText("Espessura (mm)").performTextInput("1.5")
        composeTestRule.onNodeWithText("Material").performTextInput("Aço Inox")
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
        // Valida se resultado do Gauge aparece no Dashboard (exemplo: "Gauge: 1.5mm, Aço Inox")
        composeTestRule.onNodeWithText("Gauge: 1.5mm, Aço Inox").assertIsDisplayed()
    }
}
