package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.demo.entities.Account;
import android.demo.entities.database.AccountDB;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.drawerlayout.widget.DrawerLayout;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {

    private EditText username,password,email,phone;
    private TextView fullname;
    private Button save;
    private Account account;
    DrawerLayout drawerLayout;
    private String selectedTopicName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        username = findViewById(R.id.editName);
        password = findViewById(R.id.editPassword);
        fullname = findViewById(R.id.editFullname);
        email = findViewById(R.id.editEmail);

        loadData();

        save = findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                save_onClick(v);
            }
       });


    }

    private void loadData()
    {
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");
        username.setText(account.getUsername());
        password.setText(account.getPassword());
        email.setText(account.getEmail());
        fullname.setText(account.getFullname());
    }

    public void save_onClick(View view)
    {
        try {
            AccountDB accountDB = new AccountDB(getApplicationContext());
            Account currentAccount = accountDB.find(account.getId());
            String newUsername = username.getText().toString();
            Account temp = accountDB.checkUsername(newUsername);
            if(!newUsername.equalsIgnoreCase(currentAccount.getUsername()) && temp !=null) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("ERROR");
                builder.setMessage("Cannot create!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return;

            }


            currentAccount.setUsername(username.getText().toString());
            currentAccount.setFullname(fullname.getText().toString());
            currentAccount.setEmail(email.getText().toString());
            String pass = password.getText().toString();

            if(!pass.isEmpty())
            {
                currentAccount.setPassword(password.getText().toString());
            }

            if(accountDB.update(currentAccount))
            {
                Intent intent = new Intent(Profile.this, StartActivity.class);
                Toast.makeText(this,"Update successfull", Toast.LENGTH_SHORT).show();
                intent.putExtra("account", currentAccount);
                startActivity(intent);

            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("ERROR");
                builder.setMessage("Failed");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }



        }catch (Exception e)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
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

}

