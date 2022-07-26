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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity
{

    private EditText username, password, fullname, email ,phone;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        changeStatusBarColor();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);

        save = findViewById(R.id.register);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                save_onClick(v);

            }
        });
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,MainActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

    public void save_onClick(View v) {
        try{
            AccountDB accountDB = new AccountDB(getApplicationContext());
            Account account = new Account();
            account.setEmail(email.getText().toString());
            account.setFullname(fullname.getText().toString());
            account.setPassword(password.getText().toString());
            account.setUsername(username.getText().toString());
            Account temp = accountDB.checkUsername(username.getText().toString());

            if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty() || fullname.getText().toString().isEmpty() || email.getText().toString().isEmpty()){
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if(temp==null) {
                if(accountDB.create(account)) {
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    Toast.makeText(this, "Successfully registered", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, "Cannot create!", Toast.LENGTH_SHORT).show();
                    return;
                }
                }
            else{
                Toast.makeText(this, "Username exist!", Toast.LENGTH_SHORT).show();
                return;
            }



        }catch (Exception e)
        {   AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("ERROR");
            builder.setMessage(e.getMessage());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();

        }

    }

    public void onRemember(View View) {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
}