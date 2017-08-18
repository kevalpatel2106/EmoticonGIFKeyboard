package com.kevalpatel2106.emoji_keyboard;

import com.kevalpatel2106.emoji_keyboard.internal.emoji.Emojicon;

/**
 * Created by Keval on 18-Aug-17.
 */

public interface EmoticonSelectListener {
    void emoticonSelected(Emojicon emojicon);

    void onBackSpace();
}
