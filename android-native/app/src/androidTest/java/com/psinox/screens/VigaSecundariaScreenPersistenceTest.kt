package com.psinox.screens

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VigaSecundariaScreenPersistenceTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testPersistenciaVigaSecundaria() {
        // Primeira abertura: preencher campos e calcular
        composeTestRule.setContent {
            VigaSecundariaScreen(onBack = {})
        }
        composeTestRule.onAllNodesWithText("Perfil IPE (ex: IPE200)")[0].performTextInput("IPE300")
        composeTestRule.onAllNodesWithText("Carga na Viga (kgf)")[0].performTextInput("900")
        composeTestRule.onAllNodesWithText("Espaçamento entre Vigas (m)")[0].performTextInput("5")
        composeTestRule.onNodeWithText("Calcular Viga Secundária").performClick()
        composeTestRule.onNodeWithText("Perfil: IPE300").assertIsDisplayed()

        // Simular navegação: desmontar e remontar a tela
        composeTestRule.setContent {
            VigaSecundariaScreen(onBack = {})
        }
        // Verificar se os campos foram restaurados automaticamente
        composeTestRule.onAllNodesWithText("Perfil IPE (ex: IPE200)")[0].assertTextContains("IPE300")
        composeTestRule.onAllNodesWithText("Carga na Viga (kgf)")[0].assertTextContains("900")
        composeTestRule.onAllNodesWithText("Espaçamento entre Vigas (m)")[0].assertTextContains("5")
    }
}
