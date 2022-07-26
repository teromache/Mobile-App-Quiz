package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.demo.entities.database.AccountDB;
import android.widget.Toast;

public class ConfirmPassword extends AppCompatActivity {

 private EditText password,confirmpass;
 private AccountDB db;
 private Button reset;

 String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);


        password = findViewById(R.id.password);
        confirmpass = findViewById(R.id.confirmpassword);
        reset = findViewById(R.id.reset);

        db = new AccountDB(this);

        Intent intent = getIntent();
        email = intent.getStringExtra("EMAIL");

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePassword();
            }
        });
    }

    private void updatePassword() {

        String value1 = password.getText().toString().trim();
        String value2 = confirmpass.getText().toString().trim();

        if (value1.isEmpty() && value2.isEmpty()){
            Toast.makeText(this, "Fill all fields ", Toast.LENGTH_LONG).show();
            return;
        }

        if (!value1.contentEquals(value2)){
            Toast.makeText(this, "Password doesn't match", Toast.LENGTH_LONG).show();
            return;
        }

        if (!db.checkUser(email)) {

            Toast.makeText(this, "Email does not exist ", Toast.LENGTH_LONG).show();
            return;

        } else {
            db.updatePassword(email, value1);

            Toast.makeText(this, "Password reset successfully", Toast.LENGTH_SHORT).show();
            emptyInputEditText();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    private void emptyInputEditText()
    {
        password.setText("");
        confirmpass.setText("");
    }

    public void onRemember(View View) {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }

}