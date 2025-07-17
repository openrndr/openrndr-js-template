import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.isolated
import org.openrndr.draw.shadeStyle
import org.openrndr.extra.noise.Random
import org.openrndr.extra.shapes.primitives.grid
import org.openrndr.extra.shapes.primitives.toRounded

fun gridOfRotating() = application {
    program {
        val grid = drawer.bounds.grid(8, 4, 100.0, 100.0, 0.0, 40.0).flatten().map {
            it.offsetEdges(-10.0).toRounded(10.0).contour
        }.filter { Random.bool(0.8) }
        // Issue: when I create a gradient, the canvas becomes white
        // val gradient = linearGradient(ColorRGBa.BLACK, ColorRGBa.WHITE)
        val gradient = shadeStyle {
            fragmentTransform = """
                    vec3 p = sin(va_position.xxy * vec3(0.02, 0.01, 0.03) /* + p_offset*/) * 0.5 + 0.5;
                    x_fill = vec4(p, 1.0);
                """.trimIndent()
        }
        extend {
            drawer.clear(ColorRGBa.PINK)
            grid.forEachIndexed { i, it ->
                drawer.shadeStyle = gradient
                // Issue: WebGL warning: uniform setter: No active linked Program.
                // gradient.parameter("offset", Vector2(i * 3.33, i * 7.77))
                val center = it.bounds.center
                drawer.isolated {
                    drawer.translate(center)
                    val n = Random.simplex((center * 0.01).xy0.copy(z = seconds * 0.1))
                    drawer.rotate(n * 50.0)
                    drawer.translate(-center)
                    drawer.contour(it)
                }
            }
        }
    }
}
