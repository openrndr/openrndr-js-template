import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.shape.Circle

fun justGreen() = application {
    configure {
        title = "OPENRNDR - Just Green"
    }
    program {
        extend {
            drawer.clear(ColorRGBa.GREEN)
            drawer.circle(mouse.position, 150.0)
            drawer.contour(Circle(mouse.position + 75.0, 150.0).contour)
        }
    }
}