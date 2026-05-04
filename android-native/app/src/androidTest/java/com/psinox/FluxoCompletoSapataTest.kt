package com.psinox

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psinox.screens.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FluxoCompletoSapataTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testFluxoCompletoSapata() {
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
        // Perfis → Sapata
        composeTestRule.onNodeWithText("Sapata").performClick()
        // Preenche campos da Sapata (exemplo)
        composeTestRule.onNodeWithText("Carga (kN)").performTextInput("100")
        composeTestRule.onNodeWithText("Fck (MPa)").performTextInput("25")
        composeTestRule.onNodeWithText("Coeficiente de Segurança").performTextInput("1.4")
        composeTestRule.onNodeWithText("Calcular").performClick()
        // Navega para Visualizacao
        composeTestRule.onNodeWithText("Visualizar Resultado").performClick()
        // Valida se resultado esperado é exibido (exemplo: "Tensão Admissível do Solo")
        composeTestRule.onNodeWithText("Tensão Admissível do Solo").assertIsDisplayed()
    }
}
