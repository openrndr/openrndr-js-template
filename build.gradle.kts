plugins {
    id("org.jetbrains.kotlin.js") version "1.5.20"
}
group = "org.example"
version = "1.0-SNAPSHOT"

val orxUseSnapshot = true
val orxVersion = if (orxUseSnapshot) "0.5.1-SNAPSHOT" else "?.?.??"

/*  Which additional (ORX) libraries should be added to this project. */
val orxFeatures = setOf(
//    "orx-boofcv",
//    "orx-camera",
    "orx-color",
//    "orx-compositor",
    "orx-fx",
//    "orx-glslify",
//    "orx-gui",
//    "orx-image-fit",
//    "orx-integral-image",
//    "orx-jumpflood",
//    "orx-mesh-generators",
//    "orx-midi",
//    "orx-no-clear",
//    "orx-noise",
//    "orx-olive",
//    "orx-osc",
//    "orx-palette",
//    "orx-panel",
//    "orx-parameters",
//    "orx-poisson-fill",
//    "orx-shade-styles",
//    "orx-shapes",
//    "orx-triangulation",
//    "orx-video-profiles",
//    "poc-orx-keyframer",
//    "orx-chataigne",
//    "orx-dnk3",
//    "orx-easing",
//    "orx-file-watcher",
//    "orx-filter-extension",
//    "orx-gradient-descent",
//    "orx-interval-tree",
//    "orx-kdtree",
//    "orx-kinect-v1",
//    "orx-obj-loader",
//    "orx-rabbit-control,
//    "orx-runway",
//    "orx-shader-phrases",
//    "orx-syphon",
//    "orx-temporal-blur",
//    "orx-tensorflow",
//    "orx-time-operators,
    null
).filterNotNull()

fun orx(module: String): Any {
    return "org.openrndr.extra:$module:$orxVersion"
}

repositories {
    mavenCentral()
    mavenLocal()
}
dependencies {
    implementation(kotlin("stdlib-js"))
    implementation(kotlin("reflect"))
    implementation("org.openrndr:openrndr-application:0.5.1-SNAPSHOT")
    implementation("org.openrndr:openrndr-dds:0.5.1-SNAPSHOT")
    implementation("org.openrndr:openrndr-draw:0.5.1-SNAPSHOT")
    implementation("org.openrndr:openrndr-webgl:0.5.1-SNAPSHOT")

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
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
        binaries.executable()
    }
}
