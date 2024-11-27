# OPENRNDR JavaScript template project

This is a template project for creating [OPENRNDR](https://openrndr.org/) programs specifically with Kotlin/JS.
For making a standard Kotlin/JVM-based application,
see [openrndr-template](https://github.com/openrndr/openrndr-template).

## Developing

To get started developing, run 

```bash
./gradlew jsRun -t
```

This will start a local development server with hot-reloading. 

Any changes saved in `/src/commonMain/kotlin/TemplateProgram.kt` will be reflected 
in the browser.

You can study and modify the `/src/commonMain/resources/index.html` HTML template used
to host your program.

## Exporting

During development the produced JavaScript program occupies a few megabytes.
Once the project is ready to be shared, one can export a minimized executable by running

```bash
./gradlew jsBrowserDistribution
```

This will place an `index.html` file and the minimized `openrndr-program.js`
under the `build/dist/js/productionExecutable/` folder. The file size should be
reduced to a few hundred kilobytes, 20 times smaller than during development.

## JavaScript communication

In some cases it can be useful to include additional JavaScript in the HTML template,
for instance to have the Kotlin program communicate with a remote web server, 
to synthesize sound using the web audio API or to interact with an HTML form or GUI.

Your Kotlin program can communicate with JavaScript. You can read about it at
https://kotlinlang.org/docs/js-interop.html#external-modifier

### Kotlin talking to JavaScript

...