# AnimatedSnackbar
Android library that provides an animated customizable snack bar.

## Features
- View on demand a custom snack bar from the top of the screen.
- Pass custom message to show in the snack bar.
- The snack bar disappears automatically after a few seconds.
- Dismiss the bar programmatically on demand.
- Customize various features like background, font, icon and tint.
- Use your own custom view in the snack bar.
- Automatically detect and start animated drawables in background.

## Showcase
These are GIFs and may take a few seconds to load if you have a slow connection.
![Showcase GIF](https://user-images.githubusercontent.com/5616594/58800238-d72cd780-85f6-11e9-9796-ae13a4ed6bfd.gif)

<!-- 
![CustomView GIF](https://user-images.githubusercontent.com/5616594/58909633-dc337900-8713-11e9-99c9-35ccee639208.gif)
![Showcase image](https://user-images.githubusercontent.com/5616594/58910033-d25e4580-8714-11e9-9e32-d09be8884025.png)
-->

<img src="https://user-images.githubusercontent.com/5616594/58910033-d25e4580-8714-11e9-9e32-d09be8884025.png" alt="Showcase image" width="430"/><img src="https://user-images.githubusercontent.com/5616594/58909633-dc337900-8713-11e9-99c9-35ccee639208.gif" alt="CustomView GIF" width="430"/>

<!-- <img src="https://user-images.githubusercontent.com/5616594/58800177-b795af00-85f6-11e9-8cc2-a240c76184c6.png" alt="png1" width="430"/> -->
<!-- <img src="https://user-images.githubusercontent.com/5616594/57711888-22d70b80-765f-11e9-86ce-2907ac0ddb58.gif" alt="GIF1" width="430"/> <img src="https://user-images.githubusercontent.com/5616594/57711890-22d70b80-765f-11e9-945e-80d2b9a77061.gif" alt="GIF2" width="430"/> -->

## Customization
- You can customize multiple features in the snackbar:
  - Background color or drawable.
  - Text message color.
  - Icon drawable and tint.
  - Text Font.
  - Auto hide duration.
  - Automatic status bar padding handling
  - Add your own custom view.
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
    //...
    maven { url 'https://jitpack.io' }
  }
}
```
- Step 2. Add the dependency to your app build.gradle
```Groovy
dependencies {
        implementation 'com.github.AmrSaleh:AnimatedSnackbar:v2.0' // Make sure to replace with latest version tag
}
```

### Usage in your project
#### Show a simple snack bar from your Activity of Fragment(pass activity context)
```Kotlin
AnimatedSnackbar(context)
    .setMessage(getString(R.string.dummy_message))
    .show()
```
The snack bar will auto hide after a few seconds but you can also manually hide the snack bar by calling the "hide" function.

#### Show a customized snack bar from your Activity of Fragment(pass activity context)
```Kotlin
// val animationDrawable = getDrawable(R.drawable.drawable_gradient_animation_list) as AnimationDrawable

AnimatedSnackbar(context)
    .setMessage(getString(R.string.dummy_message), ContextCompat.getColor(this@MainActivity, android.R.color.white))
    .setTextSize(15f)
    .setTypeFace(Typeface.DEFAULT_BOLD)
    .setIconDrawable(getDrawable(android.R.drawable.ic_dialog_email), ContextCompat.getColor(this@MainActivity, R.color.greenLight))
    .setBgDrawable(animationDrawable)
    .setAnimationDurationMillis(600)
    .setAutoHide(true, 4000)
    .setAddStatusBarPadding(true) // default true
    .show()
```
Second parameter in any setter is optional and you can always enter only first parameter if you like.

#### Use your custom view in the snack bar.
```Kotlin
AnimatedSnackbar(context)
                .setCustomView(R.layout.my_custom_view)
                .show()
```
#### You can also customize the message and icon even when using a custom  view.
Just make sure to use 
* id ```icon_image_view``` for your image view.
* id ```message_text_view``` for your text view.
```Kotlin
// Customizations can be set before or after setting your custom view (How cool is that!)
AnimatedSnackbar(context)
                .setMessage("Yay! I set this message before or after setting my custom view")
                .setCustomView(R.layout.my_custom_view)
                .setBgDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.darkGray)))
                .setIconDrawable(getDrawable(android.R.drawable.ic_dialog_email))
                .show()
```

#### You can hide the snack bar any time you want.
```Kotlin
// val snackbar = AnimatedSnackbar(context).setMessage(getString(R.string.dummy_message))
// snackbar.show()

snackbar.hide()
```

More description is planned to be added here, but for now please refer to the included example project for further clarification.

## License
Licensed under the [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt)
