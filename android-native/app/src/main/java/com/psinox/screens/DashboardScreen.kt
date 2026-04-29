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
import com.psinox.data.dashboardInfoMock
import com.psinox.data.DashboardInfo
import com.psinox.utils.PreferencesManager
import com.psinox.utils.ApiHelper
import org.json.JSONObject

@Composable
fun DashboardScreen() {
    val context = LocalContext.current
    var info by remember { mutableStateOf(dashboardInfoMock) }
    var erroApi by remember { mutableStateOf<String?>(null) }

    // Buscar dados da API se configurada
    LaunchedEffect(Unit) {
        val apiUrl = PreferencesManager.getApiUrl(context)
        if (!apiUrl.isNullOrBlank()) {
            val json = ApiHelper.getPerfisFromApi(apiUrl) // Espera-se que endpoint retorne objeto dashboard
            if (json != null) {
                try {
                    val obj = JSONObject(json)
                    info = DashboardInfo(
                        totalPerfis = obj.optInt("totalPerfis"),
                        totalMateriais = obj.optInt("totalMateriais"),
                        usuario = obj.optString("usuario")
                    )
                    erroApi = null
                } catch (e: Exception) {
                    erroApi = "Erro ao processar dados da API."
                    info = dashboardInfoMock
                }
            } else {
                erroApi = "Não foi possível obter dados da API."
                info = dashboardInfoMock
            }
        } else {
            info = dashboardInfoMock
            erroApi = null
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Dashboard",
            style = MaterialTheme.typography.titleLarge
        )
        if (erroApi != null) {
            Text(erroApi!!, color = MaterialTheme.colorScheme.error)
        }
        Card(
            modifier = Modifier,
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Total de Perfis: ${info.totalPerfis}", style = MaterialTheme.typography.bodyLarge)
                Text("Total de Materiais: ${info.totalMateriais}", style = MaterialTheme.typography.bodyLarge)
                Text("Usuário: ${info.usuario}", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
