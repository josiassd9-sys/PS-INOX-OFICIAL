package com.psinox.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VigaSecundariaScreen(onBack: () -> Unit) {
    var perfil by remember { mutableStateOf("") }
    var carga by remember { mutableStateOf("") }
    var espacamento by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Viga Secundária") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text("Cálculo da Viga Secundária (IPE)", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = perfil,
                onValueChange = { perfil = it },
                label = { Text("Perfil IPE (ex: IPE200)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = carga,
                onValueChange = { carga = it },
                label = { Text("Carga na Viga (kgf)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = espacamento,
                onValueChange = { espacamento = it },
                label = { Text("Espaçamento entre Vigas (m)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
            Button(
                onClick = {
                    resultado = "Perfil: $perfil\nCarga: $carga kgf\nEspaçamento: $espacamento m\n\n(Análise simulada: verifique se o perfil suporta a carga e o vão. Consulte a tabela de perfis para limites de utilização e segurança.)"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular Viga Secundária")
            }
            if (resultado.isNotBlank()) {
                Spacer(Modifier.height(16.dp))
                Text("Resultado da Análise:", style = MaterialTheme.typography.titleMedium)
                Card(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(resultado, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
