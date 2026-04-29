package com.psinox.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.psinox.data.Perfil

@Composable
fun PerfisTable(perfis: List<Perfil>) {
    val headers = listOf("Perfil", "Peso", "h", "b", "tw", "tf", "Ix", "Wx", "rx", "Iy", "Wy", "ry")
    Row {
        headers.forEach {
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
