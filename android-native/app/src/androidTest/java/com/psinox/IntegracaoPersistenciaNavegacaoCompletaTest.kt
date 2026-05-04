package com.psinox

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psinox.screens.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntegracaoPersistenciaNavegacaoCompletaTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testFluxoCompletoComPersistencia() {
        // VigaPrincipal
        composeTestRule.setContent { VigaPrincipalScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Perfil (ex: W200)")[0].performTextInput("W300")
        composeTestRule.onAllNodesWithText("Carga distribuída (kgf/m)")[0].performTextInput("2000")
        composeTestRule.onAllNodesWithText("Vão Livre (m)")[0].performTextInput("7.0")
        composeTestRule.onNodeWithText("Calcular Viga Principal").performClick()
        composeTestRule.onNodeWithText("Perfil: W300").assertIsDisplayed()

        // Sapata
        composeTestRule.setContent { SapataScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Carga do Pilar (kgf)")[0].performTextInput("40000")
        composeTestRule.onNodeWithText("Calcular Sapata").performClick()
        composeTestRule.onNodeWithText("Carga: 40000 kgf").assertIsDisplayed()

        // Laje
        composeTestRule.setContent { LajeScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Chapa de Steel Deck (ex: MD40, MD75)")[0].performTextInput("MD75")
        composeTestRule.onAllNodesWithText("Espessura do concreto (cm)")[0].performTextInput("12")
        composeTestRule.onAllNodesWithText("Carga total (kgf/m²)")[0].performTextInput("1200")
        composeTestRule.onNodeWithText("Calcular Laje").performClick()
        composeTestRule.onNodeWithText("Chapa: MD75").assertIsDisplayed()

        // ArmaduraSapata
        composeTestRule.setContent { ArmaduraSapataScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Largura da Sapata (cm)")[0].performTextInput("90")
        composeTestRule.onAllNodesWithText("Comprimento da Sapata (cm)")[0].performTextInput("120")
        composeTestRule.onAllNodesWithText("Altura da Sapata (cm)")[0].performTextInput("40")
        composeTestRule.onNodeWithText("Calcular Armadura").performClick()
        composeTestRule.onNodeWithText("Largura: 90 cm").assertIsDisplayed()

        // VigaSecundaria
        composeTestRule.setContent { VigaSecundariaScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Perfil Secundário (ex: W150)")[0].performTextInput("W150")
        composeTestRule.onAllNodesWithText("Carga distribuída (kgf/m)")[0].performTextInput("1500")
        composeTestRule.onAllNodesWithText("Vão Livre (m)")[0].performTextInput("5.0")
        composeTestRule.onNodeWithText("Calcular Viga Secundária").performClick()
        composeTestRule.onNodeWithText("Perfil: W150").assertIsDisplayed()

        // Gauge
        composeTestRule.setContent { GaugeScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Espessura (mm)")[0].performTextInput("1.2")
        composeTestRule.onAllNodesWithText("Largura (mm)")[0].performTextInput("100")
        composeTestRule.onNodeWithText("Calcular Gauge").performClick()
        composeTestRule.onNodeWithText("Espessura: 1.2 mm").assertIsDisplayed()

        // Geometria
        composeTestRule.setContent { GeometriaScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Base (cm)")[0].performTextInput("30")
        composeTestRule.onAllNodesWithText("Altura (cm)")[0].performTextInput("60")
        composeTestRule.onNodeWithText("Calcular Geometria").performClick()
        composeTestRule.onNodeWithText("Base: 30 cm").assertIsDisplayed()

        // Voltar e validar persistência em todas
        composeTestRule.setContent { VigaPrincipalScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Perfil (ex: W200)")[0].assertTextContains("W300")
        composeTestRule.onAllNodesWithText("Carga distribuída (kgf/m)")[0].assertTextContains("2000")
        composeTestRule.onAllNodesWithText("Vão Livre (m)")[0].assertTextContains("7.0")

        composeTestRule.setContent { SapataScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Carga do Pilar (kgf)")[0].assertTextContains("40000")

        composeTestRule.setContent { LajeScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Chapa de Steel Deck (ex: MD40, MD75)")[0].assertTextContains("MD75")
        composeTestRule.onAllNodesWithText("Espessura do concreto (cm)")[0].assertTextContains("12")
        composeTestRule.onAllNodesWithText("Carga total (kgf/m²)")[0].assertTextContains("1200")

        composeTestRule.setContent { ArmaduraSapataScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Largura da Sapata (cm)")[0].assertTextContains("90")
        composeTestRule.onAllNodesWithText("Comprimento da Sapata (cm)")[0].assertTextContains("120")
        composeTestRule.onAllNodesWithText("Altura da Sapata (cm)")[0].assertTextContains("40")

        composeTestRule.setContent { VigaSecundariaScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Perfil Secundário (ex: W150)")[0].assertTextContains("W150")
        composeTestRule.onAllNodesWithText("Carga distribuída (kgf/m)")[0].assertTextContains("1500")
        composeTestRule.onAllNodesWithText("Vão Livre (m)")[0].assertTextContains("5.0")

        composeTestRule.setContent { GaugeScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Espessura (mm)")[0].assertTextContains("1.2")
        composeTestRule.onAllNodesWithText("Largura (mm)")[0].assertTextContains("100")

        composeTestRule.setContent { GeometriaScreen(onBack = {}) }
        composeTestRule.onAllNodesWithText("Base (cm)")[0].assertTextContains("30")
        composeTestRule.onAllNodesWithText("Altura (cm)")[0].assertTextContains("60")
    }
}
