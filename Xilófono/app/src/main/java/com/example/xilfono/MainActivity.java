package com.example.xilfono;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    Button bdo, bre, bmi, bfa, bsol, bla, bsi;
    Context c;

    ImageButton sound_off, sound_on;

    boolean sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound = true;

        c = this;

        bdo = (Button) findViewById(R.id.buttonDo);
        bre = (Button) findViewById(R.id.buttonRE);
        bmi = (Button) findViewById(R.id.buttonMi);
        bfa = (Button) findViewById(R.id.buttonFa);
        bsol = (Button) findViewById(R.id.buttonSol);
        bla = (Button) findViewById(R.id.buttonLa);
        bsi = (Button) findViewById(R.id.buttonSi);

        sound_on = (ImageButton)findViewById(R.id.imageButton);
        sound_off = (ImageButton)findViewById(R.id.imageButton2);



        sound_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sound = false;
                sound_on.setVisibility(View.GONE);
                sound_off.setVisibility(View.VISIBLE);

            }
        });

        sound_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sound = true;
                sound_off.setVisibility(View.GONE);
                sound_on.setVisibility(View.VISIBLE);

            }
        });

        bdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound){
                    MediaPlayer mp = MediaPlayer.create(c, R.raw.note1);
                    mp.start();
                }

            }
        });

        bre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sound){
                    MediaPlayer mp = MediaPlayer.create(c, R.raw.note2);
                    mp.start();
                }
            }
        });

        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound){
                    MediaPlayer mp = MediaPlayer.create(c, R.raw.note3);
                    mp.start();
                }
            }
        });

        bfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound){
                    MediaPlayer mp = MediaPlayer.create(c, R.raw.note4);
                    mp.start();
                }
            }
        });

        bsol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound){
                    MediaPlayer mp = MediaPlayer.create(c, R.raw.note5);
                    mp.start();
                }
            }
        });

        bla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound){
                    MediaPlayer mp = MediaPlayer.create(c, R.raw.note6);
                    mp.start();
                }
            }
        });

        bsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound){
                    MediaPlayer mp = MediaPlayer.create(c, R.raw.note7);
                    mp.start();
                }
            }
        });

    }


}
