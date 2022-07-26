package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.demo.entities.Account;
import android.demo.entities.database.AccountDB;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.demo.entities.database.AccountDB;

import com.google.android.material.textfield.TextInputLayout;

public class ForgotPass extends AppCompatActivity {

    private EditText username,password;
    private Button reset;
    private Account account;
    private AccountDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        username = findViewById(R.id.resetUsername);
        password = findViewById(R.id.password);
        reset = findViewById(R.id.reset);

        db = new AccountDB(this);



        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyFromSQLite();
            }
        });
    }

    private void verifyFromSQLite(){

        if (username.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill in your username", Toast.LENGTH_SHORT).show();
            return;
        }


        if (db.checkUser(username.getText().toString().trim())) {
            Intent accountsIntent = new Intent(this, ConfirmPassword.class);
            accountsIntent.putExtra("EMAIL", username.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
        } else {
            Toast.makeText(this, "Error! Valid username.", Toast.LENGTH_SHORT).show();
        }
    }

    private void emptyInputEditText(){
        username.setText("");
    }

    public void onRemember(View View) {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }


}