package com.kevalpatel2106.emoticongifkeyboard.widget;

import android.support.annotation.NonNull;

import com.kevalpatel2106.emoticongifkeyboard.internal.emoticons.Emoticon;
import com.kevalpatel2106.emoticongifkeyboard.internal.emoticons.EmoticonProvider;

/**
 * Created by Keval Patel on 20/08/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

final class SystemEmoticonsProvider implements EmoticonProvider {

    @Override
    public Emoticon getEmoticon(@NonNull String unicode) {
        return new Emoticon(unicode);
    }
}
