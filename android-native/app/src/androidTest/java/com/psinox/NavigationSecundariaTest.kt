package com.psinox

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psinox.screens.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationSecundariaTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testNavegacaoEntreTelasSecundarias() {
        var telaAtual = "home"
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToPerfis = { telaAtual = "perfis" },
                onNavigateToMateriais = { telaAtual = "materiais" },
                onNavigateToDashboard = { telaAtual = "dashboard" },
                onNavigateToAISettings = { telaAtual = "ai" },
                onNavigateToSobre = { telaAtual = "sobre" },
                onNavigateToGauge = { telaAtual = "gauge" },
                onNavigateToConfiguracoes = { telaAtual = "config" }
            )
        }
        // Simula navegação para AISettings
        composeTestRule.onNodeWithText("Configurações de IA").performClick()
        assert(telaAtual == "ai")
        // Simula navegação para Sobre
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToPerfis = { telaAtual = "perfis" },
                onNavigateToMateriais = { telaAtual = "materiais" },
                onNavigateToDashboard = { telaAtual = "dashboard" },
                onNavigateToAISettings = { telaAtual = "ai" },
                onNavigateToSobre = { telaAtual = "sobre" },
                onNavigateToGauge = { telaAtual = "gauge" },
                onNavigateToConfiguracoes = { telaAtual = "config" }
            )
        }
        composeTestRule.onNodeWithText("Sobre o App").performClick()
        assert(telaAtual == "sobre")
        // Simula navegação para Gauge
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToPerfis = { telaAtual = "perfis" },
                onNavigateToMateriais = { telaAtual = "materiais" },
                onNavigateToDashboard = { telaAtual = "dashboard" },
                onNavigateToAISettings = { telaAtual = "ai" },
                onNavigateToSobre = { telaAtual = "sobre" },
                onNavigateToGauge = { telaAtual = "gauge" },
                onNavigateToConfiguracoes = { telaAtual = "config" }
            )
        }
        composeTestRule.onNodeWithText("Seleção de Gauge/Espessura").performClick()
        assert(telaAtual == "gauge")
        // Simula navegação para Configurações
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToPerfis = { telaAtual = "perfis" },
                onNavigateToMateriais = { telaAtual = "materiais" },
                onNavigateToDashboard = { telaAtual = "dashboard" },
                onNavigateToAISettings = { telaAtual = "ai" },
                onNavigateToSobre = { telaAtual = "sobre" },
                onNavigateToGauge = { telaAtual = "gauge" },
                onNavigateToConfiguracoes = { telaAtual = "config" }
            )
        }
        composeTestRule.onNodeWithText("Configurações").performClick()
        assert(telaAtual == "config")
    }
}
