package com.example.quizmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Quiz extends AppCompatActivity
{
    TextView countDownTime, questionText, roundText;
    TextView answer1, answer2, answer3, answer4;
    Button startQuizButton, startNextRoundButton;
    CountDownTimer countDownTimer;
    String[] questionsArray;
    List<String> questionsList = new ArrayList<String>();
    String correctAnswer;
    private long timeLeftInMillis;
    private int rounds, roundsCounter = 1;
    private int p1 = 0, p2 = 0, p3 = 0, p4 = 0; //Players points


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.question_text);
        answer1 = findViewById(R.id.ans1_text);
        answer2 = findViewById(R.id.ans2_text);
        answer3 = findViewById(R.id.ans3_text);
        answer4 = findViewById(R.id.ans4_text);
        roundText = findViewById(R.id.round_text);
        countDownTime = findViewById(R.id.countdown_timer);
        startQuizButton = findViewById(R.id.start_quiz_button);
        startNextRoundButton = findViewById(R.id.next_question_button);

        FillQuestions();

        rounds = Integer.parseInt(SettingsActivity.playerMode) * 5 - 5; //Number of rounds
        roundText.setText("Question 0/" + Integer.toString(rounds));

        timeLeftInMillis = SettingsActivity.duration * 60000; //60,000 milliseconds = 1 minute
        UpdateTimerText();
    }

    private void FillQuestions()
    {
        if (SettingsActivity.questionsCategory.equalsIgnoreCase("Geography"))
        {questionsArray = getResources().getStringArray(R.array.geography_array);}
        else if (SettingsActivity.questionsCategory.equalsIgnoreCase("History"))
        {questionsArray = getResources().getStringArray(R.array.history_array);}
        else {questionsArray = getResources().getStringArray(R.array.pop_culture_array);}
        Collections.addAll(questionsList, questionsArray);
    }

    private void UpdateTimerText()
    {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        countDownTime.setText(timeLeftFormatted);
    }

    public void StartQuiz(View view)
    {
        startQuizButton.setVisibility(View.INVISIBLE);
        roundText.setText("Question " + Integer.toString(roundsCounter) + '/' + Integer.toString(rounds));
        DoQuiz();
    }

    public void StartNextRound(View view)
    {
        startNextRoundButton.setVisibility(View.INVISIBLE);
        roundsCounter += 1;
        roundText.setText("Question " + Integer.toString(roundsCounter) + '/' + Integer.toString(rounds));
        timeLeftInMillis = SettingsActivity.duration * 60000;
        answer1.setBackgroundColor(Color.TRANSPARENT);
        answer2.setBackgroundColor(Color.TRANSPARENT);
        answer3.setBackgroundColor(Color.TRANSPARENT);
        answer4.setBackgroundColor(Color.TRANSPARENT);
        DoQuiz();
    }

    private void DoQuiz()
    {
        FillQuizText();

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long l)
            {
                timeLeftInMillis = l;
                UpdateTimerText();
            }

            @Override
            public void onFinish()
            {
                if (answer1.getText().equals(correctAnswer))
                {
                    answer1.setBackgroundColor(Color.YELLOW);
                    YoYo.with(Techniques.Pulse).duration(1000).playOn(answer1);
                }
                else if (answer2.getText().equals(correctAnswer))
                {
                    answer2.setBackgroundColor(Color.YELLOW);
                    YoYo.with(Techniques.Pulse).duration(1000).playOn(answer1);
                }
                else if (answer3.getText().equals(correctAnswer))
                {
                    answer3.setBackgroundColor(Color.YELLOW);
                    YoYo.with(Techniques.Pulse).duration(1000).playOn(answer1);
                }
                else
                {
                    answer4.setBackgroundColor(Color.YELLOW);
                    YoYo.with(Techniques.Pulse).duration(1000).playOn(answer1);
                }
                if (rounds != roundsCounter) {startNextRoundButton.setVisibility(View.VISIBLE);}
            }
        }.start();
    }

    private void FillQuizText()
    {
        int position = ThreadLocalRandom.current().nextInt(0, questionsList.size());
        String question = questionsList.get(position).split(":")[0];
        String[] answers = questionsList.get(position).split(":")[1].split("/");
        for (String answer : answers)
        {
            if (answer.contains("!"))
            {
                correctAnswer = answer;
                correctAnswer = correctAnswer.replace("!", "");
                break;
            }
        }
        questionsList.remove(position);
        questionText.setText(question);
        YoYo.with(Techniques.Bounce).duration(1000).playOn(questionText);

        answer1.setText(answers[0].replace("!", ""));
        answer2.setText(answers[1].replace("!", ""));
        answer3.setText(answers[2].replace("!", ""));
        answer4.setText(answers[3].replace("!", ""));
        YoYo.with(Techniques.FadeIn).duration(1000).playOn(answer1);
        YoYo.with(Techniques.FadeIn).duration(1000).playOn(answer2);
        YoYo.with(Techniques.FadeIn).duration(1000).playOn(answer3);
        YoYo.with(Techniques.FadeIn).duration(1000).playOn(answer4);
    }
}