package com.kevalpatel2106.emoticonpack.twitter;

import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;

/**
 * Created by Keval Patel on 21/08/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

public class TwitterEmoticonProvider implements EmoticonProvider {

    private TwitterEmoticonProvider() {
    }

    public static TwitterEmoticonProvider create() {
        return new TwitterEmoticonProvider();
    }

    public int getIcon(String unicode) {
        return hasEmoticon(unicode) ? EmoticonList.EMOTICONS.get(unicode) : -1;
    }

    @Override
    public boolean hasEmoticon(String unicode) {
        return EmoticonList.EMOTICONS.containsKey(unicode);
    }
}
