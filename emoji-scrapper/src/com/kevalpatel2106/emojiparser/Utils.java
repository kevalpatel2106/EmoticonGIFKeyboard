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

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.kevalpatel2106.emojiparser.Utils.EmoticonsCategories.ACTIVITY;
import static com.kevalpatel2106.emojiparser.Utils.EmoticonsCategories.FLAGS;
import static com.kevalpatel2106.emojiparser.Utils.EmoticonsCategories.FOOD;
import static com.kevalpatel2106.emojiparser.Utils.EmoticonsCategories.NATURE;
import static com.kevalpatel2106.emojiparser.Utils.EmoticonsCategories.OBJECTS;
import static com.kevalpatel2106.emojiparser.Utils.EmoticonsCategories.PEOPLE;
import static com.kevalpatel2106.emojiparser.Utils.EmoticonsCategories.SYMBOLS;
import static com.kevalpatel2106.emojiparser.Utils.EmoticonsCategories.TRAVEL;

/**
 * Created by Keval on 24-Aug-17.
 */
class Utils {
    static final String CURRENT_DIR_PATH = System.getProperty("user.dir") + "/out";
    private static final int IMG_WIDTH = 60;
    private static final int IMG_HEIGHT = 60;

    static String removeFirstEmojiFromText(String textWithEmojiFirst) {
        return textWithEmojiFirst.substring(textWithEmojiFirst.indexOf(" ") + 1);
    }

    static String getFirstEmojiFromText(String textWithEmojiFirst) {
        return textWithEmojiFirst.substring(0, textWithEmojiFirst.indexOf(" "));
    }

    static String getStringCodePointFromElement(String textWithCodePoint) {
        return textWithCodePoint.substring(textWithCodePoint.indexOf("+") + 1);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static File getImageDir(String vendor) {
        File dir = new File(CURRENT_DIR_PATH);
        if (!dir.exists()) dir.mkdir();

        dir = new File(CURRENT_DIR_PATH + "/" + vendor);
        if (!dir.exists()) dir.mkdir();

        dir = new File(CURRENT_DIR_PATH + "/" + vendor + "/original/");
        if (!dir.exists()) dir.mkdir();
        return dir;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static File getResizedImageDir(String vendor) {
        File dir = new File(CURRENT_DIR_PATH);
        if (!dir.exists()) dir.mkdir();

        dir = new File(CURRENT_DIR_PATH + "/" + vendor);
        if (!dir.exists()) dir.mkdir();

        dir = new File(CURRENT_DIR_PATH + "/" + vendor + "/small/");
        if (!dir.exists()) dir.mkdir();
        return dir;
    }

    static File getImageFile(String vendor, ArrayList<String> codePoints) {
        String codePointStr = "";
        for (String codePoint : codePoints) codePointStr = codePointStr + codePoint + "_";
        if (codePointStr.endsWith("_"))
            codePointStr = codePointStr.substring(0, codePointStr.length() - 1);

        return new File(getImageDir(vendor).getAbsolutePath()
                + "/emoji_"
                + vendor.toLowerCase() + "_"
                + codePointStr.toLowerCase() + ".png");
    }

    static File getResizedImageFile(String vendor, ArrayList<String> codePoints) {
        String codePointStr = "";
        for (String codePoint : codePoints) codePointStr = codePointStr + codePoint + "_";
        if (codePointStr.endsWith("_"))
            codePointStr = codePointStr.substring(0, codePointStr.length() - 1);

        return new File(getResizedImageDir(vendor).getAbsolutePath()
                + "/emoji_"
                + vendor.toLowerCase() + "_"
                + codePointStr.toLowerCase() + ".png");
    }

    static void saveFile(File file, String text) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(text);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static int[] generateCodePoint(String emojiUnicode) {
        int[] codePoints = new int[emojiUnicode.length()];
        for (int i = 0; i < codePoints.length; i++) codePoints[i] = emojiUnicode.charAt(i);
        return codePoints;
    }


    static BufferedImage resizeImage(BufferedImage originalImage) {
        int imageType = originalImage.getType();
        if (imageType == 0) imageType = 5;

        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, imageType);

        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }

    static int getCategoryFromCategoryName(String name) {
        switch (name) {
            case "people":
                return PEOPLE;
            case "nature":
                return NATURE;
            case "food-drink":
                return FOOD;
            case "flags":
                return FLAGS;
            case "symbols":
                return SYMBOLS;
            case "objects":
                return OBJECTS;
            case "activity":
                return ACTIVITY;
            case "travel-places":
                return TRAVEL;
            default:
                return SYMBOLS;
        }
    }

    final class EmoticonsCategories {
        static final int RECENT = 0;
        static final int PEOPLE = 1;
        static final int NATURE = 2;
        static final int FOOD = 3;
        static final int ACTIVITY = 4;
        static final int TRAVEL = 5;
        static final int OBJECTS = 6;
        static final int SYMBOLS = 7;
        static final int FLAGS = 8;
    }

}
