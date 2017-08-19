package com.kevalpatel2106.emoji_keyboard;

import com.kevalpatel2106.emoji_keyboard.internal.emoticons.Emoticon;

/**
 * Created by Keval on 18-Aug-17.
 */

public interface EmoticonSelectListener {
    void emoticonSelected(Emoticon emoticon);

    void onBackSpace();
}
