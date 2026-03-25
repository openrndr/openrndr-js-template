import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.composition.ClipMode
import org.openrndr.extra.composition.composition
import org.openrndr.extra.composition.drawComposition
import org.openrndr.math.Vector2
import org.openrndr.shape.Circle
import org.openrndr.shape.LineSegment
import org.openrndr.shape.Shape

fun intersectionsTest() = application {
    program {
        // Create a 2D torus
        val outline = Shape(
            listOf(
                Circle(drawer.bounds.center, 200.0).contour.reversed,
                Circle(drawer.bounds.center, 300.0).contour,
            )
        )

        val radius = outline.bounds.dimensions.length / 2
        val off = outline.bounds.center

        val compositions = List(1) {
            // Create compositions featuring horizontal lines
            // visible inside the torus shape. Change the number of
            // lines to make sure the calculations are not cached.
            val num = radius.toInt() + it
            drawComposition {
                lineSegments(List(num) { segNum ->
                    val yNorm = (segNum / (num - 1.0))
                    val x = ((segNum % 2) * 2.0 - 1.0) * radius
                    val y = (yNorm * 2.0 - 1.0) * radius
                    val start = Vector2(-x, y) + off
                    val end = Vector2(x, y) + off
                    LineSegment(start, end)
                })
                clipMode = ClipMode.INTERSECT
                shape(outline)
            }
        }

        extend {
            drawer.clear(ColorRGBa.YELLOW)
            drawer.composition(compositions[0])
        }
    }
}