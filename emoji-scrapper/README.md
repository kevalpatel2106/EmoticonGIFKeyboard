# Emoji scrapper

This is an java project to scrap the emojipedia website and findout latest emojis and their icons for different vendors. Downloaded files will be stored in /out folder.

### What will be in output?

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

2. JSON file with data of all scrapped emojis. This file is located at `/out/emoji.json`.
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
3. SQLite database that can be directly import in Android Application with emoji information, tags and emoji variants. This database is located under `/out/emoticon.db`.

4. Java rejex string to find  out supported emojis from the string. Regex string is stored in `/out/regex`.

### What data it scraps from emojipedia?

- Emoticon name.
- Emoticon unicode.
- Emoticon codepoints.
- Search tags for emoticon.
- Emoticon Variants.
- Category.


###### This is an Intellij IDEA project.


