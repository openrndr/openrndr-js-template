# OPENRNDR JavaScript template project

This is a template project for creating [OPENRNDR](https://openrndr.org/) programs specifically with Kotlin/JS.
For making a standard Kotlin/JVM-based application,
see [openrndr-template](https://github.com/openrndr/openrndr-template).

## Developing

To get started developing, run 

```bash
./gradlew browserDevelopmentRun --continuous
```

This will start a local development server with hot-reloading. 

Any changes you make in `/src/main/kotlin` will be reflected in the browser.

## Exporting

When the project is ready to be shared, one can export a minimized executable by running

```bash
./gradlew browserDistribution
```

This will place an `index.html` file and the `openrndr-program.js`
under the `build/dist/js/productionExecutable/` folder.

## next-version branch

Note that there is a `next-version` branch which may offer better performance or
new features.

