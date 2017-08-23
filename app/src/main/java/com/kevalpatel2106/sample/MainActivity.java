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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.kevalpatel2106.emoticongifkeyboard.KeyboardFragment;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.Emoticon;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonSelectListener;
import com.kevalpatel2106.emoticonpack.ios.IosEmoticonProvider;
import com.kevalpatel2106.gifpack.giphy.GiphyGifProvider;

public class MainActivity extends AppCompatActivity {
    @SuppressWarnings("unused")
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.selected_emoticons_tv);

        KeyboardFragment keyboardFragment = new KeyboardFragment();
        keyboardFragment.setEmoticonProvider(IosEmoticonProvider.create());
        keyboardFragment.setEmoticonSelectListener(new EmoticonSelectListener() {
            @Override
            public void emoticonSelected(Emoticon emoticon) {
                Log.d(TAG, "emoticonSelected: " + emoticon.getUnicode());
                textView.append(emoticon.getUnicode());
            }

            @Override
            public void onBackSpace() {
                if (textView.length() > 0) {
                    textView.setText(textView.getText()
                            .toString()
                            .substring(0, textView.length() - 1));
                }
            }
        });
        keyboardFragment.setGifProvider(GiphyGifProvider.create(this, "564ce7370bf347f2b7c0e4746593c179"));
//        keyboardFragment.setGifProvider(TenorGifProvider.create(this, "LIVDSRZULELA"));

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.keyboard_container, keyboardFragment)
                .commit();
    }
}
