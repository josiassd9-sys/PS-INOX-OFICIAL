package com.psinox.screens

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfiguracoesScreen(onBack: () -> Unit) {
    var notificacoesAtivas by remember { mutableStateOf(true) }
    var temaEscuro by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Configurações") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            }
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Notificações")
                Spacer(modifier = Modifier.width(8.dp))
                Switch(checked = notificacoesAtivas, onCheckedChange = { notificacoesAtivas = it })
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Tema escuro")
                Spacer(modifier = Modifier.width(8.dp))
                Switch(checked = temaEscuro, onCheckedChange = { temaEscuro = it })
            }
        }
    }
}
