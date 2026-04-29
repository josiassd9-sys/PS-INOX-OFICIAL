package com.psinox


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.psinox.screens.HomeScreen
import com.psinox.screens.PerfisWScreen
import com.psinox.screens.DashboardScreen
import com.psinox.screens.ConfiguracoesScreen
import com.psinox.screens.ListaMateriaisScreen
import com.psinox.screens.AISettingsScreen
import com.psinox.screens.GaugeScreen
import com.psinox.screens.SobreScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onNavigateToPerfis = { navController.navigate("perfisW") },
                onNavigateToDashboard = { navController.navigate("dashboard") },
                onNavigateToConfiguracoes = { navController.navigate("configuracoes") },
                onNavigateToListaMateriais = { navController.navigate("listaMateriais") },
                onNavigateToAISettings = { navController.navigate("aiSettings") },
                onNavigateToGauge = { navController.navigate("gauge") },
                onNavigateToSobre = { navController.navigate("sobre") }
            )
        }
        composable("perfisW") {
            PerfisWScreen()
        }
        composable("dashboard") {
            DashboardScreen()
        }
        composable("configuracoes") {
            ConfiguracoesScreen()
        }
        composable("listaMateriais") {
            ListaMateriaisScreen()
        }
        composable("aiSettings") {
            AISettingsScreen()
        }
        composable("gauge") {
            GaugeScreen()
        }
        composable("sobre") {
            SobreScreen()
        }
    }
}
}

data class Perfil(
    val nome: String,
    val peso: Double,
    val h: Double,
    val b: Double,
    val tw: Double,
    val tf: Double,
    val Ix: Double,
    val Wx: Double,
    val rx: Double,
    val Iy: Double,
    val Wy: Double,
    val ry: Double
)
