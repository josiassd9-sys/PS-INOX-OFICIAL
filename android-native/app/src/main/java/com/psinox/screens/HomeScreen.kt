package com.psinox.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onNavigateToPerfis: () -> Unit,
    onNavigateToDashboard: () -> Unit,
    onNavigateToConfiguracoes: () -> Unit,
    onNavigateToListaMateriais: () -> Unit,
    onNavigateToAISettings: () -> Unit,
    onNavigateToGauge: () -> Unit,
    onNavigateToSobre: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.foundation.layout.Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Bem-vindo ao PS INOX!",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNavigateToPerfis) {
                Text("Acessar Perfis W")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNavigateToDashboard) {
                Text("Acessar Dashboard")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNavigateToConfiguracoes) {
                Text("Acessar Configurações")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNavigateToListaMateriais) {
                Text("Acessar Lista de Materiais")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNavigateToAISettings) {
                Text("Acessar AI Settings")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNavigateToGauge) {
                Text("Acessar Gauge")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNavigateToSobre) {
                Text("Acessar Sobre")
            }
        }
    }
}
