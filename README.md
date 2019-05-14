# AnimatedSnackbar
Android library that provides an animated snack bar.

## Features
- View on demand a custom snack bar from the top of the screen.
- Pass custom message to show in the snack bar.
- The snack bar disappears automatically after a few seconds.
- Dismiss the bar programatically on demand.

## Showcase
<img src="https://user-images.githubusercontent.com/5616594/57711888-22d70b80-765f-11e9-86ce-2907ac0ddb58.gif" alt="GIF1" width="430"/> <img src="https://user-images.githubusercontent.com/5616594/57711890-22d70b80-765f-11e9-945e-80d2b9a77061.gif" alt="GIF2" width="430"/>


## Customization

- You can customize multiple features in the snackbar:
  - Background color or drawable.
  - Text message color.
  - Icon drawable and tint.
  - Text Font. (Planned)
  - Auto hide duration. (Planned)
  - Click behaviour. (Planned)

### Future plans
- Add more customization features.
- Enhance the readme.md file with images and examples.

## How to
### Import Using Gradle
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

### Usage in your project
More description is planned to be added here, but for now please refer to the included example project.

