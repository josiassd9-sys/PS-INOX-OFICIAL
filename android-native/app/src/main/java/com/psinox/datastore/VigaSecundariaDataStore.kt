package com.psinox.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "viga_secundaria_prefs")

object VigaSecundariaKeys {
    val PERFIL = stringPreferencesKey("perfil")
    val CARGA = floatPreferencesKey("carga")
    val ESPACAMENTO = floatPreferencesKey("espacamento")
    val TIPO_ACO = stringPreferencesKey("tipo_aco")
}

data class VigaSecundariaState(
    val perfil: String = "",
    val carga: Float = 0f,
    val espacamento: Float = 0f,
    val tipoAco: String = "CA-50"
)

class VigaSecundariaDataStore(private val context: Context) {
    val vigaSecundariaData: Flow<VigaSecundariaState> = context.dataStore.data.map { prefs ->
        VigaSecundariaState(
            perfil = prefs[VigaSecundariaKeys.PERFIL] ?: "",
            carga = prefs[VigaSecundariaKeys.CARGA] ?: 0f,
            espacamento = prefs[VigaSecundariaKeys.ESPACAMENTO] ?: 0f,
            tipoAco = prefs[VigaSecundariaKeys.TIPO_ACO] ?: "CA-50"
        )
    }

    suspend fun salvarVigaSecundaria(perfil: String, carga: Float, espacamento: Float, tipoAco: String) {
        context.dataStore.edit { prefs ->
            prefs[VigaSecundariaKeys.PERFIL] = perfil
            prefs[VigaSecundariaKeys.CARGA] = carga
            prefs[VigaSecundariaKeys.ESPACAMENTO] = espacamento
            prefs[VigaSecundariaKeys.TIPO_ACO] = tipoAco
        }
    }
}
