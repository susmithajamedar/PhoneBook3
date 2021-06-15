package com.example.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    public static ContactDatabase connectioncontactslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        connectioncontactslist = Room.databaseBuilder(getApplicationContext(), ContactDatabase.class, "contact").allowMainThreadQueries().fallbackToDestructiveMigration().build();

    }

    public void addContactPressed(View view) {

        Intent i = new Intent(getApplicationContext(),AddContact.class);
        startActivity(i);

    }

    public void viewContactsPressed(View view) {

        Intent i = new Intent(getApplicationContext(),ViewContacts.class);
        startActivity(i);
    }



    public void searchContactsPressed(View view) {
        Intent i = new Intent(getApplicationContext(),SearchContact.class);
        startActivity(i);
    }
}
