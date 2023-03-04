@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.js)
}

group = "org.example"
version = "1.0-SNAPSHOT"

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
//    "orx-shapes",
//    "orx-quadtree",
)

fun orx(module: String) = "org.openrndr.extra:$module:${libs.versions.orx.get()}"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(libs.kotlin.reflect)

    implementation(libs.openrndr.application)
    implementation(libs.openrndr.dds)
    implementation(libs.openrndr.draw)
    implementation(libs.openrndr.webgl)

    for (feature in orxFeatures) {
        implementation(orx(feature))
    }
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                outputFileName = "openrndr-program.js"
                cssSupport {
                    enabled.set(true)
                }
            }
        }
        binaries.executable()
    }
}

tasks.getByName("browserDevelopmentRun").dependsOn("developmentExecutableCompileSync")