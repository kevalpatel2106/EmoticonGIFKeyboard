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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Keval on 24-Aug-17.
 */
class Utils {
    private static final String CURRENT_DIR_PATH = System.getProperty("user.dir") + "/emoji-parser/out";

    static String removeFirstEmojiFromText(String textWithEmojiFirst) {
        return textWithEmojiFirst.substring(textWithEmojiFirst.indexOf(" ") + 1);
    }

    static String getStringCodePointFromElement(String textWithCodePoint) {
        return textWithCodePoint.substring(textWithCodePoint.indexOf("+") + 1);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    static File getImageDir(String vendor) {

        File dir = new File(CURRENT_DIR_PATH);
        if (!dir.exists()) dir.mkdir();

        dir = new File(CURRENT_DIR_PATH + "/" + vendor);
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
