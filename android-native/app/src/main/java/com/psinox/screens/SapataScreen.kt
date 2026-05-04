package com.psinox.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.psinox.datastore.SapataDataStore
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Composable
fun SapataScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val dataStore = remember { SapataDataStore(context) }
    val scope = rememberCoroutineScope()
    val soilTypes = listOf(
        "Rocha Sã" to 10.0,
        "Argila Dura" to 4.0,
        "Argila Rija" to 2.0,
        "Areia Compacta" to 2.5,
        "Areia Fofa" to 1.0
    )
    var carga by remember { mutableStateOf("") }
    var soloSelecionado by remember { mutableStateOf(soilTypes.first().first) }

    // Carregar valores salvos ao abrir a tela
    LaunchedEffect(Unit) {
        dataStore.sapataData.collect { state ->
            if (state.carga > 0f) carga = state.carga.toString()
            if (state.fck > 0f) soloSelecionado = soilTypes.getOrNull(state.fck.toInt())?.first ?: soilTypes.first().first
        }
    }
    var resultado by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 24.dp)) {
        TopAppBar(
            title = { Text("Cálculo da Sapata") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = carga,
            onValueChange = { carga = it },
            label = { Text("Carga do Pilar (kgf)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
            Text("Tipo de Solo:", modifier = Modifier.alignByBaseline())
            Spacer(Modifier.width(8.dp))
            var expanded by remember { mutableStateOf(false) }
            Box {
                Button(onClick = { expanded = true }) {
                    Text(soloSelecionado)
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    soilTypes.forEach { (nome, _) ->
                        DropdownMenuItem(text = { Text(nome) }, onClick = {
                            soloSelecionado = nome
                            expanded = false
                        })
                    }
                }
            }
        }
        Button(
            onClick = {
                erro = ""
                resultado = ""
                val cargaKgf = carga.replace(",", ".").toDoubleOrNull()
                val solo = soilTypes.find { it.first == soloSelecionado }
                if (cargaKgf == null || cargaKgf <= 0) {
                    erro = "Informe uma carga válida."
                    return@Button
                }
                if (solo == null) {
                    erro = "Selecione um tipo de solo."
                    return@Button
                }
                val pressaoSolo = solo.second
                val areaMinimaM2 = cargaKgf / (pressaoSolo * 10000)
                val ladoM = kotlin.math.sqrt(areaMinimaM2)
                val alturaCm = maxOf(30.0, ((ladoM * 100) / 3 / 5).let { kotlin.math.ceil(it) * 5 })
                resultado = "Carga: $cargaKgf kgf\nSolo: ${solo.first} (${pressaoSolo} kgf/cm²)\n\nÁrea mínima: %.2f m²\nLado da sapata: %.2f m\nAltura recomendada: %.0f cm\n\nEste é um pré-dimensionamento. Consulte um engenheiro para o projeto executivo final.".format(areaMinimaM2, ladoM, alturaCm)
                // Salvar dados no DataStore
                scope.launch {
                    dataStore.salvarSapata(
                        carga = cargaKgf.toFloat(),
                        fck = soilTypes.indexOfFirst { it.first == soloSelecionado }.toFloat(),
                        coefSeg = 0f // Não utilizado, mas mantido para compatibilidade
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular Sapata")
        }
        if (erro.isNotBlank()) {
            Spacer(Modifier.height(8.dp))
            Text(erro, color = MaterialTheme.colorScheme.error)
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
