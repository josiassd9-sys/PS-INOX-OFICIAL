package com.psinox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.psinox.ui.theme.PsInoxTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.psinox.screens.HomeScreen
import com.psinox.screens.PerfisScreen
import com.psinox.screens.MateriaisScreen
import com.psinox.screens.DashboardScreen
import com.psinox.screens.AISettingsScreen
import com.psinox.screens.SobreScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PsInoxTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { HomeScreen(
                            onNavigateToPerfis = { navController.navigate("perfis") },
                            onNavigateToMateriais = { navController.navigate("materiais") },
                            onNavigateToDashboard = { navController.navigate("dashboard") },
                            onNavigateToAISettings = { navController.navigate("ai_settings") },
                            onNavigateToSobre = { navController.navigate("sobre") }
                        ) }
                                                                        composable("sobre") {
                                                                            SobreScreen(onBack = { navController.popBackStack() })
                                                                        }
                                                composable("ai_settings") {
                                                    AISettingsScreen(onBack = { navController.popBackStack() })
                                                }
                        composable("perfis") {
                            PerfisScreen(onBack = { navController.popBackStack() })
                        }
                        composable("materiais") {
                            MateriaisScreen(onBack = { navController.popBackStack() })
                        }
                        composable("dashboard") {
                            DashboardScreen(onBack = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}

// ...
