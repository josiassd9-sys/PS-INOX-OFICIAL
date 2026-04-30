package com.psinox.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.psinox.data.aiSettings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AISettingsScreen(onBack: () -> Unit) {
    var enabled by remember { mutableStateOf(aiSettings.enabled) }
    var apiKey by remember { mutableStateOf(aiSettings.apiKey) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Configurações de IA") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            }
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Ativar IA")
                Spacer(modifier = Modifier.width(8.dp))
                Switch(checked = enabled, onCheckedChange = { enabled = it })
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = apiKey,
                onValueChange = { apiKey = it },
                label = { Text("API Key") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { /* Salvar configurações */ }, enabled = enabled) {
                Text("Salvar")
            }
        }
    }
}
