import org.jetbrains.kotlin.gradle.targets.js.yarn.yarn

plugins {
    kotlin("js") version "1.7.0"
}
group = "org.example"
version = "1.0-SNAPSHOT"

/*  Which version of OPENRNDR and ORX should be used? */
val openrndrUseSnapshot = true
val openrndrVersion = if (openrndrUseSnapshot) "0.5.1-SNAPSHOT" else "?.?.??"

val orxUseSnapshot = true
val orxVersion = if (orxUseSnapshot) "0.5.1-SNAPSHOT" else "?.?.??"

/*  Which additional multiplatform (ORX) libraries should be added to this project. */
val orxFeatures = setOf<String>(
//    "orx-camera",
//    "orx-color",
//    "orx-compositor",
//    "orx-easing",
    "orx-fx",
//    "orx-gradient-descent",
    "orx-image-fit",
    "orx-noise",
//    "orx-parameters",
//    "orx-shade-styles",
//    "orx-shader-phrases",
//    "orx-quadtree",
)

fun orx(module: String): Any {
    return "org.openrndr.extra:$module:$orxVersion"
}

fun openrndr(module: String): Any {
    return "org.openrndr:openrndr-$module:$openrndrVersion"
}

repositories {
    mavenCentral()
    mavenLocal()
}
dependencies {
    implementation(kotlin("stdlib-js"))
    implementation(kotlin("reflect"))

    implementation(openrndr("application"))
    implementation(openrndr("dds"))
    implementation(openrndr("draw"))
    implementation(openrndr("webgl"))

    for (feature in orxFeatures) {
        implementation(orx(feature))
    }
}

kotlin {
    js(IR) {
        browser {
            webpackTask {
                outputFileName = "openrndr-program.js"
                cssSupport.enabled = true
            }
            runTask {
                outputFileName = "openrndr-program.js"
                cssSupport.enabled = true
            }
        }
        binaries.executable()
    }

    // These are here because webpack-cli 4.9.2 (current default version)
    // throws a TypeError on running the application
    yarn.resolution("webpack", "5.73.0")
    yarn.resolution("webpack-cli", "4.10.0")
}