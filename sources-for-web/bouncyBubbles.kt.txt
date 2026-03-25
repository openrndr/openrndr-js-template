import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.color.rgb
import org.openrndr.extra.noise.Random
import org.openrndr.extra.noise.shapes.uniform
import org.openrndr.math.Vector2
import org.openrndr.shape.clamp

// Based on https://github.com/processing/processing-website/blob/main/content/examples/Topics/Motion/BouncyBubbles/BouncyBubbles.pde

fun bouncyBubbles() = application {
    configure {
        title = "OPENRNDR - Bouncy Bubbles"
    }

    program {
        val numBalls = 12
        val spring = 0.05
        val gravity = Vector2(0.0, 0.03)
        val friction = -0.9

        class Ball(var position: Vector2, val radius: Double, val id: Int) {
            var velocity = Vector2.ZERO
            val safeArea = drawer.bounds.offsetEdges(-radius)

            fun collide(others: List<Ball>) {
                for (i in id + 1 until numBalls) {
                    val diff = others[i].position - position
                    val minDist = others[i].radius + radius
                    if (diff.length < minDist) {
                        val targetPos = position + diff.normalized * minDist
                        val acceleration = (targetPos - others[i].position) * spring
                        velocity -= acceleration
                        others[i].velocity += acceleration
                    }
                }
            }

            fun move() {
                velocity += gravity
                position += velocity
                if (position.x !in radius..width - radius) {
                    velocity *= Vector2(friction, 1.0)
                }
                if (position.y !in radius..height - radius) {
                    velocity *= Vector2(1.0, friction)
                }
                position = position.clamp(safeArea)
            }

            fun display() {
                drawer.circle(position, radius)
            }
        }

        val balls = List(numBalls) { Ball(drawer.bounds.uniform(), Random.double(20.0, 60.0), it) }

        extend {
            drawer.clear(rgb("#282622"))
            drawer.stroke = null
            drawer.fill = ColorRGBa.WHITE.opacify(0.8)
            balls.forEach {
                it.collide(balls)
                it.move()
                it.display()
            }
        }
    }
}
