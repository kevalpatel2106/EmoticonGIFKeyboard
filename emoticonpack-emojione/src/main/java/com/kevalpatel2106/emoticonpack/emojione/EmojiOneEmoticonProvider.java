package com.kevalpatel2106.emoticonpack.emojione;

import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;

/**
 * Created by Keval Patel on 21/08/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

public class EmojiOneEmoticonProvider implements EmoticonProvider {

    private EmojiOneEmoticonProvider() {
    }

    public static EmojiOneEmoticonProvider create() {
        return new EmojiOneEmoticonProvider();
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
