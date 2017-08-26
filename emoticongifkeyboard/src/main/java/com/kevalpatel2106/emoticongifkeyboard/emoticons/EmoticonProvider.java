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

import android.support.annotation.DrawableRes;

/**
 * Created by Keval Patel on 20/08/17.
 * Custom emoticon icon provider to display custom emoticon images instead of system emoticons.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

public interface EmoticonProvider {

    /**
     * Get the drawable resource for the given unicode.
     *
     * @param unicode Unicode for which icon is required.
     * @return Icon drawable resource id or -1 if there is no drawable for given unicode.
     */
    @DrawableRes
    int getIcon(String unicode);

    /**
     * Check if the icon pack contains the icon image for given unicode/emoticon?
     *
     * @param unicode Unicode to check.
     * @return True if the icon found else false.
     */
    boolean hasEmoticonIcon(String unicode);
}