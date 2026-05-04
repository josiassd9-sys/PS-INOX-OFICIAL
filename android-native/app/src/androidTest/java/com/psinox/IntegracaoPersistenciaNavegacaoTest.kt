package com.psinox

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psinox.screens.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntegracaoPersistenciaNavegacaoTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testPersistenciaEFluxoNavegacao() {
        // VigaPrincipal
        composeTestRule.setContent { VigaPrincipalScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Perfil (ex: W200)")[0].performTextInput("Perfil INT")
        composeTestRule.onAllNodesWithText("Carga distribuída (kgf/m)")[0].performTextInput("2222")
        composeTestRule.onAllNodesWithText("Vão Livre (m)")[0].performTextInput("6.5")
        composeTestRule.onNodeWithText("Calcular Viga Principal").performClick()
        composeTestRule.onNodeWithText("Perfil: Perfil INT").assertIsDisplayed()

        // Sapata
        composeTestRule.setContent { SapataScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Carga do Pilar (kgf)")[0].performTextInput("33333")
        composeTestRule.onNodeWithText("Calcular Sapata").performClick()
        composeTestRule.onNodeWithText("Carga: 33333 kgf").assertIsDisplayed()

        // Laje
        composeTestRule.setContent { LajeScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Chapa de Steel Deck (ex: MD40, MD75)")[0].performTextInput("MD40")
        composeTestRule.onAllNodesWithText("Espessura do concreto (cm)")[0].performTextInput("10")
        composeTestRule.onAllNodesWithText("Carga total (kgf/m²)")[0].performTextInput("1100")
        composeTestRule.onNodeWithText("Calcular Laje").performClick()
        composeTestRule.onNodeWithText("Chapa: MD40").assertIsDisplayed()

        // Voltar para VigaPrincipal e validar persistência
        composeTestRule.setContent { VigaPrincipalScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Perfil (ex: W200)")[0].assertTextContains("Perfil INT")
        composeTestRule.onAllNodesWithText("Carga distribuída (kgf/m)")[0].assertTextContains("2222")
        composeTestRule.onAllNodesWithText("Vão Livre (m)")[0].assertTextContains("6.5")

        // Voltar para Sapata e validar persistência
        composeTestRule.setContent { SapataScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Carga do Pilar (kgf)")[0].assertTextContains("33333")

        // Voltar para Laje e validar persistência
        composeTestRule.setContent { LajeScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Chapa de Steel Deck (ex: MD40, MD75)")[0].assertTextContains("MD40")
        composeTestRule.onAllNodesWithText("Espessura do concreto (cm)")[0].assertTextContains("10")
        composeTestRule.onAllNodesWithText("Carga total (kgf/m²)")[0].assertTextContains("1100")
    }
}
