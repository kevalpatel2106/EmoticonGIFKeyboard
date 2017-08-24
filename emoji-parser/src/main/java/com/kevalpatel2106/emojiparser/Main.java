/*
 * Copyright 2017 Keval Patel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.kevalpatel2106.emojiparser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final List<String> SUPPORTED_VENDOR = Arrays.asList(
            "Apple",
            "Google",
            "Microsoft",
            "Samsung",
            "Facebook",
            "Messenger",
            "Twitter",
            "EmojiOne",
            "emojidex");

    private static final ArrayList<Emoji> mEmojis = new ArrayList<>();
    private static final String BASE_URL = "http://emojipedia.org";
    private static final String[] EMOJI_CATEGORIES_URL = new String[]{
            BASE_URL + "/people/",
            BASE_URL + "/nature/",
            BASE_URL + "/food-drink/",
            BASE_URL + "/activity/",
            BASE_URL + "/travel-places/",
            BASE_URL + "/objects/",
            BASE_URL + "/symbols/",
            BASE_URL + "/flags/"
    };

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {
        System.out.println("Initializing...");

        //Delete image dir
        for (String vendor : SUPPORTED_VENDOR) Utils.getImageDir(vendor).delete();

        //Start loading categories.
        for (String categoryUrl : EMOJI_CATEGORIES_URL) {
            try {
                parseCategoryEmoji(categoryUrl);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Try again...");
            }
        }

        System.out.println("Saving json file...");
        String json = new Gson().toJson(mEmojis);
        Utils.saveJson(json);
        System.out.println("Success!!!");
    }

    private static void parseCategoryEmoji(final String categoryUrl) throws IOException {
        System.out.println("\n\n*******************************************");
        System.out.println("Loading category : " + categoryUrl);
        System.out.println("*******************************************");

        Document doc = Jsoup.connect(categoryUrl).get();

        //Find out the emoji list <ul>
        Elements emojiLists = doc.getElementsByClass("emoji-list").get(0).getElementsByTag("li");
        System.out.println("Total " + emojiLists.size() + " emojis found.");

        //Iterate through all  the <li> elements.
        for (Element emojiListItem : emojiLists) {
            //Get the page url for each emoji
            String emojiPageUrl = BASE_URL + emojiListItem
                    .getElementsByTag("a")
                    .attr("href");

            mEmojis.add(parseEmojiDetailPage(emojiPageUrl,
                    true,
                    categoryUrl.replace(BASE_URL,"").replace("/","")));
        }
    }

    private static Emoji parseEmojiDetailPage(final String emojiPageUrl,
                                              boolean allowParseModifire,
                                              String category) throws IOException {

        System.out.println("\nParsing the emoji: " + emojiPageUrl);
        Emoji emoji = new Emoji();

        final Document doc = Jsoup.connect(emojiPageUrl).get();

        //Parse the name from H1 tag
        emoji.name = Utils.removeFirstEmojiFromText(doc.getElementsByTag("h1").text());

        //Parse the images
        final Elements vendorNames = doc.getElementsByClass("vendor-info");
        final Elements vendorImageUrls = doc.getElementsByClass("vendor-image");
        for (int i = 0; i < vendorNames.size(); i++) {
            //Vendor name
            String vendorName = vendorNames.get(i).getElementsByTag("a").text();
            String vendorImage = vendorImageUrls.get(i).getElementsByTag("img").attr("src");

            //Check if the vendor is among supported vendors
            if (SUPPORTED_VENDOR.contains(vendorName)) {
                emoji.imageUrls.put(vendorName, vendorImage);
                System.out.println("\t" + vendorName + " : " + vendorImage);
            }
        }

        //Get the tags
        emoji.tags.add(emoji.name);
        if (!doc.getElementsByClass("aliases").isEmpty()) {
            final Elements aliases = doc.getElementsByClass("aliases")
                    .tagName("ul").get(0)
                    .getElementsByTag("ul").get(0)
                    .getElementsByTag("li");
            for (Element element : aliases) emoji.tags.add(Utils.removeFirstEmojiFromText(element.text()));
            //TODO What about faces, flags and hand tags?
        }

        //Get modifiers
        if (allowParseModifire && !doc.getElementsByClass("modifiers").isEmpty()) {
            final Elements modifires = doc.getElementsByClass("modifiers").get(0)
                    .getElementsByTag("ul").get(0)
                    .getElementsByTag("li");
            for (Element modifire : modifires) {
                emoji.variants.add(parseEmojiDetailPage(BASE_URL
                                + modifire.getElementsByTag("a").attr("href"),
                        false, category));    //False to prevent infinite recursion
            }
        }

        //Add code points
        Elements codePoints = doc.getElementsMatchingOwnText("Codepoints") //Find <h2>Codepoint</h2>
                .next().get(0) //Get the element next to it (<ul>)
                .getElementsByTag("li");     //Get all <li> elements from it.
        for (Element codePointElement : codePoints) {
            emoji.codePoints.add(Utils.getStringCodePointFromElement(codePointElement
                    .getElementsByTag("a").text()));
        }

        //Download images
        downloadImages(emoji);
        return emoji;
    }

    private static void downloadImages(Emoji emoji) throws IOException {
        for (String vendorKey : emoji.imageUrls.keySet()) {
            BufferedImage img = ImageIO.read(new URL(emoji.imageUrls.get(vendorKey)));
            ImageIO.write(img, "png", Utils.getImageFile(vendorKey, emoji.codePoints));
        }
    }
}
