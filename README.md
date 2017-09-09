# EmoticonGIFKeyboard
[![Build Status](https://travis-ci.org/kevalpatel2106/EmoticonGIFKeyboard.svg?branch=master)](https://travis-ci.org/kevalpatel2106/EmoticonGIFKeyboard) [ ![Download](https://api.bintray.com/packages/kevalpatel2106/EmoticonGIFKeyboard/emoticongifkeyboard/images/download.svg) ](https://bintray.com/kevalpatel2106/EmoticonGIFKeyboard/emoticongifkeyboard/_latestVersion) [![API](https://img.shields.io/badge/API-16%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=16) [![Javadoc](https://img.shields.io/badge/Javadoc-EmoticonGIFKeyboard-blue.svg)](http://kevalpatel2106.github.io/EmoticonGIFKeyboard) <a href="https://www.paypal.me/kevalpatel2106"> <img src="https://img.shields.io/badge/paypal-donate-yellow.svg" /></a> [![Androidweekly](https://img.shields.io/badge/Android%20Weekly-%23273-green.svg)](http://androidweekly.net/issues/issue-273) [![AndroidDev Digest](https://img.shields.io/badge/AndroidDev%20Digest-%23157-blue.svg)](https://www.androiddevdigest.com/digest-157/) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-EmoticonGIFKeyboard-orange.svg?style=flat)](https://android-arsenal.com/details/1/6162)

###### 🙌 An advance Emoticons & GIF keyboard. 🙌
**EmoticonGIFKeyboard** is an easy to integrate, customizable and lightweight library to add support for emojis and GIFs.


## Features:
- Highly customizable.
- Extremely lightweight 🏋. You only have to add the icon packs you want to use by adding extra dependency, so you don't have to add emoticon icons you don't want to use.
- ❤ for **Emoticons**
    - More than 1400 emoticons. This library includes all the emoticons listed under [Emoticons 6.0](https://emojipedia.org/unicode-6.0/) standards.
    - 6 emoticons categories.
    - 🔍 Search emoticons by their name or meaning.
    - Add you favourite emoticons icon theme (e.g ios, google, samsung emoticons) by adding readily available 13 different [emoticon icon packs](https://github.com/kevalpatel2106/EmoticonGIFKeyboard#emoticon-icon-packs).
- ❤ for **GIFs**
    - Displays trending GIFs for batter suggestion.
    - 🔍 Search GIFs based on the content and meaning.
    - Select your favourite GIF provider (e.g. Giphy, Tenor) by adding available [GIF packs](https://github.com/kevalpatel2106/EmoticonGIFKeyboard#gif-packs).
- Custom widgets (e.g. `EmoticonTextView`, `EmoticonEditText` and `EmoticonButton`) to render custom emoticon icons throughout application.
- Easily disable emoticons or GIF functionality if you don't want.


## How to import this library?
- ### Gradle Dependency:
  * Add below lines to `app/build.gradle` file of your project.
  ```groovy
  dependencies {
      compile 'com.kevalpatel2106:emoticongifkeyboard:1.1'
  }
  ```
- To integrate using maven visit this [page](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/blob/master/IMPORT.md).


## How to use this EmoticonGIFKeyboard?
- ### Prepare emoticons configuration.
  * Create `EmoticonConfig` to configure emoticons.
  * Set the custom emoticon icon provider using `EmoticonConfig#setEmoticonProvider()`. If you don't set any icon provider here, library will render system emoticons. You can pic custom emoticon icons from [here](https://github.com/kevalpatel2106/EmoticonGIFKeyboard#emoticon-icon-packs).
  * Set the `EmoticonSelectListener` using `EmoticonConfig#setEmoticonSelectListener()`. This will notify you when user selects any emoticon from list or user preses back button.

  ```java
  EmoticonGIFKeyboardFragment.EmoticonConfig emoticonConfig = new EmoticonGIFKeyboardFragment.EmoticonConfig()
          .setEmoticonProvider(IosEmoticonProvider.create())
          /*
            NOTE: The process of removing last character when user preses back space will handle
            by library if your edit text is in focus.
           */
          .setEmoticonSelectListener(new EmoticonSelectListener() {

              @Override
              public void emoticonSelected(Emoticon emoticon) {
                  //Do something with new emoticon.
              }

              @Override
              public void onBackSpace() {
                  //Do something here to handle backspace event.
                  //The process of removing last character when user preses back space will handle
                  //by library if your edit text is in focus.
              }
          });
  ```

- ### Prepare GIF configuration.
  * Set the desired GIF provider by passing desired `GIFProvider` in constructor `GIFConfig()`. [Here](https://github.com/kevalpatel2106/EmoticonGIFKeyboard#gif-packs) is  the list of supported GIF providers.
    It is required to set GIF provider before adding fragment into container.
  * Implement GIF select listener using `GIFConfig#setGifSelectListener()`. This will notify you when user selects new GIF.

  ```java
  //Create GIF config
  EmoticonGIFKeyboardFragment.GIFConfig gifConfig = new EmoticonGIFKeyboardFragment

          /*
            Here we are using GIPHY to provide GIFs. Create Giphy GIF provider by passing your key.
            It is required to set GIF provider before adding fragment into container.
           */
          .GIFConfig(GiphyGifProvider.create(this, "564ce7370bf347f2b7c0e4746593c179"))
          .setGifSelectListener(new GifSelectListener() {
              @Override
              public void onGifSelected(@NonNull Gif gif) {
                  //Do something with the selected GIF.
                  Log.d(TAG, "onGifSelected: " + gif.getGifUrl());
              }
          });
  ```

- ### Add `EmoticonGIFKeyboardFragment`.
  * Create new `EmoticonGIFKeyboardFragment` by passing `EmoticonConfig` and `GIFConfig`. If you pass null to `EmoticonConfig`, emoticon functionality will be disabled. Also, if you pass `GIFConfig` as null, GIF functionality will be disabled.
  * Add the generated fragment to the container.

  ```java
  EmoticonGIFKeyboardFragment emoticonGIFKeyboardFragment = EmoticonGIFKeyboardFragment
          .getNewInstance(findViewById(R.id.keyboard_container), emoticonConfig, gifConfig);

  //Adding the keyboard fragment to keyboard_container.
  getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.keyboard_container, EmoticonGIFKeyboardFragment)
          .commit();
  ```
  

- ### Open/Close the keyboard and handle back button press.
  * Open keyboard view by calling `EmoticonGIFKeyboardFragment#open()` and close it by calling `EmoticonGIFKeyboardFragment#close()`.
  * Handle back button press events by using `EmoticonGIFKeyboardFragment#handleBackPressed()` in your activity.

  ```java
    @Override
    public void onBackPressed() {
      if (!mEmoticonGIFKeyboardFragment.handleBackPressed())
          super.onBackPressed();
    }
  ```
  

## Demo
|Emoticons | GIFs |
|:---:|:---:|
|![Emoticon Demo](/art/emoji_demo.gif)|![GIF Demo](/art/gif_demo.gif)|

|Only Emoticons | Only GIFs |
|:---:|:---:|
|![Only Emoticons](/art/only_emoji.png)|![Only GIFs](/art/only_gif.gif)|
  
|Search Emoticons | Search GIFs |
|:---:|:---:|
|![Only Emoticons](/art/search_emoji.png)|![Only GIFs](/art/search_gif.gif)|


## Emoticon icon packs
|Icon|Emoticon Pack|Gradle Dependency|Version|
|:---:|:---:|:---:|:---:|
|![Grinning Emoji](/emoticonpack-ios/art/grinning-face_1f600.png)|[Apple](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/tree/master/emoticonpack-ios)|`compile 'com.kevalpatel2106:emoticonpack-ios:<latest>`|[ ![Download](https://api.bintray.com/packages/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-ios/images/download.svg) ](https://bintray.com/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-ios/_latestVersion)|
|![Grinning Emoji](/emoticonpack-android7/art/grinning-face_1f600.png)|[Android 7.0](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/tree/master/emoticonpack-android8)|`compile 'com.kevalpatel2106:emoticonpack-android7:<latest>`|[ ![Download](https://api.bintray.com/packages/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-android7/images/download.svg) ](https://bintray.com/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-android7/_latestVersion)|
|![Grinning Emoji](/emoticonpack-android8/art/grinning-face_1f600.png)|[Android 8.0](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/tree/master/emoticonpack-android8)|`compile 'com.kevalpatel2106:emoticonpack-android8:<latest>`|[ ![Download](https://api.bintray.com/packages/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-android8/images/download.svg) ](https://bintray.com/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-android8/_latestVersion)|
|![Grinning Emoji](/emoticonpack-samsung/art/grinning-face_1f600.png)|[Samsung](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/tree/master/emoticonpack-samsung)|`compile 'com.kevalpatel2106:emoticonpack-samsung:<latest>`|[ ![Download](https://api.bintray.com/packages/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-samsung/images/download.svg) ](https://bintray.com/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-samsung/_latestVersion)|
|![Grinning Emoji](/emoticonpack-htc/art/grinning-face_1f600.png)|[HTC](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/tree/master/emoticonpack-htc)|`compile 'com.kevalpatel2106:emoticonpack-htc:<latest>`|[ ![Download](https://api.bintray.com/packages/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-htc/images/download.svg) ](https://bintray.com/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-htc/_latestVersion)|
|![Grinning Emoji](/emoticonpack-lg/art/grinning-face_1f600.png)|[LG](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/tree/master/emoticonpack-lg)|`compile 'com.kevalpatel2106:emoticonpack-lg:<latest>`|[ ![Download](https://api.bintray.com/packages/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-lg/images/download.svg) ](https://bintray.com/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-lg/_latestVersion)|
|![Grinning Emoji](/emoticonpack-windows8/art/grinning-face_1f600.png)|[Windows 8.1](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/tree/master/emoticonpack-windows8)|`compile 'com.kevalpatel2106:emoticonpack-windows8:<latest>`|[ ![Download](https://api.bintray.com/packages/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-windows8/images/download.svg) ](https://bintray.com/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-windows8/_latestVersion)|
|![Grinning Emoji](/emoticonpack-windows10/art/grinning-face_1f600.png)|[Windows 10](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/tree/master/emoticonpack-windows10)|`compile 'com.kevalpatel2106:emoticonpack-windows10:<latest>`|[ ![Download](https://api.bintray.com/packages/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-windows10/images/download.svg) ](https://bintray.com/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-windows10/_latestVersion)|
|![Grinning Emoji](/emoticonpack-twitter/art/grinning-face_1f600.png)|[Twitter](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/tree/master/emoticonpack-twitter)|`compile 'com.kevalpatel2106:emoticonpack-twitter:<latest>`|[ ![Download](https://api.bintray.com/packages/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-twitter/images/download.svg) ](https://bintray.com/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-twitter/_latestVersion)|
|![Grinning Emoji](/emoticonpack-facebook/art/grinning-face_1f600.png)|[Facebook](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/tree/master/emoticonpack-facebook)|`compile 'com.kevalpatel2106:emoticonpack-facebook:<latest>`|[ ![Download](https://api.bintray.com/packages/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-facebook/images/download.svg) ](https://bintray.com/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-facebook/_latestVersion)|
|![Grinning Emoji](/emoticonpack-messenger/art/grinning-face_1f600.png)|[Messenger](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/tree/master/emoticonpack-messenger)|`compile 'com.kevalpatel2106:emoticonpack-messenger:<latest>`|[ ![Download](https://api.bintray.com/packages/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-messenger/images/download.svg) ](https://bintray.com/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-messenger/_latestVersion)|
|![Grinning Emoji](/emoticonpack-emojidex/art/grinning-face_1f600.png)|[Emojidex](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/tree/master/emoticonpack-emojidex)|`compile 'com.kevalpatel2106:emoticonpack-emojidex:<latest>`|[ ![Download](https://api.bintray.com/packages/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-emojidex/images/download.svg) ](https://bintray.com/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-emojidex/_latestVersion)|
|![Grinning Emoji](/emoticonpack-emojione/art/grinning-face_1f600.png)|[EmojiOne](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/tree/master/emoticonpack-emojione)|`compile 'com.kevalpatel2106:emoticonpack-emojione:<latest>`|[ ![Download](https://api.bintray.com/packages/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-emojione/images/download.svg) ](https://bintray.com/kevalpatel2106/EmoticonGIFKeyboard/emoticonpack-emojione/_latestVersion)|


## GIF Packs
|GIF Provider|Module|Dependency|Version|
|:---:|:---:|:---:|:---:|
|[giphy.com](https://giphy.com)|[Giphy](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/tree/master/gifpack-giphy)|`compile 'com.kevalpatel2106:gifpack-giphy:<latest>`|[ ![Download](https://api.bintray.com/packages/kevalpatel2106/EmoticonGIFKeyboard/gifpack-giphy/images/download.svg) ](https://bintray.com/kevalpatel2106/EmoticonGIFKeyboard/gifpack-giphy/_latestVersion)|
|[tenor.com](https://tenor.com)|[Tenor](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/tree/master/gifpack-tenor)|`compile 'com.kevalpatel2106:gifpack-tenor:<latest>`|[ ![Download](https://api.bintray.com/packages/kevalpatel2106/EmoticonGIFKeyboard/gifpack-tenor/images/download.svg) ](https://bintray.com/kevalpatel2106/EmoticonGIFKeyboard/gifpack-tenor/_latestVersion)|


## Acknowledgements
- Based on Hieu Rocker's library [Emojicon](https://github.com/rockerhieu/emojicon).
- Emoticon graphics and emoticon details are from [Emojipedia](https://emojipedia.org/). (See [mEmoticon-scrapper](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/tree/master/mEmoticon-scrapper) module.)


## How to contribute?
* Check out contribution guidelines 👉[CONTRIBUTING.md](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/blob/master/CONTRIBUTING.md)


## Questions?🤔
Hit me on twitter [![Twitter](https://img.shields.io/badge/Twitter-@kevalpatel2106-blue.svg?style=flat)](https://twitter.com/kevalpatel2106)


## License
Copyright 2017 Keval Patel

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
