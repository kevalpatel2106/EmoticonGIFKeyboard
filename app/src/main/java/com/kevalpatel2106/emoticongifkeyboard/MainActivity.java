package com.kevalpatel2106.emoticongifkeyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kevalpatel2106.emoji_keyboard.EmoticonSelectListener;
import com.kevalpatel2106.emoji_keyboard.KeyboardFragment;
import com.kevalpatel2106.emoji_keyboard.internal.emoticons.Emoticon;
import com.kevalpatel2106.emoji_keyboard.internal.gifs.giphy.GiphyGifLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.selected_emoticons_tv);

        KeyboardFragment keyboardFragment = new KeyboardFragment();
        keyboardFragment.setEmoticonSelectListener(new EmoticonSelectListener() {
            @Override
            public void emoticonSelected(Emoticon emoticon) {
                textView.append(emoticon.getUnicode());
            }

            @Override
            public void onBackSpace() {
                if (textView.length() > 1) {
                    textView.setText(textView.getText()
                            .toString()
                            .substring(0, textView.length() - 2));
                }
            }
        });
        keyboardFragment.setGifLoader(GiphyGifLoader.create(this, "564ce7370bf347f2b7c0e4746593c179"));

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.keyboard_container, keyboardFragment)
                .commit();
    }
}
