import org.openrndr.application
import org.openrndr.draw.shadeStyle

fun shadeStyleParam() = application {
    program {
        val ss = shadeStyle {
            fragmentTransform = "x_fill.rgb = vec3(0.5);"

            // ISSUE: Can't use parameters (uniforms)
            //fragmentTransform = "x_fill.rgb = vec3(p_lum);"
        }
        extend {
            ss.parameter("lum", seconds.mod(1.0))
            drawer.shadeStyle = ss
            drawer.circle(drawer.bounds.center, 100.0)
        }
    }
}