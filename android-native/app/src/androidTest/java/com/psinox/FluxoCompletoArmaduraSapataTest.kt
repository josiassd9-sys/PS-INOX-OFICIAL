package com.psinox

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psinox.screens.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FluxoCompletoArmaduraSapataTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testFluxoCompletoArmaduraSapata() {
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
        composeTestRule.onNodeWithText("Largura Sapata (cm)").performTextInput("80")
        composeTestRule.onNodeWithText("Altura Sapata (cm)").performTextInput("30")
        composeTestRule.onNodeWithText("Fck (MPa)").performTextInput("25")
        composeTestRule.onNodeWithText("Calcular").performClick()
        // Navega para Visualizacao
        composeTestRule.onNodeWithText("Visualizar Resultado").performClick()
        // Valida se resultado esperado é exibido (exemplo: "Área de Aço da Armadura")
        composeTestRule.onNodeWithText("Área de Aço da Armadura").assertIsDisplayed()
    }
}
