import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.noise.Random
import org.openrndr.extra.shapes.hobbycurve.hobbyCurve
import org.openrndr.math.Polar

// See https://kotlinlang.org/docs/js-interop.html#external-modifier
// Reference to a JavaScript function declared in index.html
external fun greet(name: String, f: () -> Unit)

// Reference to a global variable declared in index.html
external var globalCounter: Int

fun main() = application {
    program {
        console.log("Kotlin says OPENRNDR program started")

        extend {
            // Update a JavaScript variable
            globalCounter = frameCount

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
        mouse.buttonDown.listen {
            // Call a JavaScript function
            greet("TemplateProgram.kt") {
                // Here we pass a function to JavaScript, which it will call after some seconds.
                // This is an example of letting JS execute Kotlin functions.
                console.log("Kotlin called back")
            }
        }
    }
}
