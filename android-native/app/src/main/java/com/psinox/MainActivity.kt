package com.psinox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.horizontalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PerfisWTabelaScreen()
            }
        }
    }
}

@Composable
fun PerfisWTabelaScreen() {
    var filtroPeso by remember { mutableStateOf("") }
    var filtroAltura by remember { mutableStateOf("") }
    var filtroWx by remember { mutableStateOf("") }

    // Exemplo de dados estáticos
    val perfis = listOf(
        Perfil("W200", 25.4, 200.0, 100.0, 6.0, 8.0, 1234.0, 234.0, 4.5, 567.0, 89.0, 3.2),
        Perfil("W250", 32.1, 250.0, 125.0, 7.0, 9.0, 2345.0, 345.0, 5.1, 678.0, 101.0, 3.8)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(8.dp)
    ) {
        // CARD SUPERIOR
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Tabela de Perfis W", style = MaterialTheme.typography.titleLarge)
                Text(
                    "Consulte as propriedades geométricas e físicas dos perfis de aço padrão W (Gerdau/Açominas).",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = filtroPeso,
                        onValueChange = { filtroPeso = it },
                        label = { Text("Peso ≥ (kg/m)") },
                        placeholder = { Text("Ex: 20") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = filtroAltura,
                        onValueChange = { filtroAltura = it },
                        label = { Text("Altura ≥ (mm)") },
                        placeholder = { Text("Ex: 250") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = filtroWx,
                        onValueChange = { filtroWx = it },
                        label = { Text("Wx ≥ (cm³)") },
                        placeholder = { Text("Ex: 300") },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        // CARD INFERIOR: TABELA ROLÁVEL
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .horizontalScroll(rememberScrollState())
            ) {
                Column {
                    Row {
                        listOf("Perfil", "Peso", "h", "b", "tw", "tf", "Ix", "Wx", "rx", "Iy", "Wy", "ry").forEach {
                            Text(it, modifier = Modifier.width(80.dp).padding(4.dp), fontWeight = FontWeight.Bold)
                        }
                    }
                    perfis.forEach { perfil ->
                        Row {
                            Text(perfil.nome, modifier = Modifier.width(80.dp).padding(4.dp))
                            Text(perfil.peso.toString(), modifier = Modifier.width(80.dp).padding(4.dp))
                            Text(perfil.h.toString(), modifier = Modifier.width(80.dp).padding(4.dp))
                            Text(perfil.b.toString(), modifier = Modifier.width(80.dp).padding(4.dp))
                            Text(perfil.tw.toString(), modifier = Modifier.width(80.dp).padding(4.dp))
                            Text(perfil.tf.toString(), modifier = Modifier.width(80.dp).padding(4.dp))
                            Text(perfil.Ix.toString(), modifier = Modifier.width(80.dp).padding(4.dp))
                            Text(perfil.Wx.toString(), modifier = Modifier.width(80.dp).padding(4.dp))
                            Text(perfil.rx.toString(), modifier = Modifier.width(80.dp).padding(4.dp))
                            Text(perfil.Iy.toString(), modifier = Modifier.width(80.dp).padding(4.dp))
                            Text(perfil.Wy.toString(), modifier = Modifier.width(80.dp).padding(4.dp))
                            Text(perfil.ry.toString(), modifier = Modifier.width(80.dp).padding(4.dp))
                        }
                    }
                }
            }
        }
    }
}

data class Perfil(
    val nome: String,
    val peso: Double,
    val h: Double,
    val b: Double,
    val tw: Double,
    val tf: Double,
    val Ix: Double,
    val Wx: Double,
    val rx: Double,
    val Iy: Double,
    val Wy: Double,
    val ry: Double
)
