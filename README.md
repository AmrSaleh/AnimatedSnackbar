# AnimatedSnackbar
Android library that provides an animated snack bar.

## Features
- View on demand a custom snack bar from the top of the screen.
- Pass custom message to show in the snack bar.
- The snack bar disappears automatically after a few seconds.
- Dismiss the bar programatically on demand.

### Future plans
- Add more customization features.
- Enhance the readme.md file with images and examples.

## How to
### Using Gradle
- Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
- Step 2. Add the dependency
```
dependencies {
        implementation 'com.github.AmrSaleh:AnimatedSnackbar:Tag'
}
```

