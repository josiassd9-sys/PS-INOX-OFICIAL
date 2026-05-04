package com.psinox.screens

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VigaPrincipalScreenPersistenceTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testPersistenciaVigaPrincipal() {
        // Primeira abertura: preencher campos e calcular
        composeTestRule.setContent {
            VigaPrincipalScreen(onBack = {})
        }
        composeTestRule.onAllNodesWithText("Perfil (ex: W200)")[0].performTextInput("Perfil Persistente")
        composeTestRule.onAllNodesWithText("Carga distribuída (kgf/m)")[0].performTextInput("1234")
        composeTestRule.onAllNodesWithText("Vão Livre (m)")[0].performTextInput("7.5")
        composeTestRule.onNodeWithText("Calcular Viga Principal").performClick()
        composeTestRule.onNodeWithText("Resultado da Análise:").assertIsDisplayed()

        // Simular navegação: desmontar e remontar a tela
        composeTestRule.setContent {
            VigaPrincipalScreen(onBack = {})
        }
        // Verificar se os campos foram restaurados automaticamente
        composeTestRule.onAllNodesWithText("Perfil (ex: W200)")[0].assertTextContains("Perfil Persistente")
        composeTestRule.onAllNodesWithText("Carga distribuída (kgf/m)")[0].assertTextContains("1234")
        composeTestRule.onAllNodesWithText("Vão Livre (m)")[0].assertTextContains("7.5")
    }
}
