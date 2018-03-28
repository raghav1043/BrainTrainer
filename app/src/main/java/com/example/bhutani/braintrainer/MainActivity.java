package com.example.bhutani.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timerText,scoreTextView;
    Button playAgain;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    boolean quizIsActive = true;
    CountDownTimer countDownTimer;
    TextView quesText;
    int total=0,score=0,locationOfCorrectAnswer;
    Button option0,option1,option2,option3;
    TextView scoreUpdate;

    public void generator(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);

        quesText.setText(Integer.toString(a) + " + " + Integer.toString(b));
        answers.clear();
        locationOfCorrectAnswer = random.nextInt(4);
        int incorrectAnswer = random.nextInt(41);
        for (int i = 0; i < 4; i++) {

            if (i == locationOfCorrectAnswer) {

                answers.add(a + b);
            } else {

                incorrectAnswer = random.nextInt(41);
                while (incorrectAnswer == (a + b)) {
                    incorrectAnswer = random.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        option0.setText(Integer.toString(answers.get(0)));
        option1.setText(Integer.toString(answers.get(1)));
        option2.setText(Integer.toString(answers.get(2)));
        option3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){
        if(quizIsActive) {
            scoreTextView.setVisibility(View.VISIBLE);
            total++;
            String message;
            if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
                score++;
                //Log.i(view.getTag().toString(),"  "  +Integer.toString(locationOfCorrectAnswer));
                message = "correct";
            } else {
                //Log.i(view.getTag().toString(),"  "  +Integer.toString(locationOfCorrectAnswer));
                message = "incorrect";
            }
            scoreTextView.setText(message);
            scoreUpdate.setText(Integer.toString(score) + "/" + Integer.toString(total));
            generator();
        }
    }


    public void playAgainFunction(View view){
        quizIsActive = true;
        score=0;
        total=0;
        scoreUpdate.setText(Integer.toString(score)+"/"+Integer.toString(total));
        quizIsActive = true;
        timerText.setText("00:30");
        countDownTimer.start();
        playAgain.setVisibility(View.INVISIBLE);
        scoreTextView.setVisibility(View.INVISIBLE);
        generator();
    }

    public void startFunction(View view){

        RelativeLayout startScreenLayout = (RelativeLayout)findViewById(R.id.startScreenLayout);
        startScreenLayout.setVisibility(View.INVISIBLE);

        countDownTimer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisecondsuntilleft) {
                int seconds = (int) millisecondsuntilleft / 1000;

                String secondString;

                if (seconds <= 9) {

                    secondString = "00 " + ":0" + Integer.toString(seconds);
                } else {

                    secondString = "00:" + Integer.toString(seconds);
                }
                timerText.setText(secondString);
            }

            public void onFinish() {

                timerText.setText("00:00");
                playAgain.setVisibility(View.VISIBLE);
                String text = "your score : "+Integer.toString(score)+"/"+Integer.toString(total);
                scoreTextView.setText(text);
                scoreTextView.setVisibility(View.VISIBLE);
                quizIsActive = false;
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerText = (TextView) findViewById(R.id.timerTextView);
        playAgain = (Button)findViewById(R.id.playAgain);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        quesText = (TextView)findViewById(R.id.quesTextView);
        option0 = (Button)findViewById(R.id.option0Button);
        option1 = (Button)findViewById(R.id.option1Button);
        option2 = (Button)findViewById(R.id.option2Button);
        option3 = (Button)findViewById(R.id.option3Button);
        scoreUpdate = (TextView)findViewById(R.id.scoreUpdate);

        generator();

    }
}
