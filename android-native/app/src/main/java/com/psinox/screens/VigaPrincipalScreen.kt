package com.psinox.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewModelScope
import com.psinox.datastore.VigaPrincipalDataStore
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.psinox.data.perfisData
import com.psinox.data.tiposAco
import com.psinox.data.E_ACO_MPA

@OptIn(ExperimentalMaterial3Api::class)
@Composable

@Composable
fun VigaPrincipalScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val dataStore = remember { VigaPrincipalDataStore(context) }
    var perfil by remember { mutableStateOf("") }
    var carga by remember { mutableStateOf("") }
    var vao by remember { mutableStateOf("") }
    var tipoAco by remember { mutableStateOf(tiposAco.first().nome) }
    var resultado by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf("") }
    var alertaWx by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    // Carregar valores salvos ao abrir a tela
    LaunchedEffect(Unit) {
        dataStore.vigaData.collect { state ->
            if (state.comprimento > 0f) vao = state.comprimento.toString()
            if (state.largura > 0f) carga = state.largura.toString()
            if (state.altura > 0f) perfil = state.altura.toString()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Viga Principal") },
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
            Text("Cálculo da Viga Principal", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = perfil,
                onValueChange = { perfil = it },
                label = { Text("Perfil (ex: W200)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = carga,
                onValueChange = { carga = it },
                label = { Text("Carga distribuída (kgf/m)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = vao,
                onValueChange = { vao = it },
                label = { Text("Vão Livre (m)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
                Text("Tipo de Aço:", modifier = Modifier.alignByBaseline())
                Spacer(Modifier.width(8.dp))
                DropdownMenu(
                    expanded = false,
                    onDismissRequest = {},
                    modifier = Modifier
                ) {}
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
            OutlinedTextField(
                value = carga,
                onValueChange = { carga = it },
                label = { Text("Carga na Viga (kgf)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = vao,
                onValueChange = { vao = it },
                label = { Text("Vão Livre (m)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
            Button(
                onClick = {
                    erro = ""
                    resultado = ""
                    alertaWx = ""
                    val vaoM = vao.replace(",", ".").toDoubleOrNull()
                    val cargaKgfM = carga.replace(",", ".").toDoubleOrNull()
                    val perfilSelecionado = perfisData.find { it.nome.equals(perfil, ignoreCase = true) }
                    val tipoAcoSelecionado = tiposAco.find { it.nome == tipoAco }
                    val fy = tipoAcoSelecionado?.fy ?: 235.0
                    val E = E_ACO_MPA
                    if (vaoM == null || vaoM <= 0) {
                        erro = "Informe um vão válido."
                        return@Button
                    }
                    if (cargaKgfM == null || cargaKgfM <= 0) {
                        erro = "Informe uma carga distribuída válida."
                        return@Button
                    }
                    if (perfilSelecionado == null) {
                        erro = "Informe um perfil válido (ex: Perfil A)."
                        return@Button
                    }
                    // Conversão e cálculo simplificado (exemplo):
                    val q_kN_m = cargaKgfM * 0.009807
                    val L_m = vaoM
                    val Msd_kNm = (q_kN_m * L_m * L_m) / 8 * 1.4 // Majorado
                    val Wx_req_cm3 = (Msd_kNm * 100) / (fy / 10)
                    val wxPerfil = perfilSelecionado.wxCm3
                    resultado = "Perfil: ${perfilSelecionado.nome}\nTipo de aço: $tipoAco\nVão: $vaoM m\nCarga: $cargaKgfM kgf/m\n\nMomento solicitante (Msd): %.2f kNm\nWx mínimo requerido: %.2f cm³\nWx do perfil: %.2f cm³".format(Msd_kNm, Wx_req_cm3, wxPerfil)
                    if (wxPerfil < Wx_req_cm3) {
                        alertaWx = "Atenção: Wx do perfil (${String.format("%.2f", wxPerfil)} cm³) é INFERIOR ao mínimo requerido (${String.format("%.2f", Wx_req_cm3)} cm³). Selecione outro perfil!"
                    } else {
                        alertaWx = "Perfil atende ao Wx mínimo requerido."
                    }
                    // Salvar dados no DataStore
                    scope.launch {
                        dataStore.salvarViga(
                            comprimento = vaoM.toFloat(),
                            largura = cargaKgfM.toFloat(),
                            altura = perfil.toFloatOrNull() ?: 0f
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular Viga Principal")
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
                        if (alertaWx.isNotBlank()) {
                            Spacer(Modifier.height(12.dp))
                            Text(alertaWx, color = if (alertaWx.contains("INFERIOR")) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
