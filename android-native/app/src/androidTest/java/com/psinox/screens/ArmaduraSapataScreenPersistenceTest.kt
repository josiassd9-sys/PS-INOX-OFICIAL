package com.psinox.screens

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArmaduraSapataScreenPersistenceTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testPersistenciaArmaduraSapata() {
        // Primeira abertura: preencher campos e calcular
        composeTestRule.setContent {
            ArmaduraSapataScreen(onBack = {})
        }
        composeTestRule.onAllNodesWithText("fck do Concreto (MPa)")[0].performTextInput("30")
        composeTestRule.onAllNodesWithText("Aço da Armadura (CA-50/60)")[0].performTextInput("60")
        composeTestRule.onAllNodesWithText("Diâmetro da Barra (mm)")[0].performTextInput("16")
        composeTestRule.onNodeWithText("Calcular Armadura").performClick()
        composeTestRule.onNodeWithText("Área de aço mínima:").assertIsDisplayed()

        // Simular navegação: desmontar e remontar a tela
        composeTestRule.setContent {
            ArmaduraSapataScreen(onBack = {})
        }
        // Verificar se os campos foram restaurados automaticamente
        composeTestRule.onAllNodesWithText("fck do Concreto (MPa)")[0].assertTextContains("30")
        composeTestRule.onAllNodesWithText("Aço da Armadura (CA-50/60)")[0].assertTextContains("60")
        composeTestRule.onAllNodesWithText("Diâmetro da Barra (mm)")[0].assertTextContains("16")
    }
}
