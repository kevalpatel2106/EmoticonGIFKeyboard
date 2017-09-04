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

package com.kevalpatel2106.emojiscrapper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Keval on 24-Aug-17.
 */
public class Emoji {

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("category")
    @Expose
    String category;

    @SerializedName("unicode")
    @Expose
    String unicode;

    @SerializedName("codepoint")
    @Expose
    ArrayList<String> codePoints = new ArrayList<>();

    @SerializedName("tag")
    @Expose
    ArrayList<String> tags = new ArrayList<>();

    @SerializedName("variants")
    @Expose
    ArrayList<Emoji> variants = new ArrayList<>();

    transient HashMap<String, File> imageUrls = new HashMap<>();
}
