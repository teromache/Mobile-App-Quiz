package com.example.login;

import android.content.Intent;
import android.demo.entities.Account;
import android.demo.entities.Mark;
import android.demo.entities.database.AccountDB;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class QuizResults extends AppCompatActivity
{

    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        final TextView correctAnswers = findViewById(R.id.correctAnswers);
        final TextView incorrectAnswers = findViewById(R.id.incorrectAnswers);
        final Button startNewQuizBtn = findViewById(R.id.startNewQuizButton);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(new Date());


        final int getCorrectAnswers = getIntent().getIntExtra("correct",0);
        final int getInCorrectAnswers = getIntent().getIntExtra("incorrect",0);
        final String getTopicName = getIntent().getStringExtra("topic");

        correctAnswers.setText("Correct Answers : "+getCorrectAnswers);
        incorrectAnswers.setText("Incorrect Answers : "+getInCorrectAnswers);

        startNewQuizBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Account account = (Account) intent.getSerializableExtra("account");
                AccountDB accountDB = new AccountDB(getApplicationContext());
                Account currentAccount = accountDB.find(account.getId());

                Mark mark = new Mark();
                mark.setUserid(account.getId());
                mark.setUsername(account.getUsername());
                mark.setSubject(getTopicName);
                mark.setcMark(getCorrectAnswers);
                mark.setDate(currentDate);
                mark.setwMark(getInCorrectAnswers);

               if (accountDB.create_Mark(mark)) {
                   intent = getIntent();
                   account = (Account) intent.getSerializableExtra("account");
                   Intent intent1 = new Intent(QuizResults.this, StartActivity.class);
                   intent1.putExtra("account", account);
                   startActivity(intent1);
                   finish();
                }
            }
        });
    }

            @Override
            public void onBackPressed() {
                Intent intent = getIntent();
                account = (Account) intent.getSerializableExtra("account");
                AccountDB accountDB = new AccountDB(getApplicationContext());
                Account currentAccount = accountDB.find(account.getId());

                Intent intent1 = new Intent(QuizResults.this, StartActivity.class);
                intent1.putExtra("account", currentAccount);
                startActivity(intent1);

                //finish();
            }

    }