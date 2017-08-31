# Emoji scrapper
<a href="https://www.paypal.me/kevalpatel2106"> <img src="https://img.shields.io/badge/paypal-donate-yellow.svg" /></a>
 
This is an open sourced java project to scrap the [Emojipedia](http://emojipedia.org) website and find out latest emojis and their icons for different vendors. 


## What will be in the output?

1. Icons of all emoticons.

    List of supported vendors/icon packs are:
    - Emojidex
    - EmojiOne
    - Facebook
    - Google (From Android 8.0)
    - Apple (From iOS 10.3/macOS 12)
    - Facebook messenger
    - Twitter
    - Microsoft (From Windows 10 Creators Update)
    - Samsung (From Galaxy S8)

    All the icons are in in orignal and small (48px * 48px) sizes.
    - Original Icons folder : `/out/{Vendor name}/original`
    - Small Icons folder : `/out/{Vendor name}/small`

2. JSON file with data of all scrapped emojis. This file is located at `/out/mEmoticon.json`.
	Sample:
	```json
	[
		{
			"name": "Grinning Face",
			"category": "people",
			"unicode": "😀",
			"codepoint": [
			    "1F600"
			],
			"tag": [
				"Grinning Face",
				"Face",
				"Happy Face",
				"Smiley Face"
			],
			"variants": []
		},
  		//...//
 		//...//
	]
 		
	```
3. SQLite database that can be directly import in Android Application with mEmoticon information, tags and mEmoticon variants. This database is located under `/out/emoticon.db`.

4. Java rejex string to find  out supported emojis from the string. Regex string is stored in `/out/regex`.


## What data it scraps from emojipedia?
- Emoticon name.
- Emoticon unicode.
- Emoticon codepoints.
- Search tags for emoticon.
- Emoticon Variants.
- Category.


## How to run this code?
- Open directory in Intelij IDEA and run (▷) the `Main.java`. That's it.


## What if I don't want to spend time to scrap the website?🤔
- Scrapping Emojopedia and get all the emojis from the website will take almost 2 -3 hours 🕑 (depends on your internet connectivity).😟😟 
- We know that you don't have time for that. Don't worry!!! You can download the scrapped data from from [emoji-scrapper-out.zip](https://github.com/kevalpatel2106/EmoticonGIFKeyboard/releases).

## Legal:
This code is open source and freely available for use. However the data scrapped from the [Emojipedia](http://emojipedia.org) website are subjected to [Emojipedia](http://emojipedia.org) terms and conditions. Visit [Emojipedia](http://emojipedia.org) to know more.


## Questions?🤔
Hit me on twitter [![Twitter](https://img.shields.io/badge/Twitter-@kevalpatel2106-blue.svg?style=flat)](https://twitter.com/kevalpatel2106)


## License
Copyright 2017 Keval Patel

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
