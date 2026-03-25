import org.openrndr.application
import org.openrndr.color.ColorRGBa

fun justGreen() = application {
    configure {
        title = "OPENRNDR - Just Green"
    }
    program {
        extend {
            drawer.clear(ColorRGBa.GREEN)
            drawer.circle(mouse.position, 150.0)
        }
    }
}