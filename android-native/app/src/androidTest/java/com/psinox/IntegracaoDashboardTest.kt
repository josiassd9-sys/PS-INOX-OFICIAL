package com.psinox

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psinox.screens.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntegracaoDashboardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testDashboardRecebeDadosDasCalculadoras() {
        // Preenche VigaPrincipal
        composeTestRule.setContent { VigaPrincipalScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Perfil (ex: W200)")[0].performTextInput("W400")
        composeTestRule.onAllNodesWithText("Carga distribuída (kgf/m)")[0].performTextInput("2500")
        composeTestRule.onAllNodesWithText("Vão Livre (m)")[0].performTextInput("8.0")
        composeTestRule.onNodeWithText("Calcular Viga Principal").performClick()
        composeTestRule.onNodeWithText("Perfil: W400").assertIsDisplayed()

        // Preenche Sapata
        composeTestRule.setContent { SapataScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Carga do Pilar (kgf)")[0].performTextInput("50000")
        composeTestRule.onNodeWithText("Calcular Sapata").performClick()
        composeTestRule.onNodeWithText("Carga: 50000 kgf").assertIsDisplayed()

        // Preenche Laje
        composeTestRule.setContent { LajeScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Chapa de Steel Deck (ex: MD40, MD75)")[0].performTextInput("MD90")
        composeTestRule.onAllNodesWithText("Espessura do concreto (cm)")[0].performTextInput("15")
        composeTestRule.onAllNodesWithText("Carga total (kgf/m²)")[0].performTextInput("1300")
        composeTestRule.onNodeWithText("Calcular Laje").performClick()
        composeTestRule.onNodeWithText("Chapa: MD90").assertIsDisplayed()

        // Dashboard
        composeTestRule.setContent { DashboardScreen(onBack = {}) }
        composeTestRule.onNodeWithText("Perfil: W400").assertIsDisplayed()
        composeTestRule.onNodeWithText("Carga: 50000 kgf").assertIsDisplayed()
        composeTestRule.onNodeWithText("Chapa: MD90").assertIsDisplayed()
    }
}
