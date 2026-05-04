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
                    result = null
                    // Validação dos campos
                    val fck = concreteStrength.replace(",", ".").toDoubleOrNull()
                    val fyk = steelStrength.replace(",", ".").toDoubleOrNull()
                    val diam = barDiameter.replace(",", ".").toDoubleOrNull()
                    if (fck == null || fck < 15) {
                        error = "Informe um fck válido (mínimo 15 MPa)."
                        isCalculating = false
                        return@Button
                    }
                    if (fyk == null || (fyk != 50.0 && fyk != 60.0)) {
                        error = "Informe o aço (50 ou 60)."
                        isCalculating = false
                        return@Button
                    }
                    if (diam == null || diam < 5.0) {
                        error = "Informe um diâmetro de barra válido (mínimo 5 mm)."
                        isCalculating = false
                        return@Button
                    }
                    // Cálculo normativo (exemplo simplificado):
                    // Área mínima de aço: As,min = 0,15% da área da seção (NBR 6118)
                    // Para sapata de 1m de largura (por metro):
                    val largura = 100.0 // cm (1m)
                    val altura = 30.0 // cm (exemplo, pode ser campo futuro)
                    val areaSecao = largura * altura // cm²
                    val asMin = areaSecao * 0.0015 // cm²
                    // Área de uma barra: As_barra = pi * d² / 4 (mm² → cm²)
                    val asBarra = Math.PI * Math.pow(diam, 2.0) / 4.0 / 100.0
                    val nBarras = kotlin.math.ceil(asMin / asBarra).toInt().coerceAtLeast(2)
                    // Espaçamento sugerido (máx. 20cm, min. 10cm)
                    val espacamento = (largura / nBarras).coerceIn(10.0, 20.0)
                    result = "Área de aço mínima: %.2f cm²/m\nDiâmetro da barra: %.1f mm\nQuantidade de barras: %d por metro\nEspaçamento sugerido: c/%.1f cm\n\n(Consulte engenheiro estrutural para detalhamento final.)"
                        .format(asMin, diam, nBarras, espacamento)
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
