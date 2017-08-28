/*
 * Copyright 2017 Keval Patel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.kevalpatel2106.emoticongifkeyboard.emoticons;

/**
 * Created by Keval on 18-Aug-17.
 * Listener to get notify when any emoticon gets selected from emoticon list or backspace pressed.
 */

public interface EmoticonSelectListener {

    /**
     * Callback to notify when any {@link Emoticon} gets select.
     *
     * @param emoticon {@link Emoticon} selected.
     */
    void emoticonSelected(Emoticon emoticon);

    /**
     * Callback to notify when backspace button is pressed.
     */
    void onBackSpace();
}
