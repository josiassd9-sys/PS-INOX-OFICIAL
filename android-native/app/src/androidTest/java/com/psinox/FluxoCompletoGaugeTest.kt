package com.psinox

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psinox.screens.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FluxoCompletoGaugeTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testFluxoCompletoGauge() {
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
        composeTestRule.onNodeWithText("Espessura (mm)").performTextInput("1.2")
        composeTestRule.onNodeWithText("Material").performTextInput("Aço Carbono")
        composeTestRule.onNodeWithText("Calcular").performClick()
        // Valida se resultado esperado é exibido (exemplo: "Gauge Correspondente")
        composeTestRule.onNodeWithText("Gauge Correspondente").assertIsDisplayed()
    }
}
