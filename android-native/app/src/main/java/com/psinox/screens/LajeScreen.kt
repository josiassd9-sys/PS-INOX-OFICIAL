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
fun LajeScreen(onBack: () -> Unit) {
    var chapa by remember { mutableStateOf("") }
    var espessuraConcreto by remember { mutableStateOf("") }
    var carga by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Laje / Steel Deck") },
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
            Text("Cálculo da Laje Steel Deck", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = chapa,
                onValueChange = { chapa = it },
                label = { Text("Chapa de Steel Deck (ex: MD40, MD75)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = espessuraConcreto,
                onValueChange = { espessuraConcreto = it },
                label = { Text("Espessura do Concreto (cm)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = carga,
                onValueChange = { carga = it },
                label = { Text("Carga Total (kgf/m²)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
            Button(
                onClick = {
                    resultado = "Chapa: $chapa\nEspessura: $espessuraConcreto cm\nCarga: $carga kgf/m²\n\n(Análise simulada: verifique se a chapa suporta o vão e a carga. Consulte a tabela do fabricante para limites de vão e carga admissível.)"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular Laje")
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
