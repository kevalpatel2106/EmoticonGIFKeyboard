package com.kevalpatel2106.emoticongifkeyboard.emoticons;

import android.support.annotation.DrawableRes;

/**
 * Created by Keval Patel on 20/08/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

public interface EmoticonProvider {

    @DrawableRes
    int getIcon(String unicode);

    boolean hasEmoticon(String unicode);
}