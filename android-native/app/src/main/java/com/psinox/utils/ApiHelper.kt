package com.psinox.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object ApiHelper {
    suspend fun getPerfisFromApi(apiUrl: String): String? = withContext(Dispatchers.IO) {
        try {
            val url = URL(apiUrl)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.connectTimeout = 5000
            conn.readTimeout = 5000
            val responseCode = conn.responseCode
            if (responseCode == 200) {
                val reader = BufferedReader(InputStreamReader(conn.inputStream))
                val result = reader.readText()
                reader.close()
                result
            } else {
                Log.e("ApiHelper", "Erro HTTP: $responseCode")
                null
            }
        } catch (e: Exception) {
            Log.e("ApiHelper", "Erro ao buscar API", e)
            null
        }
    }
}
