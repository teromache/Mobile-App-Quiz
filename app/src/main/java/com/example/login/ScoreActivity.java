package com.example.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.demo.entities.Account;
import android.demo.entities.database.AccountDB;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton remove,home;
    ImageView empty_imageview;
    TextView no_data, date;
    private Account account;
    String count = "";

    AccountDB myDB;
    ArrayList<String> book_id;
    ArrayList<String> book_title;
    ArrayList<String> book_author;
    ArrayList<String> book_pages;
    ArrayList<String> currenDate;
    CustomAdapter customAdapter;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        recyclerView = findViewById(R.id.recyclerView);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        remove = findViewById(R.id.remove);
        home = findViewById(R.id.home);

        loadData();

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                confirmDialog();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                account = (Account) intent.getSerializableExtra("account");
                Intent intent1 = new Intent(ScoreActivity.this, StartActivity.class);
                intent1.putExtra("account", account);
                startActivity(intent1);
                finish();
            }
        });

        myDB = new AccountDB(ScoreActivity.this);
        book_id = new ArrayList<String>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();
        currenDate = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(ScoreActivity.this,this, book_id, book_title, book_author, book_pages, currenDate);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ScoreActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    private void loadData()
    {
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");
    }

    void storeDataInArrays()
    {
        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");
        int id =   account.getId();
        Cursor cursor = myDB.readAllData(id);


        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                book_id.add(count);
                book_title.add(cursor.getString(3));
                book_author.add(cursor.getString(4));
                book_pages.add(cursor.getString(6));
                currenDate.add(cursor.getString(5));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void confirmDialog(){
        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");
        int id =   account.getId();
        String new_id = String.valueOf(id);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete");
        builder.setMessage("Proceed delete all data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AccountDB myDB = new AccountDB(ScoreActivity.this);
                myDB.deleteData(new_id);
                recreate();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

}
