package com.dreamteam.app.rock_paper_scissors;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

public class LoserActivity extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    Button newGame;
    ImageButton buttonExit;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loser);
        final Animation rotatealpha = AnimationUtils.loadAnimation(this,
                R.anim.rotatealpha);
        final Animation rotate = AnimationUtils.loadAnimation(this,
                R.anim.rotate);
        final Animation rotatescale = AnimationUtils.loadAnimation(this,
                R.anim.rotatescale);
        final Animation rotatetranslate = AnimationUtils.loadAnimation(this,
                R.anim.rotatetranslate);

        buttonExit = findViewById(R.id.buttonExit);
        newGame = findViewById(R.id.newGame);
        handler = new Handler();


        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationSet sets = new AnimationSet(false);
//                sets.addAnimation(rotate);
//                sets.addAnimation(rotatealpha);
                sets.addAnimation(rotatescale);
                v.startAnimation(sets);
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(LoserActivity.this, GameActivity.class);
                        startActivity(intent);
                    }
                };
                handler.postDelayed(runnable, 1000);
            }
        });
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationSet sets = new AnimationSet(false);
                sets.addAnimation(rotatetranslate);
                v.startAnimation(sets);
                runnable = new Runnable() {

                    @Override
                    public void run() {
                        Intent intent = new Intent(LoserActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                };
                handler.postDelayed(runnable, 1000);

            }
        });

    }

}