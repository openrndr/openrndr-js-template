# OPENRNDR JavaScript template project

This is a template project for creating [OPENRNDR](https://openrndr.org/) programs specifically with Kotlin/JS.
For making a standard Kotlin/JVM-based application,
see [openrndr-template](https://github.com/openrndr/openrndr-template).

## Developing

To get started run 

```bash
./gradlew jsRun -t
```

This will start a local development server with hot-reloading. 

Any changes saved under `/src/commonMain/kotlin/` will be reflected 
in the browser.

This template includes three sample OPENRNDR programs. These programs are
displayed as clickable buttons in the resulting web page. For educational purposes, a `source`
button is displayed at the bottom right to view the source code of
the program.

After adding, renaming or removing programs from this project one should update `appList.kt`. Run the following Gradle task to generate the file automatically

```bash
./gradlew "update appList"
```

## Exporting

During development the produced JavaScript program occupies a few megabytes.
Once the project is ready to be shared, one can export a minimized executable by running

```bash
./gradlew jsBrowserProductionWebpack
./gradlew jsBrowserDistribution
```

This will place the resulting files into the
 `build/dist/js/productionExecutable/` folder.

## JavaScript communication

In some cases it can be useful to include additional JavaScript in the HTML template,
for instance to have the Kotlin program communicate with a remote web server, 
to synthesize sound using the web audio API or to interact with an HTML form or GUI.

Read about Kotlin <-> JavaScript communication at
https://kotlinlang.org/docs/js-interop.html#external-modifier

### A. Kotlin talking to JavaScript

This is an example of having Kotlin update a JavaScript variable, and call a JavaScript function.

#### TemplateProgram.kt

```kotlin
// Reference to a JavaScript variable declared in index.html
external var globalCounter: Int

// Reference to a JavaScript function declared in index.html
external fun greet(name: String)

fun main() = application {
    program {
        extend {
            // Update a JavaScript variable
            globalCounter = frameCount

            // ...
        }
        mouse.buttonDown.listen {
            // Call a JavaScript function
            greet("TemplateProgram.kt")
        }
    }
}
```

#### index.html
```html
<script language="JavaScript">
    let globalCounter = 0;
    function greet(name) {
        console.log("JS says hello, " + name + "! " + globalCounter);
    }
</script>
```

### B. JavaScript calling a Kotlin function

This example shows how to pass a Kotlin callback function to
JavaScript. The function is stored, then called every
5 seconds. This approach can be used if we need our visuals to react to JavaScript events.

#### TemplateProgram.kt

```kotlin
// Reference to a JavaScript function declared in index.html
// The function takes one argument: a Kotlin function.
external fun setCallback(f: () -> Unit)

fun main() = application {
    program {
        // Pass a Kotlin function to JavaScript
        setCallback {
            // In this simple example we just log something
            console.log("Interval Kotlin")
        }

        extend {
            // ...
        }
    }
}
```

#### index.html

```html
<script language="JavaScript">
    let callback = function() {}
    
    // Store the received function for later use 
    function setCallback(cb) {
        callback = cb;
    }
    
    // Execute `callback` every 5 seconds
    setInterval(function() {
        console.log("Interval JS");
        callback();
    }, 5000);
</script>
```
