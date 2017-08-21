package com.kevalpatel2106.emoticonpack.emojione;

import com.kevalpatel2106.emoticongifkeyboard.emoticons.Emoticon;
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
    public Emoticon getEmoticon(String unicode) {
        if (hasEmoticon(unicode)) {
            int index = EmoticonList.EMOTICONS.lastIndexOf(new Emoticon(unicode));
            if (index > 0) return EmoticonList.EMOTICONS.get(index);
        }
        return new Emoticon(unicode);
    }

    @Override
    public boolean hasEmoticon(String unicode) {
        return EmoticonList.EMOTICONS.contains(new Emoticon(unicode));
    }
}
