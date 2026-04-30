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
fun SobreScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Sobre o App") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            }
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text("PS INOX - App Nativo", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Este aplicativo foi migrado de uma base híbrida para nativa utilizando Jetpack Compose, mantendo toda a lógica, dados e experiência do app original.")
            Spacer(modifier = Modifier.height(16.dp))
            Text("Versão: 1.0")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Desenvolvido por: Sua equipe")
        }
    }
}
