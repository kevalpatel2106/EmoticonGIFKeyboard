package com.kevalpatel2106.emoticonspack.google;

import com.kevalpatel2106.emoticongifkeyboard.emoticons.Emoticon;
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
