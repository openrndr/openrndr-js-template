import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.url.URLSearchParams

// Add your application functions here
val myApps = mapOf(
    "bouncyBubbles" to ::bouncyBubbles,
    "justGreen" to ::justGreen,
    "fabulousPink" to ::fabulousPink,
)

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

    loadAndHighlight("kotlin/${currentProgram}.kt")
}
external fun loadAndHighlight(url: String)
