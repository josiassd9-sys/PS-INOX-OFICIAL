package com.psinox

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psinox.screens.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testNavegacaoEntreTelasPrincipais() {
        var telaAtual = "home"
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToPerfis = { telaAtual = "perfis" },
                onNavigateToMateriais = { telaAtual = "materiais" },
                onNavigateToDashboard = { telaAtual = "dashboard" },
                onNavigateToAISettings = { telaAtual = "ai" },
                onNavigateToSobre = { telaAtual = "sobre" },
                onNavigateToGauge = { telaAtual = "gauge" },
                onNavigateToConfiguracoes = { telaAtual = "config" },
                onNavigateToTubosODSchedule = {},
                onNavigateToMetalons = {},
                onNavigateToChapas = {},
                onNavigateToListaMateriais = {},
                onNavigateToTabelaSucata = {},
                onNavigateToListaSucatas = {},
                onNavigateToConfiguracoesFerramentas = {}
            )
        }
        // Simula navegação para Perfis
        composeTestRule.onNodeWithText("Perfis").performClick()
        assert(telaAtual == "perfis")
        // Simula navegação para Materiais
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToPerfis = { telaAtual = "perfis" },
                onNavigateToMateriais = { telaAtual = "materiais" },
                onNavigateToDashboard = { telaAtual = "dashboard" },
                onNavigateToAISettings = { telaAtual = "ai" },
                onNavigateToSobre = { telaAtual = "sobre" },
                onNavigateToGauge = { telaAtual = "gauge" },
                onNavigateToConfiguracoes = { telaAtual = "config" },
                onNavigateToTubosODSchedule = {},
                onNavigateToMetalons = {},
                onNavigateToChapas = {},
                onNavigateToListaMateriais = {},
                onNavigateToTabelaSucata = {},
                onNavigateToListaSucatas = {},
                onNavigateToConfiguracoesFerramentas = {}
            )
        }
        composeTestRule.onNodeWithText("Materiais").performClick()
        assert(telaAtual == "materiais")
        // Simula navegação para Dashboard
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToPerfis = { telaAtual = "perfis" },
                onNavigateToMateriais = { telaAtual = "materiais" },
                onNavigateToDashboard = { telaAtual = "dashboard" },
                onNavigateToAISettings = { telaAtual = "ai" },
                onNavigateToSobre = { telaAtual = "sobre" },
                onNavigateToGauge = { telaAtual = "gauge" },
                onNavigateToConfiguracoes = { telaAtual = "config" },
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
        assert(telaAtual == "dashboard")
    }
}
