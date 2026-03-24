import web.dom.ElementId
import web.dom.document
import web.html.HtmlSource
import web.url.URLSearchParams
import web.window.window
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.toJsString

@OptIn(ExperimentalWasmJsInterop::class)
fun main() {

    // Take the GET argument from the URL specifying which program to run. If missing take the first.
    val currentProgram = (URLSearchParams(window.location.search).get("program".toJsString()) ?: myApps.keys.first()).toString()

    // Launch the selected program
    myApps[currentProgram]?.invoke()

    // Create a div with clickable links
    val menuDiv = document.getElementById(ElementId("menu"))
    val menu = myApps.keys.joinToString(" ") { programName ->
        if (programName != currentProgram)
            """<a href="?program=$programName">$programName</a>"""
        else
            """<span>$programName</span>"""
    }
    menuDiv?.innerHTML = HtmlSource(menu)

    loadAndHighlight("sources-for-web/${currentProgram}.kt.txt")
}
external fun loadAndHighlight(url: String)
