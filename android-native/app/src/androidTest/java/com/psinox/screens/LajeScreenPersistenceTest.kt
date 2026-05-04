package com.psinox.screens

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LajeScreenPersistenceTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testPersistenciaLaje() {
        // Primeira abertura: preencher campos e calcular
        composeTestRule.setContent {
            LajeScreen(onBack = {})
        }
        composeTestRule.onAllNodesWithText("Chapa de Steel Deck (ex: MD40, MD75)")[0].performTextInput("MD75")
        composeTestRule.onAllNodesWithText("Espessura do concreto (cm)")[0].performTextInput("12")
        composeTestRule.onAllNodesWithText("Carga total (kgf/m²)")[0].performTextInput("1500")
        composeTestRule.onNodeWithText("Calcular Laje").performClick()
        composeTestRule.onNodeWithText("Chapa: MD75").assertIsDisplayed()

        // Simular navegação: desmontar e remontar a tela
        composeTestRule.setContent {
            LajeScreen(onBack = {})
        }
        // Verificar se os campos foram restaurados automaticamente
        composeTestRule.onAllNodesWithText("Chapa de Steel Deck (ex: MD40, MD75)")[0].assertTextContains("MD75")
        composeTestRule.onAllNodesWithText("Espessura do concreto (cm)")[0].assertTextContains("12")
        composeTestRule.onAllNodesWithText("Carga total (kgf/m²)")[0].assertTextContains("1500")
    }
}
