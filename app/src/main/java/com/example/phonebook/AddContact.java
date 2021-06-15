package com.example.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddContact extends AppCompatActivity {
    EditText fname;
    EditText lname ;
    EditText address ;
    EditText email;
    EditText phone ;

    String fnamedb ;
    String lnamedb ;
    String addressdb;
    String emaildb ;
    String phonedb ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);


    }


    public void addContactPressed(View view) {


        fname = (EditText) findViewById(R.id.etfirstname);
        lname = (EditText) findViewById(R.id.etlastname);
        address = (EditText) findViewById(R.id.etaddress);
        email = (EditText) findViewById(R.id.etemailid);
        phone = (EditText) findViewById(R.id.etphonenum);

        fnamedb = fname.getText().toString();
        lnamedb = lname.getText().toString();
        addressdb = address.getText().toString();
        phonedb = (phone.getText().toString());
        emaildb = email.getText().toString();

        try {

            if (lnamedb.equals("") || fnamedb.equals("") || addressdb.equals("") || emaildb.equals("") || phonedb.equals("")) {
                Toast.makeText(this, "Please fill all details!", Toast.LENGTH_SHORT).show();
            } else {

                Contact contact = new Contact(fnamedb,lnamedb,phonedb,emaildb,addressdb);
                MainActivity.connectioncontactslist.contactDao().insertContact(contact);
                Toast.makeText(this, "Details added", Toast.LENGTH_SHORT).show();
                fname.setText("");
                lname.setText("");
                address.setText("");
                email.setText("");
                phone.setText("");
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);

            }
        }
        catch(Exception e){
            Toast.makeText(this,"Please provide all details correctly",Toast.LENGTH_LONG).show();
        }

    }


}
