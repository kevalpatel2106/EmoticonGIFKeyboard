package com.kevalpatel2106.emoticongifkeyboard.gifs;

import android.support.annotation.NonNull;

/**
 * Created by Keval on 22-Aug-17.
 * Listner to get notify when any GIF is selected from the lists.
 *
 * @author <a href='https://github.com/kevalpatel2106'>Kevalpatel2106</a>
 */

public interface GifSelectListener {

    /**
     * @param gif Gif selected.
     */
    void onGifSelected(@NonNull Gif gif);
}
