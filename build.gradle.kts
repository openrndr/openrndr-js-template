plugins {
    id("org.jetbrains.kotlin.js") version "1.5.20"
}
group = "org.example"
version = "1.0-SNAPSHOT"
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
    implementation("org.openrndr.extra:orx-fx:0.5.1-SNAPSHOT")
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