package com.psinox.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "armadura_sapata_prefs")

object ArmaduraSapataKeys {
    val FCK = floatPreferencesKey("fck")
    val FYK = floatPreferencesKey("fyk")
    val DIAM = floatPreferencesKey("diam")
}

data class ArmaduraSapataState(
    val fck: Float = 25f,
    val fyk: Float = 50f,
    val diam: Float = 10f
)

class ArmaduraSapataDataStore(private val context: Context) {
    val armaduraSapataData: Flow<ArmaduraSapataState> = context.dataStore.data.map { prefs ->
        ArmaduraSapataState(
            fck = prefs[ArmaduraSapataKeys.FCK] ?: 25f,
            fyk = prefs[ArmaduraSapataKeys.FYK] ?: 50f,
            diam = prefs[ArmaduraSapataKeys.DIAM] ?: 10f
        )
    }

    suspend fun salvarArmaduraSapata(fck: Float, fyk: Float, diam: Float) {
        context.dataStore.edit { prefs ->
            prefs[ArmaduraSapataKeys.FCK] = fck
            prefs[ArmaduraSapataKeys.FYK] = fyk
            prefs[ArmaduraSapataKeys.DIAM] = diam
        }
    }
}
