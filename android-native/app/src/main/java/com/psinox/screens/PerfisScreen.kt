package com.psinox.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.psinox.data.perfisData

import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfisScreen(
    onBack: () -> Unit,
    onNavigateToArmaduraSapata: () -> Unit,
    onNavigateToGeometria: () -> Unit,
    onNavigateToLaje: () -> Unit,
    onNavigateToVigaSecundaria: () -> Unit,
    onNavigateToVigaPrincipal: () -> Unit,
    onNavigateToPilar: () -> Unit,
    onNavigateToSapata: () -> Unit,
    onNavigateToVisualizacao: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 24.dp)) {
        TopAppBar(
            title = { Text("Perfis") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            }
        )
        Column(modifier = Modifier.padding(top = 16.dp)) {
            Button(
                onClick = onNavigateToArmaduraSapata,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text("Ir para Armadura Sapata")
            }
            Button(
                onClick = onNavigateToGeometria,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text("Ir para Geometria da Laje")
            }
            Button(
                onClick = onNavigateToLaje,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text("Ir para Laje/Steel Deck")
            }
            Button(
                onClick = onNavigateToVigaSecundaria,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text("Ir para Viga Secundária")
            }
            Button(
                onClick = onNavigateToVigaPrincipal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text("Ir para Viga Principal")
            }
            Button(
                onClick = onNavigateToPilar,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text("Ir para Pilar")
            }
            Button(
                onClick = onNavigateToSapata,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text("Ir para Sapata")
            }
            Button(
                onClick = onNavigateToVisualizacao,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text("Ir para Visualização")
            }
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(perfisData) { perfil ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = perfil.nome, style = MaterialTheme.typography.titleMedium)
                            Text(text = perfil.descricao, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
