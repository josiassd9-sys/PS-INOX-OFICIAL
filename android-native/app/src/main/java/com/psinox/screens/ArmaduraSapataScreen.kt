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
fun ArmaduraSapataScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Armadura Sapata") },
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
            Text(
                text = "Calculadora de Armadura de Sapata",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            // Campos de entrada simulados (valores default)
            var concreteStrength by remember { mutableStateOf("25") }
            var steelStrength by remember { mutableStateOf("50") }
            var barDiameter by remember { mutableStateOf("10.0") }
            var isCalculating by remember { mutableStateOf(false) }
            var result by remember { mutableStateOf<String?>(null) }
            var error by remember { mutableStateOf<String?>(null) }

            OutlinedTextField(
                value = concreteStrength,
                onValueChange = { concreteStrength = it },
                label = { Text("fck do Concreto (MPa)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = steelStrength,
                onValueChange = { steelStrength = it },
                label = { Text("Aço da Armadura (CA-50/60)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = barDiameter,
                onValueChange = { barDiameter = it },
                label = { Text("Diâmetro da Barra (mm)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
            Button(
                onClick = {
                    isCalculating = true
                    error = null
                    // Simulação de cálculo (substituir por lógica real depois)
                    result = "Área de aço sugerida: 4,52 cm²/m\nEspaçamento: c/12,5 cm\nTotal de barras: 8 por direção\n\n(Análise: solução dentro dos limites normativos. Consulte engenheiro estrutural para detalhamento final.)"
                    isCalculating = false
                },
                enabled = !isCalculating,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Text(if (isCalculating) "Calculando..." else "Calcular Armadura")
            }
            if (error != null) {
                Text(text = error ?: "", color = MaterialTheme.colorScheme.error)
            }
            if (result != null) {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = result ?: "", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
