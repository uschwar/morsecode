package com.example.morsetrainer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MorseTrainer extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse_trainer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_morse_trainer, menu);
        return true;
    }
}
