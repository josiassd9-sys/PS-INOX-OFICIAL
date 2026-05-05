package com.psinox

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psinox.screens.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntegracaoArmaduraSapataDashboardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testIntegracaoArmaduraSapataDashboard() {
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
        // Perfis → Armadura Sapata
        composeTestRule.onNodeWithText("Armadura Sapata").performClick()
        // Preenche campos da Armadura Sapata (exemplo)
        composeTestRule.onNodeWithText("Largura Sapata (cm)").performTextInput("90")
        composeTestRule.onNodeWithText("Altura Sapata (cm)").performTextInput("35")
        composeTestRule.onNodeWithText("Fck (MPa)").performTextInput("28")
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
        // Valida se resultado da Armadura Sapata aparece no Dashboard (exemplo: "Armadura Sapata: 90cm x 35cm, fck 28MPa")
        composeTestRule.onNodeWithText("Armadura Sapata: 90cm x 35cm, fck 28MPa").assertIsDisplayed()
    }
}
