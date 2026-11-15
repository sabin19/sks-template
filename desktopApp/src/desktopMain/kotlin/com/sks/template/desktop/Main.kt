package com.sks.template.desktop

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.sks.template.shared.di.initKoin
import com.sks.template.shared.ui.App

fun main() {
    initKoin()
    application {
        val windowState = rememberWindowState(width = 1200.dp, height = 800.dp)
        Window(
            onCloseRequest = ::exitApplication,
            title = "SKS Template",
            state = windowState,
        ) {
            App()
        }
    }
}
