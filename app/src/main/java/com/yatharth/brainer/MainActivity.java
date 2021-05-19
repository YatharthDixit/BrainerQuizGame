package com.yatharth.brainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton, restartButton; GridLayout grid; TextView ques, scoretoView, timeRemaining, finalScore; int wrongAns ,locationOfCorrect,a,b, yourscore=0, totalscore=0;

    Random rand;CountDownTimer countDownTimer; boolean isTimerRunning=false;
    TextView button0, button1, button2, button3;
    public void start(View view){
        if(isTimerRunning){
            countDownTimer.cancel();
        }
        yourscore=0;
        totalscore=0;
        setInterface();
        timer();
        finalScore.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.INVISIBLE);
        scoretoView.setVisibility(View.VISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        ques.setVisibility(View.VISIBLE);
        timeRemaining.setVisibility(View.VISIBLE);
        restartButton.setVisibility(View.VISIBLE);
    }

    public void timer(){
        isTimerRunning=true;
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                timeRemaining.setText((int)l/1000+" Seconds Left!");
            }
            @Override
            public void onFinish() {
                isTimerRunning=false;
                goButton.setVisibility(View.INVISIBLE);
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                ques.setVisibility(View.INVISIBLE);
                timeRemaining.setVisibility(View.VISIBLE);
                restartButton.setVisibility(View.VISIBLE);
                scoretoView.setVisibility(View.INVISIBLE);
                finalScore.setText("Total Ques: "+totalscore+"\nYour Score: "+yourscore);
                finalScore.setVisibility(View.VISIBLE);

            }
        }.start();
    }

    public void setInterface(){
        a = rand.nextInt(500);
        b = rand.nextInt(500);
        ques.setText(a+"+"+b);
        locationOfCorrect= rand.nextInt(4);
        ArrayList<Integer> options = new ArrayList<Integer>();
        for(int i =0; i<4; i++){
            if(i==locationOfCorrect){
                options.add(a+b);
            }else{
                wrongAns=(rand.nextInt(999));
                while(wrongAns==(a+b)){
                    wrongAns=(rand.nextInt(999));
                }
                options.add(wrongAns);
            }
        }
        button0.setText(String.valueOf(options.get(0)));
        button1.setText(String.valueOf(options.get(1)));
        button2.setText(String.valueOf(options.get(2)));
        button3.setText(String.valueOf(options.get(3)));
        scoretoView.setText("Score : "+yourscore+ "/"+totalscore);
    }

    public void ansPressed(View view){
        totalscore++;
        String ans = view.getTag().toString();
        Log.i("Button Pressed : ", ans);
        if (Integer.parseInt(ans)==locationOfCorrect){
            yourscore++;        }

    setInterface();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton=  findViewById(R.id.goButton);
        restartButton=  findViewById(R.id.restartButton);
        scoretoView=(TextView)findViewById(R.id.yourScore);
        button0 = (TextView)findViewById(R.id.textView0);
        button1 = (TextView)findViewById(R.id.textView1);
        button2 =(TextView) findViewById(R.id.textView2);
        button3 = (TextView)findViewById(R.id.textView3);
        finalScore= (TextView)findViewById(R.id.finishScore);
        timeRemaining=(TextView) findViewById(R.id.timeRemaining);
        ques= findViewById(R.id.ques);
        rand = new Random();



        setInterface();





    }
}