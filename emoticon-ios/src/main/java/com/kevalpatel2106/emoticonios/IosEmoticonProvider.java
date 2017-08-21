package com.kevalpatel2106.emoticonios;

import com.kevalpatel2106.emoticongifkeyboard.internal.emoticons.Emoticon;
import com.kevalpatel2106.emoticongifkeyboard.internal.emoticons.EmoticonProvider;

/**
 * Created by Keval Patel on 21/08/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

public class IosEmoticonProvider implements EmoticonProvider {


    public static IosEmoticonProvider create() {
        return new IosEmoticonProvider();
    }

    @Override
    public Emoticon getEmoticon(int codePointHex) {
        if (hasEmoticon(codePointHex)) {
            return new Emoticon(codePointHex, EmoticonList.sEmoticons.get(codePointHex));
        } else {
            return Emoticon.fromCodePoint(codePointHex);
        }
    }

    @Override
    public boolean hasEmoticon(int codePointHex) {
        return EmoticonList.sEmoticons.indexOfKey(codePointHex) > 0;
    }
}
