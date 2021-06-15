package com.example.phonebook;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MessageCall extends AppCompatActivity {

    Contact contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_call);
        Object o=getIntent().getSerializableExtra("selected");
        contact=(Contact) o;
        Bundle b = new Bundle();
        Log.d("contact data ","email"+contact.getContactEmail());
        b.putSerializable("selected",contact);
        CallMessage fragment = new CallMessage();
        fragment.setArguments(b);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout, fragment, "selected").commit();


    }
}
