package com.kevalpatel2106.emoticongifkeyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kevalpatel2106.emoji_keyboard.KeyboardFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.selected_emoticons_tv);

        KeyboardFragment keyboardFragment = new KeyboardFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.keyboard_container, keyboardFragment)
                .commit();
    }
}
