package com.kevalpatel2106.emoticongifkeyboard;

import com.kevalpatel2106.emoticongifkeyboard.internal.emoticons.Emoticon;

/**
 * Created by Keval on 18-Aug-17.
 */

public interface EmoticonSelectListener {
    void emoticonSelected(Emoticon emoticon);

    void onBackSpace();
}
