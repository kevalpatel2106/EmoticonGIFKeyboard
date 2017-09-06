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

package com.kevalpatel2106.emojiscrapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    private static final Comparator<String> STRING_LENGTH_COMPARATOR = (first, second) -> {
        final int firstLength = first.length();
        final int secondLength = second.length();
        return firstLength < secondLength ? 1 : firstLength == secondLength ? 0 : -1;
    };
    private static final List<String> SUPPORTED_VENDOR = Arrays.asList(
//            "Apple",
//            "Android 8.0",
            "Android 7.0"
//            "HTC",
//            "Microsoft",
//            "Windows 8.1",
//            "Samsung",
//            "Facebook",
//            "Messenger",
//            "Twitter",
//            "EmojiOne",
//            "emojidex",
            /*"LG"*/);

    private static ArrayList<Emoji> mEmojis = new ArrayList<>();
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
    private static ArrayList<String> sUnicodesForPattern;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Initializing...");
        sUnicodesForPattern = new ArrayList<>();

        System.out.println("Deleting previous data...");
        String[] entries = new File(Utils.CURRENT_DIR_PATH).list();
        if (entries != null) for (String s : entries) {
            File currentFile = new File(Utils.CURRENT_DIR_PATH, s);
            currentFile.delete();
        }

        //Start loading categories.
        for (String categoryUrl : EMOJI_CATEGORIES_URL) {
            parseCategoryEmoji(categoryUrl);
        }

        System.out.println("\n\n*******************************************");
        System.out.println("Saving json file...");
        String json = new Gson().toJson(mEmojis);
        Utils.saveFile(new File(Utils.CURRENT_DIR_PATH + "/emoji.json"), json);

        createEmoticonList(mEmojis);

        System.out.println("\n\n*******************************************");
        System.out.println("Creating database...");
        Connection connection = SQLiteJDBC.connect();
        SQLiteJDBC.createTable(connection);

        System.out.println("\n\n*******************************************");
        System.out.println("Saving to database..." + mEmojis.size());
        for (int i = 0, mEmojisSize = mEmojis.size(); i < mEmojisSize; i++) {
            System.out.println(i + "");
            Emoji emoji = mEmojis.get(i);
            if (emoji != null) {
                System.out.println(emoji.unicode + " " + emoji.category + " " + emoji.codePoints + " " + emoji.tags);
                SQLiteJDBC.insertEmoji(connection, emoji);
            }
        }

        System.out.println("\n\n*******************************************");
        System.out.println("Creating regex...");
        createAndSaveEmoticonRegex(sUnicodesForPattern);

        System.out.println("\n\nSuccess!!!");
        System.out.println("*******************************************");
    }

    private static void parseCategoryEmoji(final String categoryUrl) {
        System.out.println("\n\n*******************************************");
        System.out.println("Loading category : " + categoryUrl);
        System.out.println("*******************************************");

        try {
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

                Emoji emoji = parseEmojiDetailPage(emojiPageUrl,
                        true,
                        categoryUrl.replace(BASE_URL, "").replace("/", ""));

                if (emoji != null) mEmojis.add(emoji);

                System.out.println("Emojis parsed: " + mEmojis.size());
            }
        } catch (IOException e) {
            System.out.println("Category page loading failed...Trying again...");
            parseCategoryEmoji(categoryUrl);
        }
    }

    private static Emoji parseEmojiDetailPage(final String emojiPageUrl,
                                              boolean allowParseModifire,
                                              String category) {

        try {

            System.out.println("\nParsing the emoji: " + emojiPageUrl);
            Emoji emoji = new Emoji();
            emoji.category = category;

            final Document doc = Jsoup.connect(emojiPageUrl).get();

            //Parse the name from H1 tag
            emoji.name = Utils.removeFirstEmojiFromText(doc.getElementsByTag("h1").text());
            emoji.unicode = Utils.getFirstEmojiFromText(doc.getElementsByTag("h1").text());
            sUnicodesForPattern.add(emoji.unicode);
            System.out.println(doc.getElementsByTag("h1").text());

            //Get the tags
            emoji.tags.add(emoji.name);
            if (emoji.name.toLowerCase().contains("face")) emoji.tags.add("Face");
            if (emoji.name.toLowerCase().contains("flag")) emoji.tags.add("flag");
            if (emoji.name.toLowerCase().contains("hand")) emoji.tags.add("hand");
            if (emoji.name.toLowerCase().contains("hands")) emoji.tags.add("hand");
            if (emoji.name.toLowerCase().contains("Person")) emoji.tags.add("Person");

            if (!doc.getElementsByClass("aliases").isEmpty()) {
                final Elements aliases = doc.getElementsByClass("aliases")
                        .tagName("ul").get(0)
                        .getElementsByTag("ul").get(0)
                        .getElementsByTag("li");
                for (Element element : aliases) {
                    String tagToAdd = Utils.removeFirstEmojiFromText(element.text());
                    emoji.tags.add(tagToAdd);
                    if (tagToAdd.contains(" and ")) {
                        emoji.tags.add(tagToAdd.split(" and ")[0]);
                        emoji.tags.add(tagToAdd.split(" and ")[1]);
                    }
                    if (tagToAdd.contains(":")) emoji.tags.add(tagToAdd.split(":")[0]);
                }
            }
            System.out.println("\t\tTags: " + emoji.tags.toString());

            //Add code points
            Elements codePoints = doc.getElementsMatchingOwnText("Codepoints") //Find <h2>Codepoint</h2>
                    .next().get(0) //Get the element next to it (<ul>)
                    .getElementsByTag("li");     //Get all <li> elements from it.
            for (Element codePointElement : codePoints) {
                emoji.codePoints.add(Utils.getStringCodePointFromElement(codePointElement
                        .getElementsByTag("a").text()));
            }

            //Parse the images
            final Elements vendorNames = doc.getElementsByClass("vendor-info");
            final Elements vendorImageUrls = doc.getElementsByClass("vendor-image");
            for (int i = 0; i < vendorNames.size(); i++) {
                //Vendor name
                String vendorName = vendorNames.get(i).getElementsByTag("a").text();

                String vendorImage;
                if (vendorImageUrls.get(i).getElementsByTag("img").hasAttr("data-src")) {
                    vendorImage = vendorImageUrls.get(i).getElementsByTag("img").attr("data-src");
                } else {
                    vendorImage = vendorImageUrls.get(i).getElementsByTag("img").attr("src");
                }

                //Special case for android 7. If the icon is not in android 7, we need to look into android 5.0.
                if (vendorName.equals("Android 5.0") && !emoji.imageUrls.containsKey("Android 7.0")) {
                    vendorName = "Android 7.0";
                }
                //Check if the vendor is among supported vendors
                if (SUPPORTED_VENDOR.contains(vendorName)) {
                    System.out.println(vendorName + " " + vendorImage);
                    emoji.imageUrls.put(vendorName, downloadImages(vendorImage, vendorName, emoji.codePoints));
                }
            }

            //Get modifiers
            if (allowParseModifire && !doc.getElementsByClass("modifiers").isEmpty()) {
                final Elements modifiers = doc.getElementsByClass("modifiers").get(0)
                        .getElementsByTag("ul").get(0)
                        .getElementsByTag("li");
                for (Element modifier : modifiers) {
                    Emoji emojiVariantParsed = parseEmojiDetailPage(BASE_URL
                                    + modifier.getElementsByTag("a").attr("href"),
                            false, category);    //False to prevent infinite recursion

                    emoji.variants.add(emojiVariantParsed);
                }
            }
            return emoji;

        } catch (IOException e) {
            System.out.println("Emoji detail page loading failed...Trying again...");
            parseEmojiDetailPage(emojiPageUrl, allowParseModifire, category);
            return null;
        }
    }

    private static File downloadImages(String url,
                                       String vendor,
                                       ArrayList<String> codePoints) throws IOException {
        File originalImageFile = Utils.getImageFile(vendor, codePoints);
        File resizedImageFile = Utils.getResizedImageFile(vendor, codePoints);

        //Prepare full scale image
        BufferedImage originalImg = ImageIO.read(new URL(url));
        ImageIO.write(originalImg, "png", originalImageFile);

        //Prepare resized image also
        BufferedImage resizedImg = Utils.resizeImage(originalImg);
        ImageIO.write(resizedImg, "png", resizedImageFile);

        return originalImageFile;
    }

    private static void createEmoticonList(ArrayList<Emoji> emojis) {
        System.out.println("\n\n*******************************************");
        System.out.println("Creating emoticon lists file...");

        String start = "\n" +
                "import java.util.HashMap;\n" +
                "\n" +
                "class EmoticonList {\n" +
                "    static final HashMap<String, Integer> EMOTICONS = new HashMap<>();\n" +
                "\n" +
                "    static {\n";

        for (String vendor : SUPPORTED_VENDOR) {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(start);
            for (Emoji emoji : emojis) {
                if (!emoji.imageUrls.containsKey(vendor)) continue;

                stringBuilder.append("        EMOTICONS.put(")
                        .append("\"").append(emoji.unicode).append("\"")
                        .append(", R.drawable.")
                        .append(emoji.imageUrls.get(vendor).getName().replace(".png", ""))
                        .append(");\n");
            }

            stringBuilder.append("\t}\n}");
            Utils.saveFile(new File(Utils.CURRENT_DIR_PATH + "/" + vendor + "/EmoticonList.java"),
                    stringBuilder.toString());
        }
    }

    /**
     * Create the regex to find emoticons and save it to the shared preference. This will generate
     * regex by adding unicode of all emoticons separated by '|'.
     *
     * @param unicodesForPattern List of all the supported unicodes from the database.
     */
    private static void createAndSaveEmoticonRegex(final ArrayList<String> unicodesForPattern) {
        // We need to sort the unicodes by length so the longest one gets matched first.
        unicodesForPattern.sort(STRING_LENGTH_COMPARATOR);

        StringBuilder unicodeRegex = new StringBuilder();
        for (String unicode : unicodesForPattern)
            unicodeRegex.append(Pattern.quote(unicode)).append("|");
        if (unicodeRegex.toString().endsWith("|"))
            unicodeRegex = new StringBuilder(unicodeRegex.substring(0, unicodeRegex.length() - 1));

        //Save the regex
        Utils.saveFile(new File(Utils.CURRENT_DIR_PATH + "/regex"), unicodeRegex.toString());
    }

    private static String readTextFile(File file) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        StringBuilder builder = new StringBuilder();
        try {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                builder.append(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
