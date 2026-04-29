package com.psinox.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.psinox.data.materiaisData
import com.psinox.data.Material
import com.psinox.utils.PreferencesManager
import com.psinox.utils.ApiHelper
import kotlinx.coroutines.launch
import org.json.JSONArray

@Composable
fun ListaMateriaisScreen() {
    val context = LocalContext.current
    var materiais by remember { mutableStateOf<List<Material>>(materiaisData) }
    var erroApi by remember { mutableStateOf<String?>(null) }

    // Buscar dados da API se configurada
    LaunchedEffect(Unit) {
        val apiUrl = PreferencesManager.getApiUrl(context)
        if (!apiUrl.isNullOrBlank()) {
            val json = ApiHelper.getPerfisFromApi(apiUrl) // Reutilizando helper, espera-se que endpoint retorne lista de materiais
            if (json != null) {
                try {
                    val arr = JSONArray(json)
                    val lista = mutableListOf<Material>()
                    for (i in 0 until arr.length()) {
                        val obj = arr.getJSONObject(i)
                        lista.add(
                            Material(
                                nome = obj.optString("nome"),
                                quantidade = obj.optDouble("quantidade"),
                                unidade = obj.optString("unidade")
                            )
                        )
                    }
                    materiais = lista
                    erroApi = null
                } catch (e: Exception) {
                    erroApi = "Erro ao processar dados da API."
                    materiais = materiaisData
                }
            } else {
                erroApi = "Não foi possível obter dados da API."
                materiais = materiaisData
            }
        } else {
            materiais = materiaisData
            erroApi = null
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lista de Materiais",
            style = MaterialTheme.typography.titleLarge
        )
        if (erroApi != null) {
            Text(erroApi!!, color = MaterialTheme.colorScheme.error)
        }
        materiais.forEach { material ->
            Card(
                modifier = Modifier.fillMaxSize().padding(8.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Material: ${material.nome}", style = MaterialTheme.typography.bodyLarge)
                    Text("Quantidade: ${material.quantidade} ${material.unidade}", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
