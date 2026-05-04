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
    onNavigateToSobre: () -> Unit,
    onNavigateToGauge: () -> Unit,
    onNavigateToConfiguracoes: () -> Unit,
    onNavigateToTubosODSchedule: () -> Unit,
    onNavigateToMetalons: () -> Unit,
    onNavigateToChapas: () -> Unit
    ,onNavigateToListaMateriais: () -> Unit
    ,onNavigateToTabelaSucata: () -> Unit
    ,onNavigateToListaSucatas: () -> Unit
    ,onNavigateToConfiguracoesFerramentas: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bem-vindo ao PS INOX",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = "Total de Perfis: ${dashboardInfo.totalPerfis}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Total de Materiais: ${dashboardInfo.totalMateriais}", style = MaterialTheme.typography.bodyLarge)
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                    Button(onClick = onNavigateToListaMateriais, modifier = Modifier.fillMaxWidth()) {
                                        Text("Lista de Materiais")
                                    }
                                    Button(onClick = onNavigateToTabelaSucata, modifier = Modifier.fillMaxWidth()) {
                                        Text("Tabela de Sucata")
                                    }
                                    Button(onClick = onNavigateToListaSucatas, modifier = Modifier.fillMaxWidth()) {
                                        Text("Lista de Sucatas")
                                    }
                                    Button(onClick = onNavigateToConfiguracoesFerramentas, modifier = Modifier.fillMaxWidth()) {
                                        Text("Configurações de Ferramentas")
                                    }
                        Button(onClick = onNavigateToTubosODSchedule, modifier = Modifier.fillMaxWidth()) {
                            Text("Tubos OD & Schedule")
                        }
                        Button(onClick = onNavigateToMetalons, modifier = Modifier.fillMaxWidth()) {
                            Text("Metalons")
                        }
                        Button(onClick = onNavigateToChapas, modifier = Modifier.fillMaxWidth()) {
                            Text("Chapas")
                        }
            Button(onClick = onNavigateToPerfis, modifier = Modifier.fillMaxWidth()) {
                Text("Ver Perfis")
            }
            Button(onClick = onNavigateToMateriais, modifier = Modifier.fillMaxWidth()) {
                Text("Ver Materiais")
            }
            Button(onClick = onNavigateToDashboard, modifier = Modifier.fillMaxWidth()) {
                Text("Ver Dashboard")
            }
            Button(onClick = onNavigateToAISettings, modifier = Modifier.fillMaxWidth()) {
                Text("Configurações de IA")
            }
            Button(onClick = onNavigateToGauge, modifier = Modifier.fillMaxWidth()) {
                Text("Ver Gauges")
            }
            Button(onClick = onNavigateToConfiguracoes, modifier = Modifier.fillMaxWidth()) {
                Text("Configurações Gerais")
            }
            Button(onClick = onNavigateToSobre, modifier = Modifier.fillMaxWidth()) {
                Text("Sobre o App")
            }
        }
    }
}
