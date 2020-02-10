package com.example.tryquiz2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button buttonA, buttonB, buttonC, buttonD;
    TextView questionText, triviaQuizText, timeText, resultText, coinText;
    QuizDbHelper QuizHelper;
    Questions currentQuestion;
    List<Questions> list;
    int qid = 0;
    int timeValue = 20;
    int coinValue = 0;
    CountDownTimer countDownTimer;
    Typeface tb, sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText =  findViewById(R.id.triviaQuestion);
        buttonA =  findViewById(R.id.buttonA);
        buttonB =  findViewById(R.id.buttonB);
        buttonC =  findViewById(R.id.buttonC);
        buttonD =  findViewById(R.id.buttonD);
        triviaQuizText =  findViewById(R.id.triviaQuizText);
        timeText =  findViewById(R.id.timeText);
        resultText =  findViewById(R.id.resultText);
        coinText = findViewById(R.id.coinText);


        triviaQuizText.setTypeface(sb);
        questionText.setTypeface(tb);
        buttonA.setTypeface(tb);
        buttonB.setTypeface(tb);
        buttonC.setTypeface(tb);
        buttonD.setTypeface(tb);
        timeText.setTypeface(tb);
        resultText.setTypeface(sb);
        coinText.setTypeface(tb);


        QuizHelper = new QuizDbHelper(this);
        QuizHelper.getWritableDatabase();

        if (QuizHelper.getAllOfTheQuestions().size() == 0) {

            QuizHelper.allQuestion();
        }


        list = QuizHelper.getAllOfTheQuestions();
        Collections.shuffle(list);
        currentQuestion = list.get(qid);

        countDownTimer = new CountDownTimer(22000, 1000) {
            public void onTick(long millisUntilFinished) {

                timeText.setText(String.valueOf(timeValue) + "\"");
                timeValue -= 1;
                if (timeValue == -1) {
                    resultText.setText(getString(R.string.timeup));
                    disableButton();
                }
            }

            public void onFinish() {
                timeUp();
            }
        }.start();
        updateQueAndOptions();
    }


    public void updateQueAndOptions() {
        questionText.setText(currentQuestion.getQuestion());
        buttonA.setText(currentQuestion.getOptA());
        buttonB.setText(currentQuestion.getOptB());
        buttonC.setText(currentQuestion.getOptC());
        buttonD.setText(currentQuestion.getOptD());

        timeValue = 20;


        countDownTimer.cancel();
        countDownTimer.start();
        coinText.setText(String.valueOf(coinValue));
        coinValue++;

    }


    public void buttonA(View view) {


        if (currentQuestion.getOptA().equals(currentQuestion.getAnswer())) {
            buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));


            if (qid < list.size() - 1) {

                disableButton();
                correctDialog();
            }
            else {

                gameWon();
            }
        }
        else {

            gameLostPlayAgain();

        }
    }


    public void buttonB(View view) {
        if (currentQuestion.getOptB().equals(currentQuestion.getAnswer())) {
            buttonB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {
            gameLostPlayAgain();
        }
    }


    public void buttonC(View view) {
        if (currentQuestion.getOptC().equals(currentQuestion.getAnswer())) {
            buttonC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {

            gameLostPlayAgain();
        }
    }


    public void buttonD(View view) {
        if (currentQuestion.getOptD().equals(currentQuestion.getAnswer())) {
            buttonD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {
            gameLostPlayAgain();
        }
    }

    public void gameWon() {
        Intent intent = new Intent(this, GameWon.class);
        startActivity(intent);
        finish();
    }


    public void gameLostPlayAgain() {
        Intent intent = new Intent(this, PlayAgain.class);
        startActivity(intent);
        finish();
    }


    public void timeUp() {
        Intent intent = new Intent(this, TimeUpActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer.start();
    }


    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }


    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }


    @Override
    public void onBackPressed() {
        finish();
    }


    public void correctDialog() {
        final Dialog dialogCorrect = new Dialog(MainActivity.this);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogCorrect.setContentView(R.layout.dialog_correct);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();
        onPause();


        TextView correctText =  dialogCorrect.findViewById(R.id.correctText);
        Button buttonNext =  dialogCorrect.findViewById(R.id.dialogNext);


        correctText.setTypeface(sb);
        buttonNext.setTypeface(sb);


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dialogCorrect.dismiss();
                qid++;
                currentQuestion = list.get(qid);
                updateQueAndOptions();
                resetColor();
                enableButton();
            }
        });
    }

    public void resetColor() {
        buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
    }


    public void disableButton() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }


    public void enableButton() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }

}

