package com.psinox.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.psinox.data.perfisData
import com.psinox.data.tiposAco
import com.psinox.data.E_ACO_MPA

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PilarScreen(onBack: () -> Unit) {
    var altura by remember { mutableStateOf("") }
    var carga by remember { mutableStateOf("") }
    var perfil by remember { mutableStateOf("") }
    var tipoAco by remember { mutableStateOf(tiposAco.first().nome) }
    var resultado by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf("") }
    var alertaUtilizacao by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 24.dp)) {
        TopAppBar(
            title = { Text("Cálculo do Pilar") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = perfil,
            onValueChange = { perfil = it },
            label = { Text("Perfil (ex: Perfil A)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = altura,
            onValueChange = { altura = it },
            label = { Text("Altura (m)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = carga,
            onValueChange = { carga = it },
            label = { Text("Carga axial (kgf)") },
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
                alertaUtilizacao = ""
                val alturaM = altura.replace(",", ".").toDoubleOrNull()
                val cargaKgf = carga.replace(",", ".").toDoubleOrNull()
                val perfilSelecionado = perfisData.find { it.nome.equals(perfil, ignoreCase = true) }
                val tipoAcoSelecionado = tiposAco.find { it.nome == tipoAco }
                val fy = tipoAcoSelecionado?.fy ?: 235.0
                val E = E_ACO_MPA
                if (alturaM == null || alturaM <= 0) {
                    erro = "Informe uma altura válida."
                    return@Button
                }
                if (cargaKgf == null || cargaKgf <= 0) {
                    erro = "Informe uma carga axial válida."
                    return@Button
                }
                if (perfilSelecionado == null) {
                    erro = "Informe um perfil válido (ex: Perfil A)."
                    return@Button
                }
                // Cálculo realista: usar área do perfil selecionado
                val area_cm2 = perfilSelecionado.areaCm2
                val area_mm2 = area_cm2 * 100.0
                val P_N = cargaKgf * 9.807 * 1.4 // Majorado
                val slenderness = (alturaM * 100) / 2.0 // Exemplo: usar raio de giração real do perfil
                val slendernessLimit = 4.71 * kotlin.math.sqrt(E / fy)
                val fcr_MPa = if (slenderness <= slendernessLimit) {
                    val fe_MPa = (Math.PI * Math.PI * E) / (slenderness * slenderness)
                    Math.pow(0.658, fy / fe_MPa) * fy
                } else {
                    (0.877 * Math.PI * Math.PI * E) / (slenderness * slenderness)
                }
                val allowableStress_MPa = fcr_MPa / 1.67
                val actingStress_MPa = P_N / area_mm2
                val utilizacao = (actingStress_MPa / allowableStress_MPa) * 100
                resultado = "Perfil: ${perfilSelecionado.nome}\nTipo de aço: $tipoAco\nAltura: $alturaM m\nCarga: $cargaKgf kgf\nÁrea: %.2f cm²\n\nTensão atuante: %.2f MPa\nTensão admissível: %.2f MPa\nUtilização: %.1f%%".format(area_cm2, actingStress_MPa, allowableStress_MPa, utilizacao)
                if (utilizacao > 100) {
                    alertaUtilizacao = "Atenção: Pilar NÃO atende! Utilização acima de 100%."
                } else {
                    alertaUtilizacao = "Dimensionamento seguro."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular Pilar")
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
                    if (alertaUtilizacao.isNotBlank()) {
                        Spacer(Modifier.height(12.dp))
                        Text(alertaUtilizacao, color = if (alertaUtilizacao.contains("NÃO atende")) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
