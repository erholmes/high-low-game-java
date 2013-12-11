package com.iti.guessinggamejava;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

//this is the main activity. It displays a splash screen and then transitions to the game
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.splashscreen);
            
            /* Start up the splash screen and main menu in a time delayed thread */
            new Handler().postDelayed(new Thread() {
                    @Override
                    public void run() {
                               Intent guessingGame = new Intent(MainActivity.this,
                                               GuessingGame.class);
                               MainActivity.this.startActivity(guessingGame);
                               MainActivity.this.finish();
                               overridePendingTransition(R.layout.fadein,R.layout.fadeout);
                    }
            }, 4000);
                    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
    }
}
