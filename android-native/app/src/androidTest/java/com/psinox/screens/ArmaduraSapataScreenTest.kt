package com.psinox.screens

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArmaduraSapataScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testCalculoArmaduraSapataMostraResultado() {
        composeTestRule.setContent {
            ArmaduraSapataScreen(onBack = {})
        }
        // Preenche campos obrigatórios
        composeTestRule.onAllNodesWithText("fck (MPa)")[0].performTextInput("25")
        composeTestRule.onAllNodesWithText("Aço (fyk em kgf/mm²)")[0].performTextInput("50")
        composeTestRule.onAllNodesWithText("Diâmetro da barra (mm)")[0].performTextInput("12.5")
        composeTestRule.onNodeWithText("Calcular Armadura").performClick()
        // Verifica se resultado aparece
        composeTestRule.onNodeWithText("Resultado da Análise:").assertIsDisplayed()
        composeTestRule.onNodeWithText("fck: 25 MPa").assertIsDisplayed()
    }
}
