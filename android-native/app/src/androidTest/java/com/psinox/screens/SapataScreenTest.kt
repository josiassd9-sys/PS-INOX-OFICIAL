package com.psinox.screens

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SapataScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testCalculoSapataMostraResultado() {
        composeTestRule.setContent {
            SapataScreen(onBack = {})
        }
        // Preenche campos obrigatórios
        composeTestRule.onAllNodesWithText("Carga (kgf)")[0].performTextInput("50000")
        composeTestRule.onAllNodesWithText("Tensão do solo (kPa)")[0].performTextInput("150")
        composeTestRule.onNodeWithText("Calcular Sapata").performClick()
        // Verifica se resultado aparece
        composeTestRule.onNodeWithText("Resultado da Análise:").assertIsDisplayed()
        composeTestRule.onNodeWithText("Carga: 50000 kgf").assertIsDisplayed()
    }
}
