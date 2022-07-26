package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.demo.entities.Account;
import android.demo.entities.database.AccountDB;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Getusername, Getpassword;
    private Button login,SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        setContentView(R.layout.activity_login);

        Getusername = findViewById(R.id.username);
        Getpassword = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_onClick(v);

            }
        });

    }

    public void login_onClick(View v) {
        AccountDB accountDB = new AccountDB(getApplicationContext());
        String username = Getusername.getText().toString();
        String password = Getpassword.getText().toString();
        Account account = accountDB.login(username, password);

        if(account == null)
        { Toast.makeText(this, "Invalid account!", Toast.LENGTH_SHORT).show();
            return;

        }else{
            Intent intent = new Intent(MainActivity.this,StartActivity.class);
            intent.putExtra("account",account);
            startActivity(intent);
        }

    }

    public void onForgotClick(View view)
    {
        startActivity(new Intent(this, ForgotPass.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }

    public void onLoginClick(View View) {
        startActivity(new Intent(this, SignUpActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
}