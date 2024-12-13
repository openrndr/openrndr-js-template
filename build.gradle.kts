@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.versions)
}

group = "org.example"
version = "1.0-SNAPSHOT"

/*  Which additional multiplatform (ORX) libraries should be added to this project. */
val orxFeatures = setOf<String>(
//    "orx-camera",
//    "orx-color",
//    "orx-compositor",
//    "orx-easing",
//    "orx-fx",
//    "orx-gradient-descent",
    "orx-image-fit",
    "orx-noise",
//    "orx-parameters",
//    "orx-shade-styles",
//    "orx-shader-phrases",
      "orx-shapes",
//    "orx-quadtree",
)

fun orx(module: String) = "org.openrndr.extra:$module:${libs.versions.orx.get()}"

repositories {
    mavenCentral()
    mavenLocal()
}

kotlin {
    js {
        browser {
            commonWebpackConfig {
                sourceMaps = false // Double refresh workaround
                outputFileName = "openrndr-program.js"
                cssSupport {
                    enabled = true
                }
            }
        }
        binaries.executable()
    }
    sourceSets {
        commonMain {
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
        }
    }
}

tasks {
    dependencyUpdates {
        gradleReleaseChannel = "current"

        val nonStableKeywords = listOf("alpha", "beta", "rc")

        fun isNonStable(
            version: String
        ) = nonStableKeywords.any {
            version.lowercase().contains(it)
        }

        rejectVersionIf {
            isNonStable(candidate.version) && !isNonStable(currentVersion)
        }
    }
}

