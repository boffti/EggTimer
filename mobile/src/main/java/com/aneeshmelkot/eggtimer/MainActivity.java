package com.aneeshmelkot.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    SeekBar seekbar;
    TextView timerView;
    Boolean counterIsActive = false;
    Button timerButton;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar = (SeekBar)findViewById(R.id.seekBar);
        timerView = (TextView)findViewById(R.id.timerView);
        timerButton = (Button)findViewById(R.id.timerButton);

        seekbar.setMax(600);
        seekbar.setProgress(30);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void updateTimer(int secondsLeft) {

        int minutes = secondsLeft/60;
        int seconds = secondsLeft - minutes * 60;

        String secondString;
        secondString = Integer.toString(seconds);

        if(secondString == "0") {
            secondString = "00";
        } else if(seconds <=9) {
            secondString = "0"+secondString;
        }

        timerView.setText(Integer.toString(minutes)+":"+secondString);

    }

    public void controlTimer(View view) {
        Log.i("Button Pressed","Pressed");

        if(counterIsActive==false) {
            counterIsActive = true;
            seekbar.setEnabled(false);
            timerButton.setText("Stop");


            timer =  new CountDownTimer(seekbar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    timerView.setText("0:00");
                    Log.i("Timer", "Done");
                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mPlayer.start();

                }
            }.start();

        } else {

                timerView.setText("0:00");
                seekbar.setProgress(30);
                timer.cancel();
                timerButton.setText("GO!");
                seekbar.setEnabled(true);
                counterIsActive = false;
                timerButton.setText("GO!");


        }

    }






}
