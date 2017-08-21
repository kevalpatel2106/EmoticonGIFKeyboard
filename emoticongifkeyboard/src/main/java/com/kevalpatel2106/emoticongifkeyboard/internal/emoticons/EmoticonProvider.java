package com.kevalpatel2106.emoticongifkeyboard.internal.emoticons;

/**
 * Created by Keval Patel on 20/08/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

public interface EmoticonProvider {

    Emoticon getEmoticon(int codePointHex);


    boolean hasEmoticon(int codePointHex);
}