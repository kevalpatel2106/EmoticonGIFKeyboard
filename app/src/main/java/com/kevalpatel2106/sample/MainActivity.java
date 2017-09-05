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

package com.kevalpatel2106.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bumptech.glide.Glide;
import com.kevalpatel2106.emoticongifkeyboard.EmoticonGIFKeyboardFragment;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.Emoticon;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonSelectListener;
import com.kevalpatel2106.emoticongifkeyboard.gifs.Gif;
import com.kevalpatel2106.emoticongifkeyboard.gifs.GifSelectListener;
import com.kevalpatel2106.emoticongifkeyboard.widget.EmoticonEditText;
import com.kevalpatel2106.emoticongifkeyboard.widget.EmoticonTextView;
import com.kevalpatel2106.emoticonpack.android8.Android8EmoticonProvider;
import com.kevalpatel2106.emoticonpack.ios.IosEmoticonProvider;
import com.kevalpatel2106.emoticonpack.windows10.Windows10EmoticonProvider;
import com.kevalpatel2106.gifpack.giphy.GiphyGifProvider;

public class MainActivity extends AppCompatActivity {
    @SuppressWarnings("unused")
    private static final String TAG = "MainActivity";
    private EmoticonGIFKeyboardFragment mEmoticonGIFKeyboardFragment;

    /**
     * Manually toggle soft keyboard visibility
     *
     * @param context calling context
     */
    public static void toggleKeyboardVisibility(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null)
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AppCompatImageView gifImageView = findViewById(R.id.selected_git_iv);

        //Set the emoticon text view.
        final EmoticonTextView textView = findViewById(R.id.selected_emoticons_tv);
        /*
          Set the custom emoticon icon provider. If you don't set any icon provider here, library
          will render system emoticons. Here we are setting iOS emoticons icon pack.
         */
        textView.setEmoticonProvider(IosEmoticonProvider.create());


        //Set the emoticon edit text.
        final EmoticonEditText editText = findViewById(R.id.selected_emoticons_et);
        /*
          Set the custom emoticon icon provider. If you don't set any icon provider here, library
          will render system emoticons. Here we are setting Android 8.0 emoticons icon pack.
         */
        editText.setEmoticonProvider(Android8EmoticonProvider.create());


        //Set emoticon configuration.
        EmoticonGIFKeyboardFragment.EmoticonConfig emoticonConfig = new EmoticonGIFKeyboardFragment.EmoticonConfig()

                /*
                  Set the custom emoticon icon provider. If you don't set any icon provider here, library
                  will render system emoticons. Here we are setting Windows 10 emoticons icon pack.
                 */
                .setEmoticonProvider(Windows10EmoticonProvider.create())

                /*
                  Set the emoticon select listener. This will notify you when user selects any emoticon from
                  list or user preses back button.
                  NOTE: The process of removing last character when user preses back space will handle
                  by library if your edit text is in focus.
                 */
                .setEmoticonSelectListener(new EmoticonSelectListener() {

                    @Override
                    public void emoticonSelected(Emoticon emoticon) {
                        //Do something with new emoticon.
                        Log.d(TAG, "emoticonSelected: " + emoticon.getUnicode());
                        editText.append(emoticon.getUnicode(),
                                editText.getSelectionStart(),
                                editText.getSelectionEnd());
                    }

                    @Override
                    public void onBackSpace() {
                        //Do something here to handle backspace event.
                        //The process of removing last character when user preses back space will handle
                        //by library if your edit text is in focus.
                    }
                });

        //Create GIF config
        EmoticonGIFKeyboardFragment.GIFConfig gifConfig = new EmoticonGIFKeyboardFragment
                /*
                  Set the desired GIF provider. Here we are using GIPHY to provide GIFs.
                  Create Giphy GIF provider by passing your key.
                  It is required to set GIF provider before adding fragment into container.
                 */
                .GIFConfig(GiphyGifProvider.create(this, "564ce7370bf347f2b7c0e4746593c179"))

                /*
                  Implement GIF select listener. This will notify you when user selects new GIF.
                 */
                .setGifSelectListener(new GifSelectListener() {
                    @Override
                    public void onGifSelected(@NonNull Gif gif) {
                        //Do something with the selected GIF.
                        Log.d(TAG, "onGifSelected: " + gif.getGifUrl());
                        Glide.with(MainActivity.this)
                                .load(gif.getGifUrl())
                                .asGif()
                                .placeholder(R.mipmap.ic_launcher)
                                .into(gifImageView);
                    }
                });



        /*
          Create instance of emoticon gif keyboard by passing emoticon and gif config. If you pass null
          to emoticon config, emoticon functionality will be disabled. Also, if you pass gif config
          as null, GIF functionality will be disabled.
         */
        mEmoticonGIFKeyboardFragment = EmoticonGIFKeyboardFragment
                .getNewInstance(findViewById(R.id.keyboard_container), emoticonConfig, gifConfig);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.keyboard_container, mEmoticonGIFKeyboardFragment)
                .commit();
        mEmoticonGIFKeyboardFragment.open(); //Open the fragment by default while initializing.


        //Set smiley button to open/close the emoticon gif keyboard
        findViewById(R.id.emoji_open_close_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmoticonGIFKeyboardFragment.toggle();
                toggleKeyboardVisibility(MainActivity.this);
            }
        });

        //Send button
        findViewById(R.id.emoji_send_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(editText.getText());
                editText.setText("");
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mEmoticonGIFKeyboardFragment == null || !mEmoticonGIFKeyboardFragment.handleBackPressed())
            super.onBackPressed();
    }
}
