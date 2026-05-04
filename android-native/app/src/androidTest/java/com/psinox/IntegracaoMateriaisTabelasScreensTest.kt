package com.psinox

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psinox.screens.TubosODScheduleScreen
import com.psinox.screens.MetalonsScreen
import com.psinox.screens.ChapasScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntegracaoMateriaisTabelasScreensTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun testTubosODScheduleScreenExibeDados() {
        composeTestRule.setContent { TubosODScheduleScreen(onBack = {}) }
        composeTestRule.onNodeWithText("Tubos OD & Schedule").assertIsDisplayed()
        composeTestRule.onNodeWithText("OD (mm)  |  Schedule  |  Espessura (mm)  |  Peso (kg/m)").assertIsDisplayed()
        composeTestRule.onNodeWithText("21.3").assertIsDisplayed()
        composeTestRule.onNodeWithText("SCH10S").assertIsDisplayed()
    }

    @Test
    fun testMetalonsScreenExibeDados() {
        composeTestRule.setContent { MetalonsScreen(onBack = {}) }
        composeTestRule.onNodeWithText("Metalons").assertIsDisplayed()
        composeTestRule.onNodeWithText("Largura x Altura (mm) | Espessura (mm) | Peso (kg/m)").assertIsDisplayed()
        composeTestRule.onNodeWithText("20.0 x 20.0").assertIsDisplayed()
        composeTestRule.onNodeWithText("0.68").assertIsDisplayed()
    }

    @Test
    fun testChapasScreenExibeDados() {
        composeTestRule.setContent { ChapasScreen(onBack = {}) }
        composeTestRule.onNodeWithText("Chapas").assertIsDisplayed()
        composeTestRule.onNodeWithText("Espessura (mm) | Largura (mm) | Comprimento (mm) | Peso (kg)").assertIsDisplayed()
        composeTestRule.onNodeWithText("0.8").assertIsDisplayed()
        composeTestRule.onNodeWithText("12.6").assertIsDisplayed()
    }
}
