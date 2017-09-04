# GIF Pack - GIPHY
[ ![Download](https://api.bintray.com/packages/kevalpatel2106/EmoticonGIFKeyboard/gifpack-giphy/images/download.svg) ](https://bintray.com/kevalpatel2106/EmoticonGIFKeyboard/gifpack-giphy/_latestVersion) [![API](https://img.shields.io/badge/API-16%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=16) [![Javadoc](https://img.shields.io/badge/Javadoc-EmoticonGIFKeyboard-blue.svg)](http://kevalpatel2106.github.io/EmoticonGIFKeyboard) <a href="https://www.paypal.me/kevalpatel2106"> <img src="https://img.shields.io/badge/paypal-donate-yellow.svg" /></a> ![Giphy](https://img.shields.io/badge/GIF%20Service-Giphy-orange.svg)

![Giphy Banner](/gifpack-giphy/art/giphyseriesc.gif)

This GIF pack provides GIF images from [Giphy](https://giphy.com/). Internally it uses Giphy APIs to get trending gifs and search the gifs.


## How to use?
- Add below lines to `app/build.gradle` file of your project.
    ```
    dependencies {
       compile 'com.kevalpatel2106:gifpack-giphy:<latest>
    }
    ```
- This gif pack is plugin for [EmoticonGIFKeyboard](https://github.com/kevalpatel2106/EmoticonGIFKeyboard).
- Create an application in [Giphy Developers](https://developers.giphy.com/).
- Obtain the api key from the [Dashboard](https://developers.giphy.com/dashboard).
- Add the GIF provider in GifConfig constructor.
  ```
      EmoticonGIFKeyboardFragment.GIFConfig giphyGifConfig = new EmoticonGIFKeyboardFragment.GIFConfig(GiphyGifProvider.create(context, "<YOUR API KEY>"))
  ```


## Third party libraries used:
- [Retrofit](http://square.github.io/retrofit/)


## Questions?🤔
Hit me on twitter [![Twitter](https://img.shields.io/badge/Twitter-@kevalpatel2106-blue.svg?style=flat)](https://twitter.com/kevalpatel2106)


## License
Copyright 2017 Keval Patel

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
