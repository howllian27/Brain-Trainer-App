package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import androidx.gridlayout.widget.GridLayout;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button ans1, ans2, ans3, ans4, playAgain;
    TextView question;
    TextView ansComment;
    TextView scoreBoard;
    TextView timer;
    int actualAns;
    int score;
    int maxScore;

    public static int[] qnArray(TextView qn){
        Random rd = new Random();
        int[] ansArr = new int[2];

        for (int i=0; i < ansArr.length; i++){
            ansArr[i] = rd.nextInt(100);
        }

        String newText = Integer.toString(ansArr[0]) + "+" + Integer.toString(ansArr[1]);
        qn.setText(newText);
        return ansArr;
    }

    public void updateAns(int[] numbers){
        Random rd = new Random();
        int[] ansArr = new int[4];
        actualAns = numbers[0] + numbers[1];
        ansArr[(int) Math.round(Math.random() * 3)] = actualAns;

        int maxNum = actualAns + 10;
        int minNum = actualAns - 10;
        HashMap<Integer, Integer> numList = new HashMap<>();
        for (int i=0; i < ansArr.length; i++){
            if (ansArr[i] != actualAns){
                ansArr[i] = rd.nextInt(maxNum - minNum + 1) + minNum;
                while (ansArr[i] == actualAns || numList.containsKey(ansArr[i])){
                    ansArr[i] = rd.nextInt(maxNum - minNum + 1) + minNum;
                }
                numList.put(ansArr[i], 1);
            }
        }

        ans1.setText(Integer.toString(ansArr[0]));
        ans2.setText(Integer.toString(ansArr[1]));
        ans3.setText(Integer.toString(ansArr[2]));
        ans4.setText(Integer.toString(ansArr[3]));

    }

    public void selectAns(View view){
        Button userAns = (Button) view;
        int selectedAns = Integer.parseInt(userAns.getText().toString());
        if (selectedAns == actualAns){
            ansComment.setText("Correct!");
            score++;
        } else {
            ansComment.setText("Wrong :(");
        }
        maxScore++;
        String newScore = Integer.toString(score) + "/" + Integer.toString(maxScore);
        scoreBoard.setText(newScore);
        int[] numbersInQn = qnArray(question);
        updateAns(numbersInQn);
    }

    public void replay(View view){
        scoreBoard.setText("0/0");
        timer.setText("60s");
        ans1.setEnabled(true);
        ans2.setEnabled(true);
        ans3.setEnabled(true);
        ans4.setEnabled(true);
        playAgain.setVisibility(View.INVISIBLE);
        ansComment.setText("");
        score = 0;
        maxScore = 0;

        int[] numbersInQn = qnArray(question);
        updateAns(numbersInQn);

        CountDownTimer countdown = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                int timeInSeconds = (int) l/1000;
                String timeRemaining = Integer.toString(timeInSeconds) + "s";
                timer.setText(timeRemaining);
            }

            @Override
            public void onFinish() {
                ans1.setEnabled(false);
                ans2.setEnabled(false);
                ans3.setEnabled(false);
                ans4.setEnabled(false);
                playAgain.setVisibility(View.VISIBLE);
                ansComment.setText("Done!");
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = findViewById(R.id.timer);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        playAgain = findViewById(R.id.playAgain);
        question = findViewById(R.id.question);
        ansComment = findViewById(R.id.ansComment);
        scoreBoard = findViewById(R.id.scoreBoard);
        score = 0;
        maxScore = 0;

        int[] numbersInQn = qnArray(question);
        updateAns(numbersInQn);

        CountDownTimer countdown = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                int timeInSeconds = (int) l/1000;
                String timeRemaining = Integer.toString(timeInSeconds) + "s";
                timer.setText(timeRemaining);
            }

            @Override
            public void onFinish() {
                ans1.setEnabled(false);
                ans2.setEnabled(false);
                ans3.setEnabled(false);
                ans4.setEnabled(false);
                playAgain.setVisibility(View.VISIBLE);
                ansComment.setText("Done!");
            }
        }.start();

    }
}