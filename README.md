# AnimatedSnackbar
Android library that provides an animated customizable snack bar.

## Features
- View on demand a custom snack bar from the top of the screen.
- Pass custom message to show in the snack bar.
- The snack bar disappears automatically after a few seconds.
- Dismiss the bar programatically on demand.
- Customize various features like background, font, icon and tint.

## Showcase
These are GIFs and may take a few seconds to load if you have a slow connection.
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
```Groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
- Step 2. Add the dependency to your app build.gradle
```Groovy
dependencies {
        implementation 'com.github.AmrSaleh:AnimatedSnackbar:v1.1' // Make sure to replace with latest version tag
}
```

### Usage in your project
#### Add the AnimatedSnackbar in your XML layout
```XML
<com.amrsaleh.animatedsnackbarlib.AnimatedSnackbar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/snackbar"
        app:layout_constraintTop_toTopOf="parent"/>
```
You can further customize your snack bar by adding the following optional custom attributes
```XML
<com.amrsaleh.animatedsnackbarlib.AnimatedSnackbar
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:id="@+id/snackbar"
            app:layout_constraintTop_toTopOf="parent"
              app:icon="@android:drawable/ic_dialog_email"
              app:icon_tint="@android:color/holo_blue_bright"
              app:text_color="@android:color/holo_blue_bright"
              android:background="@color/colorPrimaryDark"/>
```
#### Show the snack bar from your Activity of Fragment
```Kotlin
show_error_button.setOnClickListener {
    // Show the animated snack bar with a custom String message
    snackbar.showSnackbarWithMessage(getString(R.string.dummy_message))
}
```
The snack bar will auto hide after a few seconds but you can also manually hide the snack bar by calling the hideSnackbar function and cutomize the autohide duration or disable autohide by modifying the companion object params.
```Kotlin
// Hide the snack bar
snackbar.hideSnackbar()

// Customizing auto hide
AnimatedSnackbar.AUTO_HIDE = true
AnimatedSnackbar.AUTO_HIDE_DELAY_MILLIS = 4000
```
More description is planned to be added here, but for now please refer to the included example project for further clarification.

## License
Licensed under the [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt)
