package com.kevalpatel2106.emoticonspack.google;

import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;

/**
 * Created by Keval Patel on 21/08/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

public class GoogleEmoticonProvider implements EmoticonProvider {

    private GoogleEmoticonProvider() {
    }

    public static GoogleEmoticonProvider create() {
        return new GoogleEmoticonProvider();
    }

    @Override
    public int getIcon(String unicode) {
        return hasEmoticon(unicode) ? EmoticonList.EMOTICONS.get(unicode) : -1;
    }

    @Override
    public boolean hasEmoticon(String unicode) {
        return EmoticonList.EMOTICONS.containsKey(unicode);
    }
}
