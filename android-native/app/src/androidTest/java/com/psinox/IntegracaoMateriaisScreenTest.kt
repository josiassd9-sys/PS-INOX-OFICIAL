package com.psinox

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psinox.screens.MateriaisScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntegracaoMateriaisScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testMateriaisScreenExibeMateriaisEClick() {
        var materialClicado: Int? = null
        composeTestRule.setContent {
            MateriaisScreen(
                onBack = {},
                onMaterialClick = { id -> materialClicado = id }
            )
        }
        // Verifica se os materiais de aço inox aparecem
        composeTestRule.onNodeWithText("Aço Inox 304").assertIsDisplayed()
        composeTestRule.onNodeWithText("Aço Inox 316").assertIsDisplayed()
        // Clica no material e valida callback
        composeTestRule.onNodeWithText("Aço Inox 304").performClick()
        assert(materialClicado == 1)
        composeTestRule.onNodeWithText("Aço Inox 316").performClick()
        assert(materialClicado == 2)
    }
}
