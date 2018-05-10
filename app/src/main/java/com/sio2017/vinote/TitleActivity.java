
package com.sio2017.vinote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class TitleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        // Afficher l'écran titre pendant 1.5s
        new CountDownTimer(1000,500){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){
                Intent myIntent = new Intent(TitleActivity.this, MainActivity.class);
                startActivity(myIntent);
                TitleActivity.this.finish();
            }
        }.start();
    }


}
