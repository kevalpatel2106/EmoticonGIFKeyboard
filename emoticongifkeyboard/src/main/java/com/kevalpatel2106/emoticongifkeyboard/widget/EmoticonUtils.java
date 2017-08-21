package com.kevalpatel2106.emoticongifkeyboard.widget;

import android.support.annotation.NonNull;

import com.kevalpatel2106.emoticongifkeyboard.internal.emoticons.EmoticonProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Keval Patel on 20/08/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

public class EmoticonUtils {
    private static final String emo_regex = "([\\u20a0-\\u32ff\\ud83c\\udc00-\\ud83d\\udeff\\udbb9\\udce5-\\udbb9\\udcee])";
    private static final Pattern mPattern = Pattern.compile(emo_regex);

    public static void replaceEmoticonsIcon(@NonNull String stringToCheck,
                                            @NonNull EmoticonProvider provider) {
        Matcher matcher = mPattern.matcher(stringToCheck);
        while (matcher.find()) {
            matcher.toString();
            //TODO find a way to get the unicode/
        }
    }
}
