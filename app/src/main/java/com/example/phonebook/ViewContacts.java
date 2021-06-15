package com.example.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewContacts extends AppCompatActivity {


    ListView contactslistview;
    ContactListAdapter adapter;
    private List<Contact> allContacts;


    ArrayList<Contact> contacts = new ArrayList<Contact>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        contactslistview = (ListView) findViewById(R.id.lvcontacts);

        contactslistview.setLongClickable(true);


        allContacts = MainActivity.connectioncontactslist.contactDao().getAllContacts();

        if (allContacts.size() == 0) {


            Toast.makeText(this,"Added contact list at the moment",Toast.LENGTH_SHORT).show();
            //contactslistview.notify();

        } else {
            // add adapter

            contacts = (ArrayList<Contact>) allContacts;
            adapter = new ContactListAdapter(this, R.layout.contactlist_row, contacts);

            contactslistview.setAdapter(adapter);

            adapter.notifyDataSetChanged();

            // set click handler
            contactslistview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                               int pos, long id) {
                    Toast.makeText(getApplicationContext(), "long clicked", Toast.LENGTH_SHORT).show();

                    Log.d("long","long click");

                     Contact c = contacts.get(pos);
                     Intent i = new Intent(ViewContacts.this, MessageCall.class);
                     i.putExtra("selected",c);
                     startActivity(i);

                    return true;
                }
            });
        }
    }


    }

