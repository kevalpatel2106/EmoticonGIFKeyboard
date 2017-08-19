/*
 * Copyright 2014 Hieu Rocker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kevalpatel2106.emoji_keyboard.internal.emoticons;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * Emoticon POJO.
 *
 * @author Hieu Rocker (rockerhieu@gmail.com)
 * @author <a href='https://github.com/kevalpatel2106'>Kevalpatel2106</a>
 */
public final class Emoticon implements Parcelable {

    public static final Creator<Emoticon> CREATOR = new Creator<Emoticon>() {
        @Override
        public Emoticon createFromParcel(Parcel in) {
            return new Emoticon(in);
        }

        @Override
        public Emoticon[] newArray(int size) {
            return new Emoticon[size];
        }
    };
    /**
     * Unicode value of the emoticon.
     */
    @NonNull
    private final String unicode;
    /**
     * Custom icon for the emoticon. (If you don't want to use system default ones.)
     */
    @DrawableRes
    private int icon;

    public Emoticon(@DrawableRes int icon, @NonNull String unicode) {
        //noinspection ConstantConditions
        if (unicode == null) throw new RuntimeException("Unicode cannot be null.");

        this.icon = icon;
        this.unicode = unicode;
    }

    /**
     * Constructor for parcelable object.
     */
    public Emoticon(Parcel in) {
        this.icon = in.readInt();
        this.unicode = in.readString();

        //noinspection ConstantConditions
        if (unicode == null) throw new RuntimeException("Unicode cannot be null.");
    }

    public Emoticon(@NonNull String unicode) {
        //noinspection ConstantConditions
        if (unicode == null) throw new RuntimeException("Unicode cannot be null.");

        this.unicode = unicode;
    }

    public static Emoticon fromCodePoint(int codePoint) {
        return new Emoticon(newString(codePoint));
    }

    public static Emoticon fromChar(char ch) {
        return new Emoticon(Character.toString(ch));
    }

    public static Emoticon fromChars(String chars) {
        return new Emoticon(chars);
    }

    private static String newString(int codePoint) {
        if (Character.charCount(codePoint) == 1) {
            return String.valueOf(codePoint);
        } else {
            return new String(Character.toChars(codePoint));
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(icon);
        dest.writeString(unicode);
    }

    @DrawableRes
    public int getIcon() {
        return icon;
    }

    @NonNull
    public String getUnicode() {
        return unicode;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Emoticon && unicode.equals(((Emoticon) o).unicode);
    }

    @Override
    public int hashCode() {
        return unicode.hashCode();
    }

}
