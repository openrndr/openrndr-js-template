import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.url.URLSearchParams

fun main() {
    // Take the GET argument from the URL specifying which program to run. If missing take the first.
    val currentProgram = URLSearchParams(window.location.search).get("program") ?: myApps.keys.first()

    // Launch the selected program
    myApps[currentProgram]!!.invoke()

    // Create a div with clickable links
    val menuDiv = document.getElementById("menu")
    menuDiv?.innerHTML = myApps.keys.joinToString(" ") { programName ->
        if (programName != currentProgram)
            """<a href="?program=$programName">$programName</a> """
        else
            """<span>$programName</span> """
    }

    loadAndHighlight("sources-for-web/${currentProgram}.kt.txt")
}
external fun loadAndHighlight(url: String)
