import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.color.rgb
import kotlin.math.cos

suspend fun main() = application {
    program {
        extend {
            val a = rgb("#ff0000")
            drawer.clear(a)
            drawer.fill = ColorRGBa.WHITE
            drawer.circle(width/2.0, height/2.0, 100.0 + cos(seconds)*40.0)
        }
    }
}