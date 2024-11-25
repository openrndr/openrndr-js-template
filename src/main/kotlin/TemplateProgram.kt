import org.openrndr.applicationAsync
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.noise.Random
import org.openrndr.extra.shapes.hobbyCurve
import org.openrndr.math.Polar

// When targeting JavaScript we use `suspend` and `applicationAsync`.
// The rest of the code is identical to an OPENRNDR program running in the JVM.
suspend fun main() = applicationAsync {
    program {
        extend {
            drawer.clear(ColorRGBa.PINK)
            drawer.fill = ColorRGBa.WHITE

            // Create a List of points centered in the window.
            // Use polar coordinates. The radius is animated using simplex noise.
            val points = List(12) {
                val angle = it * 30.0
                val radius = 200.0 + Random.simplex(it * 0.1, it * 1.7 + seconds * 0.2) * 100.0
                drawer.bounds.center + Polar(angle, radius).cartesian
            }
            // Construct and draw a closed Hobby curve with the points.
            drawer.contour(hobbyCurve(points, true))
        }
    }
}
