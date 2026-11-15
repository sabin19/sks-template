package com.sks.template.shared

import androidx.compose.ui.window.ComposeUIViewController
import com.sks.template.shared.di.initKoin
import com.sks.template.shared.ui.App
import org.koin.compose.KoinContext

fun MainViewController() = ComposeUIViewController(configure = {
    initKoin()
}) {
    KoinContext {
        App()
    }
}
