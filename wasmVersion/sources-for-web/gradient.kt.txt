import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.shadestyles.linearGradient
import org.openrndr.extra.shadestyles.radialGradient

fun gradient() = application {
    program {
        // ISSUE: when enabling a linear gradient, the whole
        // window becomes white and no circle is visible.
        val gradient = linearGradient(ColorRGBa.BLACK, ColorRGBa.WHITE)
        extend {
            drawer.shadeStyle = gradient
            drawer.circle(drawer.bounds.center, 100.0)
        }
    }
}