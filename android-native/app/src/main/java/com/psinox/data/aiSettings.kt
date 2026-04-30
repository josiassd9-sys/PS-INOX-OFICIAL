package com.psinox.data

data class AISettings(
    val enabled: Boolean,
    val apiKey: String
)

val aiSettings = AISettings(
    enabled = false,
    apiKey = ""
)
