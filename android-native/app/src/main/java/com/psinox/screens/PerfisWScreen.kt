package com.psinox.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.psinox.data.perfisData
import com.psinox.data.Perfil
import com.psinox.components.AppCard
import com.psinox.components.AppInput
import com.psinox.components.PerfisTable
import com.psinox.utils.PreferencesManager
import com.psinox.utils.ApiHelper
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import org.json.JSONArray

@Composable
fun PerfisWScreen() {
    val context = LocalContext.current
    var filtroPeso by remember { mutableStateOf("") }
    var filtroAltura by remember { mutableStateOf("") }
    var filtroWx by remember { mutableStateOf("") }
    var perfis by remember { mutableStateOf<List<Perfil>>(perfisData) }
    var erroApi by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    // Buscar dados da API se configurada
    LaunchedEffect(Unit) {
        val apiUrl = PreferencesManager.getApiUrl(context)
        if (!apiUrl.isNullOrBlank()) {
            val json = ApiHelper.getPerfisFromApi(apiUrl)
            if (json != null) {
                try {
                    val arr = JSONArray(json)
                    val lista = mutableListOf<Perfil>()
                    for (i in 0 until arr.length()) {
                        val obj = arr.getJSONObject(i)
                        lista.add(
                            Perfil(
                                nome = obj.optString("nome"),
                                peso = obj.optDouble("peso"),
                                h = obj.optDouble("h"),
                                b = obj.optDouble("b"),
                                tw = obj.optDouble("tw"),
                                tf = obj.optDouble("tf"),
                                Ix = obj.optDouble("Ix"),
                                Wx = obj.optDouble("Wx"),
                                rx = obj.optDouble("rx"),
                                Iy = obj.optDouble("Iy"),
                                Wy = obj.optDouble("Wy"),
                                ry = obj.optDouble("ry")
                            )
                        )
                    }
                    perfis = lista
                    erroApi = null
                } catch (e: Exception) {
                    erroApi = "Erro ao processar dados da API."
                    perfis = perfisData
                }
            } else {
                erroApi = "Não foi possível obter dados da API."
                perfis = perfisData
            }
        } else {
            perfis = perfisData
            erroApi = null
        }
    }

    val perfisFiltrados = perfis.filter { perfil ->
        val pesoOk = filtroPeso.toDoubleOrNull()?.let { perfil.peso >= it } ?: true
        val alturaOk = filtroAltura.toDoubleOrNull()?.let { perfil.h >= it } ?: true
        val wxOk = filtroWx.toDoubleOrNull()?.let { perfil.Wx >= it } ?: true
        pesoOk && alturaOk && wxOk
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(8.dp)
    ) {
        AppCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Tabela de Perfis W", style = MaterialTheme.typography.titleLarge)
                Text(
                    "Consulte as propriedades geométricas e físicas dos perfis de aço padrão W (Gerdau/Açominas).",
                    style = MaterialTheme.typography.bodySmall
                )
                if (erroApi != null) {
                    Text(erroApi!!, color = MaterialTheme.colorScheme.error)
                }
                Spacer(Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    AppInput(
                        value = filtroPeso,
                        onValueChange = { filtroPeso = it },
                        label = "Peso ≥ (kg/m)",
                        placeholder = "Ex: 20",
                        modifier = Modifier.weight(1f)
                    )
                    AppInput(
                        value = filtroAltura,
                        onValueChange = { filtroAltura = it },
                        label = "Altura ≥ (mm)",
                        placeholder = "Ex: 250",
                        modifier = Modifier.weight(1f)
                    )
                    AppInput(
                        value = filtroWx,
                        onValueChange = { filtroWx = it },
                        label = "Wx ≥ (cm³)",
                        placeholder = "Ex: 300",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        AppCard(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                PerfisTable(perfisFiltrados)
            }
        }
    }
}
