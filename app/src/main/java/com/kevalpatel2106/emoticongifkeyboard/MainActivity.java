package com.kevalpatel2106.emoticongifkeyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kevalpatel2106.emoji_keyboard.KeyboardFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.keyboard_container, new KeyboardFragment()).commit();
    }
}
