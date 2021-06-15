package com.example.phonebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class SearchContactListAdapter extends ArrayAdapter<Contact> {

    private ArrayList<Contact> contacts;
    private Context context;
    private int resource;


    public SearchContactListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Contact> objects) {
        super(context, resource, objects);
        contacts = objects;
        this.context=context;
        this.resource=resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Contact contact = contacts.get(position);

        String fname = contact.getContactfName();
        String address = contact.getContactAddress();
        String lname  = contact.getContactlName();
        String emailid =contact.getContactEmail();
        String phoneno = contact.getContactPhone();
        final int row= position;
        //Handle buttons and add onClickListeners


        //Create an Layout inflater;
        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource,parent,false);


        //fetch text views
        TextView tvfirstname = (TextView) convertView.findViewById(R.id.fnamerowsearch);
        TextView tvAddress = (TextView) convertView.findViewById(R.id.addressrowsearch);
        TextView tvlastname = (TextView) convertView.findViewById(R.id.lnamerowsearch);
        TextView phone=(TextView) convertView.findViewById(R.id.phonenorowsearch);
        TextView email =(TextView) convertView.findViewById(R.id.emailidrowsearch);




        //set text for each text views
        tvfirstname.setText("First Name: "+fname);
        tvAddress.setText("Address: "+address);
        tvlastname.setText("Last Name: "+lname);
        phone.setText("Phone number: "+phoneno);
        email.setText("Email Id : "+emailid);

        ViewContacts v=new ViewContacts();
//
        return convertView;
    }
}

