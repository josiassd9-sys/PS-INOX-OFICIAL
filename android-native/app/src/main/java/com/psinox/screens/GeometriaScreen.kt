package com.psinox.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.psinox.datastore.GeometriaDataStore
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeometriaScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val dataStore = remember { GeometriaDataStore(context) }
    val scope = rememberCoroutineScope()
    var vaoX by remember { mutableStateOf("") }
    var vaoY by remember { mutableStateOf("") }
    var balancoXEsq by remember { mutableStateOf("") }
    var balancoXDir by remember { mutableStateOf("") }
    var balancoYSup by remember { mutableStateOf("") }
    var balancoYInf by remember { mutableStateOf("") }

    // Carregar valores salvos ao abrir a tela
    LaunchedEffect(Unit) {
        dataStore.geometriaData.collect { state ->
            if (state.vaoX > 0f) vaoX = state.vaoX.toString()
            if (state.vaoY > 0f) vaoY = state.vaoY.toString()
            if (state.balancoXEsq > 0f) balancoXEsq = state.balancoXEsq.toString()
            if (state.balancoXDir > 0f) balancoXDir = state.balancoXDir.toString()
            if (state.balancoYSup > 0f) balancoYSup = state.balancoYSup.toString()
            if (state.balancoYInf > 0f) balancoYInf = state.balancoYInf.toString()
        }
    }
    var resultado by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Geometria da Laje") },
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
            Text("Parâmetros da Laje (em metros)", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = vaoX,
                onValueChange = { vaoX = it },
                label = { Text("Vão em X (principal)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = vaoY,
                onValueChange = { vaoY = it },
                label = { Text("Vão em Y (secundário)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = balancoXEsq,
                onValueChange = { balancoXEsq = it },
                label = { Text("Balanço em X (esquerda)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = balancoXDir,
                onValueChange = { balancoXDir = it },
                label = { Text("Balanço em X (direita)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = balancoYSup,
                onValueChange = { balancoYSup = it },
                label = { Text("Balanço em Y (superior)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = balancoYInf,
                onValueChange = { balancoYInf = it },
                label = { Text("Balanço em Y (inferior)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
            Button(
                onClick = {
                    val vX = vaoX.toDoubleOrNull() ?: 0.0
                    val vY = vaoY.toDoubleOrNull() ?: 0.0
                    val bXEsq = balancoXEsq.toDoubleOrNull() ?: 0.0
                    val bXDir = balancoXDir.toDoubleOrNull() ?: 0.0
                    val bYSup = balancoYSup.toDoubleOrNull() ?: 0.0
                    val bYInf = balancoYInf.toDoubleOrNull() ?: 0.0
                    resultado = analisarGeometria(vX, vY, bXEsq, bXDir, bYSup, bYInf)
                    // Salvar dados no DataStore
                    scope.launch {
                        dataStore.salvarGeometria(
                            vaoX = vX.toFloat(),
                            vaoY = vY.toFloat(),
                            balancoXEsq = bXEsq.toFloat(),
                            balancoXDir = bXDir.toFloat(),
                            balancoYSup = bYSup.toFloat(),
                            balancoYInf = bYInf.toFloat()
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Analisar Geometria")
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

fun analisarGeometria(
    vaoX: Double,
    vaoY: Double,
    balancoXEsq: Double,
    balancoXDir: Double,
    balancoYSup: Double,
    balancoYInf: Double
): String {
    // Lógica simplificada baseada no fluxo do híbrido
    val builder = StringBuilder()
    builder.appendLine("Análise da Geometria da Laje:\n")
    builder.appendLine("1. Eixo X (Vigas Principais):")
    builder.appendLine("   - Vão Livre Principal: %.2f m.".format(vaoX))
    if (vaoX > 12) {
        builder.appendLine("   - Insight: Vão grande. A deformação (flexa) será um fator crítico no dimensionamento da viga principal. Perfis mais altos (maior inércia) serão necessários.")
    } else if (vaoX > 6) {
        builder.appendLine("   - Insight: Vão médio. Um bom equilíbrio entre resistência e deformação será necessário.")
    } else {
        builder.appendLine("   - Insight: Vão pequeno. O dimensionamento provavelmente será governado pela resistência (momento fletor) em vez da deformação.")
    }
    val balancoXTotal = balancoXEsq + balancoXDir
    if (balancoXTotal > 0) {
        val proporcaoX_E = if (vaoX > 0) (balancoXEsq / vaoX) * 100 else 0.0
        val proporcaoX_D = if (vaoX > 0) (balancoXDir / vaoX) * 100 else 0.0
        if (proporcaoX_E > 35 || proporcaoX_D > 35) {
            builder.appendLine("   - Alerta de Balanço Excessivo (Eixo X): Um ou ambos os balanços (Esquerdo: %.1f%%, Direito: %.1f%%) excedem 35%% do vão principal. Isso pode gerar momentos fletores negativos elevados na viga principal, exigindo uma análise cuidadosa e, possivelmente, um aumento da seção do perfil.".format(proporcaoX_E, proporcaoX_D))
        } else {
            builder.appendLine("   - Balanços (Eixo X): Os balanços estão dentro de uma proporção comum em relação ao vão principal.")
        }
    }
    builder.appendLine()
    builder.appendLine("2. Eixo Y (Vigas Secundárias):")
    builder.appendLine("   - Vão Livre Secundário: %.2f m.".format(vaoY))
    if (vaoY > vaoX) {
        builder.appendLine("   - Alerta Estrutural: O vão das vigas secundárias (%.2f m) é maior que o das vigas principais (%.2f m). Isso é incomum e pode não ser a solução mais econômica. Geralmente, as vigas principais vencem o maior vão.".format(vaoY, vaoX))
    }
    if (vaoY > 8) {
        builder.appendLine("   - Insight: Vão secundário longo. Pode ser necessário um número maior de vigas secundárias ou perfis secundários mais robustos para garantir a estabilidade e limitar a deformação da laje.")
    } else {
        builder.appendLine("   - Insight: Vão secundário padrão. O dimensionamento das vigas secundárias deve seguir as práticas comuns.")
    }
    val balancoYTotal = balancoYSup + balancoYInf
    if (balancoYTotal > 0) {
        val proporcaoY_S = if (vaoY > 0) (balancoYSup / vaoY) * 100 else 0.0
        val proporcaoY_I = if (vaoY > 0) (balancoYInf / vaoY) * 100 else 0.0
        if (proporcaoY_S > 40 || proporcaoY_I > 40) {
            builder.appendLine("   - Alerta de Balanço Excessivo (Eixo Y): Balanços (%.1f%% e %.1f%%) nas extremidades das vigas secundárias são grandes. Isso pode afetar a estabilidade e a distribuição de carga nas vigas principais.".format(proporcaoY_S, proporcaoY_I))
        }
    }
    builder.appendLine()
    val relacaoLados = if (vaoY > 0) vaoX / vaoY else 0.0
    builder.appendLine("3. Relação de Lados (Vão X / Vão Y): %.2f".format(relacaoLados))
    if (relacaoLados > 2) {
        builder.appendLine("   - Comportamento da Laje: Laje armada em uma direção. A carga será predominantemente transferida ao longo do menor vão (Eixo Y). As vigas secundárias são os elementos principais de carga.")
    } else if (relacaoLados > 0.5) {
        builder.appendLine("   - Comportamento da Laje: Laje armada em duas direções. A carga será distribuída tanto para as vigas principais quanto para as secundárias. É uma solução eficiente.")
    } else {
        builder.appendLine("   - Comportamento da Laje: Laje armada em uma direção (sentido X). A carga será predominantemente transferida ao longo do menor vão (Eixo X). As vigas principais são os elementos principais de carga.")
    }
    builder.appendLine()
    builder.appendLine("**Conclusão e Recomendações:**")
    if (vaoX > 12 || vaoY > vaoX || (vaoX > 0 && (balancoXEsq / vaoX > 0.35)) || (vaoX > 0 && (balancoXDir / vaoX > 0.35))) {
        builder.appendLine("A geometria apresenta desafios significativos que exigirão uma análise estrutural detalhada. Recomenda-se atenção especial ao dimensionamento das vigas principais devido ao vão longo e/ou balanços excessivos, e reavaliação da orientação dos vãos.")
    } else {
        builder.appendLine("A configuração geométrica parece razoável e segue as práticas comuns de engenharia. O pré-dimensionamento pode prosseguir com base nesta configuração, focando em um equilíbrio otimizado entre os perfis.")
    }
    return builder.toString()
}
