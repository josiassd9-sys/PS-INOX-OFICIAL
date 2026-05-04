package com.psinox.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisualizacaoScreen(onBack: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 24.dp)) {
        TopAppBar(
            title = { Text("Visualização Estrutural") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
        Card(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    "Em breve: visualização gráfica da estrutura",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Esta área exibirá diagramas, esquemas ou visualização 3D da estrutura calculada. Planeje aqui a integração futura com bibliotecas gráficas (ex: Canvas, OpenGL, gráficos customizados) para visualização interativa.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
