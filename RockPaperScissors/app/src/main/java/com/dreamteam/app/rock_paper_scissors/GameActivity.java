package com.dreamteam.app.rock_paper_scissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Button b_akmuo, b_popierius, b_zirkles;
    TextView tv_taskai;
    ImageView iv_Android, iv_Zmogus;

    int ZmogausTaskai, AndroidTaskai = 0;

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
        setContentView(R.layout.activity_game);


        b_popierius = (Button) findViewById(R.id.b_popierius);
        b_akmuo = (Button) findViewById(R.id.b_akmuo);
        b_zirkles = (Button) findViewById(R.id.b_zirkles);

        iv_Android = (ImageView) findViewById(R.id.iv_Android);
        iv_Zmogus = (ImageView) findViewById(R.id.iv_Zmogus);

        tv_taskai = (TextView) findViewById(R.id.tv_Taskai);

        b_popierius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_Zmogus.setImageResource(R.drawable.popierius);
                String message = zaidimo_ejimas("popierius");
                Toast.makeText(GameActivity.this, message, Toast.LENGTH_SHORT).show();
                gameLimiter();

                tv_taskai.setText("Zaidejas: " + Integer.toString(ZmogausTaskai) + " " + "Android: " + Integer.toString(AndroidTaskai));
            }
        });
        b_akmuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_Zmogus.setImageResource(R.drawable.akmuo);
                String message = zaidimo_ejimas("akmuo");
                Toast.makeText(GameActivity.this, message, Toast.LENGTH_SHORT).show();
                gameLimiter();
                tv_taskai.setText("Zaidejas: " + Integer.toString(ZmogausTaskai) + " " + "Android: " + Integer.toString(AndroidTaskai));

            }
        });
        b_zirkles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_Zmogus.setImageResource(R.drawable.zirkles);
                String message = zaidimo_ejimas("zirkles");
                Toast.makeText(GameActivity.this, message, Toast.LENGTH_SHORT).show();
                gameLimiter();
                tv_taskai.setText("Zaidejas: " + Integer.toString(ZmogausTaskai) + " " + "Android: " + Integer.toString(AndroidTaskai));

            }
        });

    }
    public void gameLimiter() {

        if (ZmogausTaskai == 10) {
            Intent scoreTrack = new Intent(GameActivity.this, WinnerActivity.class);
            startActivity(scoreTrack);
        }
        if (AndroidTaskai == 10) {
            Intent loser = new Intent(GameActivity.this, LoserActivity.class);
            startActivity(loser);
        }

    }

//    public void getScore() {
//
//
//        if (myScore == 10) {
//            objWinner.winnerView.setText("You WON!!!");
//        } else if (androidScore == 10) {
//            objWinner.winnerView.setText("Android WON!!!");
//        } else objWinner.winnerView.setText("Nothing happened");
//    }

    public String zaidimo_ejimas(String zmogaus_pasirinkimas) {

        String android_pasirinkimas = " ";
        Random r = new Random();

        //kadangi yra trys pasirinkimo variantai tai bus 3 numeriai.
        int android_pasirinkimo_numeris = r.nextInt(3) + 1;

        if (android_pasirinkimo_numeris == 1) {
            android_pasirinkimas = "akmuo";
        } else if (android_pasirinkimo_numeris == 2) {
            android_pasirinkimas = "zirkles";
        } else if (android_pasirinkimo_numeris == 3) {
            android_pasirinkimas = "popierius";
        }

        if (android_pasirinkimas == "akmuo") {
            iv_Android.setImageResource(R.drawable.akmuo);
        } else if (android_pasirinkimas == "zirkles") {
            iv_Android.setImageResource(R.drawable.zirkles);
        } else if (android_pasirinkimas == "popierius") {
            iv_Android.setImageResource(R.drawable.popierius);
        }


        //zmogaus ir android pasirinkimo palyginimas kuris laimejo

        if (android_pasirinkimas == zmogaus_pasirinkimas) {
            return "Lygiosios. Niekas nelaimejo.";
        } else if (zmogaus_pasirinkimas == "akmuo" && android_pasirinkimas == "zirkles") {
            ZmogausTaskai++;
            return "Akmuo sulauzo zirkles. Zaidejas laimejo!";
        } else if (zmogaus_pasirinkimas == "zirkles" && android_pasirinkimas == "akmuo") {
            AndroidTaskai++;
            return "Akmuo sulauzo zirkles. Android laimejo!";
        } else if (zmogaus_pasirinkimas == "akmuo" && android_pasirinkimas == "popierius") {
            AndroidTaskai++;
            return "Popierius uzdengia akmeni. Android laimejo!";
        } else if (zmogaus_pasirinkimas == "popierius" && android_pasirinkimas == "akmuo") {
            ZmogausTaskai++;
            return "Popierius uzdengia akmeni. Zaidejas laimejo!";
        } else if (zmogaus_pasirinkimas == "zirkles" && android_pasirinkimas == "popierius") {
            ZmogausTaskai++;
            return "Zirkles sukarpo popieriu. Zaidejas laimejo!";
        } else if (zmogaus_pasirinkimas == "popierius" && android_pasirinkimas == "zirkles") {
            AndroidTaskai++;
            return "Zirkles sukarpo popieriu. Android laimejo!";
        } else return "";

    }

}