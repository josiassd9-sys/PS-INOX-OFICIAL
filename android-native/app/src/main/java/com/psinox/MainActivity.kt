
package com.psinox

import com.psinox.screens.VisualizacaoScreen
import com.psinox.screens.SapataScreen
import com.psinox.screens.PilarScreen

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
import com.psinox.screens.ArmaduraSapataScreen
import com.psinox.screens.GeometriaScreen
import com.psinox.screens.LajeScreen
import com.psinox.screens.VigaSecundariaScreen
import com.psinox.screens.VigaPrincipalScreen
import com.psinox.screens.MateriaisScreen
import com.psinox.screens.DashboardScreen
import com.psinox.screens.AISettingsScreen
import com.psinox.screens.SobreScreen
import com.psinox.screens.GaugeScreen
import com.psinox.screens.MaterialDetailScreen
import com.psinox.screens.ConfiguracoesScreen
import com.psinox.data.materiaisData

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
                            onNavigateToSobre = { navController.navigate("sobre") },
                            onNavigateToGauge = { navController.navigate("gauge") },
                            onNavigateToConfiguracoes = { navController.navigate("configuracoes") }
                        ) }
                                                composable("configuracoes") {
                                                    ConfiguracoesScreen(onBack = { navController.popBackStack() })
                                                }
                        composable("sobre") {
                            SobreScreen(onBack = { navController.popBackStack() })
                        }
                        composable("ai_settings") {
                            AISettingsScreen(onBack = { navController.popBackStack() })
                        }
                        composable("perfis") {
                            PerfisScreen(
                                onBack = { navController.popBackStack() },
                                onNavigateToArmaduraSapata = { navController.navigate("armadura_sapata") },
                                onNavigateToGeometria = { navController.navigate("geometria") },
                                onNavigateToLaje = { navController.navigate("laje") },
                                onNavigateToVigaSecundaria = { navController.navigate("viga_secundaria") },
                                onNavigateToVigaPrincipal = { navController.navigate("viga_principal") },
                                onNavigateToPilar = { navController.navigate("pilar") },
                                onNavigateToSapata = { navController.navigate("sapata") },
                                onNavigateToVisualizacao = { navController.navigate("visualizacao") }
                            )
                        }
                        composable("visualizacao") {
                            VisualizacaoScreen(onBack = { navController.popBackStack() })
                        }
                        composable("sapata") {
                            SapataScreen(onBack = { navController.popBackStack() })
                        }
                        composable("pilar") {
                            PilarScreen(onBack = { navController.popBackStack() })
                        }
                        composable("viga_principal") {
                            VigaPrincipalScreen(onBack = { navController.popBackStack() })
                        }
                        composable("viga_secundaria") {
                            VigaSecundariaScreen(onBack = { navController.popBackStack() })
                        }
                        composable("laje") {
                            LajeScreen(onBack = { navController.popBackStack() })
                        }
                        composable("armadura_sapata") {
                            ArmaduraSapataScreen(onBack = { navController.popBackStack() })
                        }
                        composable("geometria") {
                            GeometriaScreen(onBack = { navController.popBackStack() })
                        }
                        composable("armadura_sapata") {
                            ArmaduraSapataScreen(onBack = { navController.popBackStack() })
                        }
                        composable("materiais") {
                            MateriaisScreen(
                                onBack = { navController.popBackStack() },
                                onMaterialClick = { materialId -> navController.navigate("material_detail/$materialId") }
                            )
                        }
                        composable("material_detail/{materialId}") { backStackEntry ->
                            val materialId = backStackEntry.arguments?.getString("materialId")?.toIntOrNull()
                            val material = materiaisData.find { it.id == materialId }
                            if (material != null) {
                                MaterialDetailScreen(material = material, onBack = { navController.popBackStack() })
                            } else {
                                // Material não encontrado
                                Text("Material não encontrado")
                            }
                        }
                        composable("dashboard") {
                            DashboardScreen(onBack = { navController.popBackStack() })
                        }
                        composable("gauge") {
                            GaugeScreen(onBack = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}

// ...
