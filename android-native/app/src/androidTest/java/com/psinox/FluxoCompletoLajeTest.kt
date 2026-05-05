package com.psinox

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psinox.screens.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FluxoCompletoLajeTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testFluxoCompletoLaje() {
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
        // Perfis → Laje
        composeTestRule.onNodeWithText("Laje").performClick()
        // Preenche campos da Laje (exemplo)
        composeTestRule.onNodeWithText("Comprimento (m)").performTextInput("4.0")
        composeTestRule.onNodeWithText("Largura (m)").performTextInput("3.0")
        composeTestRule.onNodeWithText("Altura (cm)").performTextInput("12")
        composeTestRule.onNodeWithText("Calcular").performClick()
        // Navega para Visualizacao
        composeTestRule.onNodeWithText("Visualizar Resultado").performClick()
        // Valida se resultado esperado é exibido (exemplo: "Área de Aço Necessária")
        composeTestRule.onNodeWithText("Área de Aço Necessária").assertIsDisplayed()
    }
}
