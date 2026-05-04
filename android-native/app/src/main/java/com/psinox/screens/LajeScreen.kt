package com.psinox.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.psinox.datastore.LajeDataStore
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Composable
fun LajeScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val dataStore = remember { LajeDataStore(context) }
    val scope = rememberCoroutineScope()
    var chapa by remember { mutableStateOf("") }
    var espessuraConcreto by remember { mutableStateOf("") }
    var carga by remember { mutableStateOf("") }

    // Carregar valores salvos ao abrir a tela
    LaunchedEffect(Unit) {
        dataStore.lajeData.collect { state ->
            if (state.comprimento > 0f) chapa = state.comprimento.toString()
            if (state.largura > 0f) espessuraConcreto = state.largura.toString()
            if (state.altura > 0f) carga = state.altura.toString()
        }
    }
    var resultado by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf("") }

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
                    erro = ""
                    resultado = ""
                    // Validação dos campos
                    val chapaId = chapa.trim().uppercase()
                    val espConcreto = espessuraConcreto.replace(",", ".").toDoubleOrNull()
                    val cargaTotal = carga.replace(",", ".").toDoubleOrNull()
                    if (chapaId.isBlank()) {
                        erro = "Informe a chapa de Steel Deck (ex: MD40, MD75)."
                        return@Button
                    }
                    if (espConcreto == null || espConcreto < 5.0) {
                        erro = "Informe uma espessura de concreto válida (mínimo 5 cm)."
                        return@Button
                    }
                    if (cargaTotal == null || cargaTotal < 100) {
                        erro = "Informe uma carga total válida (mínimo 100 kgf/m²)."
                        return@Button
                    }
                    // Limites típicos de carga admissível (exemplo, pode ser ajustado conforme fabricante)
                    val limites = mapOf(
                        "MD40" to 1200.0,
                        "MD75" to 1800.0
                    )
                    val cargaLimite = limites[chapaId] ?: 0.0
                    if (cargaLimite == 0.0) {
                        erro = "Chapa não reconhecida. Use MD40 ou MD75 (ou ajuste os limites conforme fabricante)."
                        return@Button
                    }
                    val situacao = if (cargaTotal <= cargaLimite) {
                        "OK: Carga dentro do limite da chapa ($cargaLimite kgf/m²)."
                    } else {
                        "ATENÇÃO: Carga excede o limite da chapa ($cargaLimite kgf/m²)! Escolha chapa superior ou reduza a carga."
                    }
                    resultado = "Chapa: $chapaId\nEspessura: $espConcreto cm\nCarga: $cargaTotal kgf/m²\n\n$situacao\n\n(Consulte a tabela do fabricante para limites detalhados de vão e carga admissível.)"
                    // Salvar dados no DataStore
                    scope.launch {
                        dataStore.salvarLaje(
                            comprimento = chapa.toFloatOrNull() ?: 0f,
                            largura = espessuraConcreto.toFloatOrNull() ?: 0f,
                            altura = carga.toFloatOrNull() ?: 0f
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular Laje")
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
}
