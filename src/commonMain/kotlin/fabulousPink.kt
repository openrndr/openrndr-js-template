import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.noise.Random
import org.openrndr.extra.shapes.hobbycurve.hobbyCurve
import org.openrndr.math.Polar

fun fabulousPink() = application {
    configure {
        title = "OPENRNDR - Fabulous Pink"
    }
    program {
        console.log("Kotlin says OPENRNDR program started")

        extend {
            drawer.clear(ColorRGBa.PINK)

            drawer.fill = null
            drawer.circle(drawer.bounds.center, 250.0)

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
    }}