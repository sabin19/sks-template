import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.sks.template.shared.di.initKoin
import com.sks.template.shared.ui.App
import kotlinx.browser.document
import kotlinx.browser.window
import org.koin.compose.KoinContext

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val startApp = {
        initKoin()
        val container = checkNotNull(document.getElementById("composeApp")) {
            "Element with id 'composeApp' not found in document"
        }
        ComposeViewport(container) {
            KoinContext {
                App()
            }
        }
    }

    if (document.getElementById("composeApp") != null) {
        startApp()
    } else {
        window.onload = { startApp() }
    }
}
