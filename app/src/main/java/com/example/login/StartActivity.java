package com.example.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.demo.entities.Account;
import android.demo.entities.database.AccountDB;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {


    private TextView textViewWelcome;
    private Button profile;
    private Account account;
    private String selectedTopicName = "";
    String EmailHolder;
    DrawerLayout drawerLayout;
    ImageView imageView;

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;

    AccountDB myDB;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activity);

        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");

        drawerLayout = findViewById(R.id.drawer_layout);
        textViewWelcome = findViewById(R.id.textViewWelcome);

        final Button startBtn = findViewById(R.id.startQuizBtn);
        final LinearLayout javaLayout = findViewById(R.id.javaLayout);
        final LinearLayout phpLayout = findViewById(R.id.phpLayout);
        final LinearLayout htmlLayout = findViewById(R.id.htmlLayout);
        final LinearLayout androidLayout = findViewById(R.id.androidLayout);

        javaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                selectedTopicName = "Mobile App";


                javaLayout.setBackgroundResource(R.drawable.round_back_white_stroke10);


                phpLayout.setBackgroundResource(R.drawable.round_back_white10);
                htmlLayout.setBackgroundResource(R.drawable.round_back_white10);
                androidLayout.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        phpLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                selectedTopicName = "Web App";


                phpLayout.setBackgroundResource(R.drawable.round_back_white_stroke10);

                javaLayout.setBackgroundResource(R.drawable.round_back_white10);
                htmlLayout.setBackgroundResource(R.drawable.round_back_white10);
                androidLayout.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        htmlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                selectedTopicName = "Algorithm";


                htmlLayout.setBackgroundResource(R.drawable.round_back_white_stroke10);


                javaLayout.setBackgroundResource(R.drawable.round_back_white10);
                phpLayout.setBackgroundResource(R.drawable.round_back_white10);
                androidLayout.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        androidLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                selectedTopicName = "Ai Programming";

                androidLayout.setBackgroundResource(R.drawable.round_back_white_stroke10);
                javaLayout.setBackgroundResource(R.drawable.round_back_white10);
                phpLayout.setBackgroundResource(R.drawable.round_back_white10);
                htmlLayout.setBackgroundResource(R.drawable.round_back_white10);
            }
        });


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (selectedTopicName.isEmpty()) {
                    Toast.makeText(StartActivity.this, "Please select topic first", Toast.LENGTH_SHORT).show();
                } else {

                    question();

                }
            }
        });
    }

    public void ClickMenu(View view)
    {
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {

        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view)
    {closeDrawer(drawerLayout);}

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view)
    {
        recreate();
    }

    public void ClickProfile(View view)
    {
        profile();
    }

    public void ClickScore(View view)
    {
        mark();
    }

    public void ClickLogout(View view)
    {
        logout();
    }


    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    public void profile() {
        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");

        Intent intent1 = new Intent(StartActivity.this,Profile.class);
        intent1.putExtra("account", account);
        startActivity(intent1);
    }

    public void question() {

        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");
        Intent intent1 = new Intent(StartActivity.this, Question.class);

        intent1.putExtra("selectedTopic", selectedTopicName);
        intent1.putExtra("account", account);
        startActivity(intent1);

        finish();
    }

    public void mark() {
        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");
        Intent intent1 = new Intent(StartActivity.this, ScoreActivity.class);

        intent1.putExtra("selectedTopic", selectedTopicName);
        intent1.putExtra("account", account);
        startActivity(intent1);
        finish();
    }

    public void logout()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure want to logout?");
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent1 = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(StartActivity.this,"You clicked over Cancel",Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

}