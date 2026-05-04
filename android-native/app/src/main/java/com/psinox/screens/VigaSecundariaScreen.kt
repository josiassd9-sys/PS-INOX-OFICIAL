package com.psinox.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.psinox.datastore.VigaSecundariaDataStore
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.psinox.data.perfisData
import com.psinox.data.tiposAco
import com.psinox.data.E_ACO_MPA

@OptIn(ExperimentalMaterial3Api::class)
@Composable

@Composable
@Composable
fun VigaSecundariaScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val dataStore = remember { VigaSecundariaDataStore(context) }
    val scope = rememberCoroutineScope()
    var perfil by remember { mutableStateOf("") }
    var carga by remember { mutableStateOf("") }
    var espacamento by remember { mutableStateOf("") }
    var tipoAco by remember { mutableStateOf(tiposAco.first().nome) }

    // Carregar valores salvos ao abrir a tela
    LaunchedEffect(Unit) {
        dataStore.vigaSecundariaData.collect { state ->
            if (state.perfil.isNotBlank()) perfil = state.perfil
            if (state.carga > 0f) carga = state.carga.toString()
            if (state.espacamento > 0f) espacamento = state.espacamento.toString()
            if (state.tipoAco.isNotBlank()) tipoAco = state.tipoAco
        }
    }
    var resultado by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf("") }

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
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
                Text("Tipo de Aço:", modifier = Modifier.alignByBaseline())
                Spacer(Modifier.width(8.dp))
                var expanded by remember { mutableStateOf(false) }
                Box {
                    Button(onClick = { expanded = true }) {
                        Text(tipoAco)
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        tiposAco.forEach { t ->
                            DropdownMenuItem(text = { Text(t.nome) }, onClick = {
                                tipoAco = t.nome
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
                    val espacamentoM = espacamento.replace(",", ".").toDoubleOrNull()
                    val cargaKgf = carga.replace(",", ".").toDoubleOrNull()
                    val perfilInformado = perfil.trim().uppercase()
                    val tipoAcoSelecionado = tiposAco.find { it.nome == tipoAco }
                    val fy = tipoAcoSelecionado?.fy ?: 235.0
                    if (perfilInformado.isBlank() || !perfilInformado.startsWith("IPE")) {
                        erro = "Informe um perfil IPE válido (ex: IPE200)."
                        return@Button
                    }
                    if (cargaKgf == null || cargaKgf <= 0) {
                        erro = "Informe uma carga válida."
                        return@Button
                    }
                    if (espacamentoM == null || espacamentoM <= 0) {
                        erro = "Informe um espaçamento válido."
                        return@Button
                    }
                    // Cálculo: viga simplesmente apoiada, carga pontual (viga principal transfere para a secundária)
                    // Considerando carga total na viga secundária = cargaKgf
                    val L_m = espacamentoM
                    val q_kN_m = cargaKgf * 0.009807 // conversão kgf/m para kN/m
                    val Msd_kNm = (q_kN_m * L_m * L_m) / 8 * 1.4 // Majorado
                    val Wx_req_cm3 = (Msd_kNm * 100) / (fy / 10)
                    resultado = "Perfil: $perfilInformado\nTipo de aço: $tipoAco\nEspaçamento: $espacamentoM m\nCarga: $cargaKgf kgf\n\nMomento solicitante (Msd): %.2f kNm\nWx mínimo requerido: %.2f cm³\n\n(Consulte Wx do perfil IPE informado e compare com o requerido para validar a segurança.)".format(Msd_kNm, Wx_req_cm3)
                    // Salvar dados no DataStore
                    scope.launch {
                        dataStore.salvarVigaSecundaria(
                            perfil = perfil,
                            carga = cargaKgf.toFloat(),
                            espacamento = espacamentoM.toFloat(),
                            tipoAco = tipoAco
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular Viga Secundária")
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
