package com.psinox.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.psinox.datastore.GaugeDataStore
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.psinox.data.gaugeInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Composable
fun GaugeScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val dataStore = remember { GaugeDataStore(context) }
    val scope = rememberCoroutineScope()
    var carga by remember { mutableStateOf("") }
    var uso by remember { mutableStateOf("Piso") }

    // Carregar valores salvos ao abrir a tela
    LaunchedEffect(Unit) {
        dataStore.gaugeData.collect { state ->
            if (state.carga > 0f) carga = state.carga.toString()
            if (state.uso.isNotBlank()) uso = state.uso
        }
    }
    var resultado by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf("") }

    val opcoesUso = listOf("Piso", "Cobertura", "Divisória")

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Seleção de Gauge/Espessura") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            }
        )
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = carga,
                onValueChange = { carga = it },
                label = { Text("Carga (kgf/m²)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                Text("Uso:", modifier = Modifier.alignByBaseline())
                Spacer(Modifier.width(8.dp))
                var expanded by remember { mutableStateOf(false) }
                Box {
                    Button(onClick = { expanded = true }) {
                        Text(uso)
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        opcoesUso.forEach { u ->
                            DropdownMenuItem(text = { Text(u) }, onClick = {
                                uso = u
                                expanded = false
                            })
                        }
                    }
                }
            }
            Button(onClick = {
                erro = ""
                resultado = ""
                val cargaNum = carga.replace(",", ".").toDoubleOrNull()
                if (cargaNum == null || cargaNum <= 0) {
                    erro = "Informe uma carga válida."
                    return@Button
                }
                // Lógica simplificada: recomendações típicas
                val espessuraMinima = when (uso) {
                    "Piso" -> if (cargaNum > 1000) 20.0 else 12.5
                    "Cobertura" -> if (cargaNum > 300) 8.0 else 6.0
                    "Divisória" -> 6.0
                    else -> 8.0
                }
                val gaugesCompat = gaugeInfo.filter { it.valor >= espessuraMinima }
                resultado = "Espessura mínima recomendada: %.1f mm\nGauges compatíveis:".format(espessuraMinima)
                if (gaugesCompat.isNotEmpty()) {
                    resultado += gaugesCompat.joinToString(separator = "\n") { "- Gauge ${it.id}: ${it.valor} mm" }
                } else {
                    resultado += "\nNenhum gauge disponível atende à espessura mínima."
                }
                // Salvar dados no DataStore
                scope.launch {
                    dataStore.salvarGauge(
                        carga = cargaNum.toFloat(),
                        uso = uso
                    )
                }
            }, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                Text("Calcular Gauge")
            }
            if (erro.isNotBlank()) {
                Text(erro, color = MaterialTheme.colorScheme.error)
            }
            if (resultado.isNotBlank()) {
                Card(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(resultado, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            Text("Tabela de Gauges Cadastrados:", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(gaugeInfo) { gauge ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(text = "ID: ${gauge.id}", style = MaterialTheme.typography.titleMedium)
                            Text(text = "Valor: ${gauge.valor} ${gauge.unidade}", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
