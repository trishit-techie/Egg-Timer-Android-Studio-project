package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    SeekBar timerSeekBar;
    Button countDownButton;
    CountDownTimer countDownTimer;
    boolean counterIsActive=false;
    public void resetTimer()
    {
        counterIsActive=false;
        timerSeekBar.setEnabled(true);
        timerTextView.setText("00:00");
        timerSeekBar.setProgress(0);
        countDownTimer.cancel();
    }
    public  void startCountDown(View view) {
        if (counterIsActive == true) {

            resetTimer();
        }
        else {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            countDownButton = (Button) findViewById(R.id.startButton);
            countDownTimer=new CountDownTimer(timerSeekBar.getProgress() * 1000, 1000) {
                public void onTick(long millisecondsuntildone) {

                    updatedTime((int) millisecondsuntildone / 1000);
                }

                public void onFinish() {
                    Log.i("It's done", "Time is out!!");
                    Toast.makeText(getApplicationContext(), "Your time is out!!", Toast.LENGTH_LONG).show();
                    resetTimer();
                }
            }.start();
        }
    }

    public void updatedTime(int p)
    {
        int minutes=p/60;
        int seconds = p-(minutes*60);
        String secondString = Integer.toString(seconds);
        String minutesString = Integer.toString(minutes);
        if(secondString.equals("0")||secondString.equals("1")||secondString.equals("2")||secondString.equals("3")||secondString.equals("4")||secondString.equals("5")||secondString.equals("6")||secondString.equals("7")||secondString.equals("8")||secondString.equals("9"))
        {
            secondString="0"+secondString;
        }
        if(minutesString.equals("0")||minutesString.equals("1")||minutesString.equals("2")||minutesString.equals("3")||minutesString.equals("4")||minutesString.equals("5")||minutesString.equals("6")||minutesString.equals("7")||minutesString.equals("8")||minutesString.equals("9"))
        {
            minutesString="0"+minutesString;
        }
        timerTextView.setText(minutesString+":"+secondString);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         timerSeekBar = (SeekBar)findViewById(R.id.timerSeekBar);
         timerTextView = (TextView)findViewById(R.id.countDownTextView);
        timerSeekBar.setMax(3600);
        timerSeekBar.setProgress(0);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               updatedTime(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
