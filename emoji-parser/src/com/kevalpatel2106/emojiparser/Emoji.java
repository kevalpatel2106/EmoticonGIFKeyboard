package com.kevalpatel2106.emojiparser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    HashMap<String, String> imageUrls = new HashMap<>();
}
