package com.kevalpatel2106.emojiparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Keval on 24-Aug-17.
 */
class Utils {
    private static final String CURRENT_DIR_PATH = System.getProperty("user.dir");

    static String removeFirstEmojiFromText(String textWithEmojiFirst) {
        return textWithEmojiFirst.substring(textWithEmojiFirst.indexOf(" ") + 1);
    }

    static String getStringCodePointFromElement(String textWithCodePoint) {
        return textWithCodePoint.substring(textWithCodePoint.indexOf("+") + 1);
    }

    static File getImageDir(String vendor) {
        File dir = new File(CURRENT_DIR_PATH + "/" + vendor);
        if (!dir.exists()) dir.mkdir();
        return dir;
    }

    static File getImageFile(String vendor, ArrayList<String> codePoints) {
        String codePointStr = "";
        for (String codePoint : codePoints) codePointStr = codePointStr + codePoint + "_";

        return new File(getImageDir(vendor).getAbsolutePath()
                + "/emoji_"
                + vendor.toLowerCase() + "_"
                + codePointStr + ".png");
    }

    static void saveJson(String json) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File(CURRENT_DIR_PATH + "/emoji.json")));
            writer.write(json);

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
}
