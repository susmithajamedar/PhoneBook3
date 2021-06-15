package com.example.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.List;

public class Updatecontact extends AppCompatActivity  implements Serializable {

    EditText fname;
    EditText lname;
    EditText phoneno;
    EditText addr;
    EditText email;
    String emailupdate,fnameupdate,lnameupdate,addressupdate,phonenoupdate;
    List<Contact> contacts;
    Contact contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatecontact);
        Object o=getIntent().getSerializableExtra("selected");
        contact=(Contact) o;

         fname =  (EditText) findViewById(R.id.etfirstnameupdate);
         lname =  (EditText) findViewById(R.id.etlastnameupdate);
         email =  (EditText) findViewById(R.id.etemailidupdate);
         addr =  (EditText) findViewById(R.id.etaddressupdate);
         phoneno =  (EditText) findViewById(R.id.etphonenumupdate);

        fname.setText(contact.getContactfName());
        lname.setText(contact.getContactlName());
        email.setText(contact.getContactEmail());
        phoneno.setText(contact.getContactPhone());
        addr.setText(contact.getContactAddress());





    }

    public void UpdateContactPressed(View view) {

        try {
            emailupdate = email.getText().toString();
            fnameupdate = fname.getText().toString();
            lnameupdate = lname.getText().toString();
            addressupdate = addr.getText().toString();
            phonenoupdate = phoneno.getText().toString();

            if (lnameupdate.equals("") || fnameupdate.equals("") || addressupdate.equals("") || emailupdate.equals("") || phonenoupdate.equals("")) {
                Toast.makeText(this, "Please fill all details!", Toast.LENGTH_SHORT).show();
            } else {

                Contact contact1 = new Contact(fnameupdate,lnameupdate,phonenoupdate,emailupdate,addressupdate);
                MainActivity.connectioncontactslist.contactDao().updateContact(contact.getContactEmail(),phonenoupdate,fnameupdate,lnameupdate,addressupdate,emailupdate);
                Toast.makeText(this, "Details added", Toast.LENGTH_SHORT).show();

                fname.setText("");
                lname.setText("");
                addr.setText("");
                email.setText("");
                phoneno.setText("");
                Intent i = new Intent(this,ViewContacts.class);
                startActivity(i);

            }
        }
        catch(Exception e){
            Toast.makeText(this,"Please provide all details correctly",Toast.LENGTH_LONG).show();
        }




    }


}
