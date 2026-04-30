package com.psinox.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.psinox.data.dashboardInfo

@Composable
fun HomeScreen(
    onNavigateToPerfis: () -> Unit,
    onNavigateToMateriais: () -> Unit,
    onNavigateToDashboard: () -> Unit,
    onNavigateToAISettings: () -> Unit,
    onNavigateToSobre: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bem-vindo ao PS INOX",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Total de Perfis: ${dashboardInfo.totalPerfis}")
                Text(text = "Total de Materiais: ${dashboardInfo.totalMateriais}")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onNavigateToPerfis) {
            Text("Ver Perfis")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onNavigateToMateriais) {
            Text("Ver Materiais")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onNavigateToDashboard) {
            Text("Ver Dashboard")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onNavigateToAISettings) {
            Text("Configurações de IA")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onNavigateToSobre) {
            Text("Sobre o App")
        }
    }
}
