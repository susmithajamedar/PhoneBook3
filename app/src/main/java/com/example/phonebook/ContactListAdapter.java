package com.example.phonebook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;



public class ContactListAdapter extends ArrayAdapter<Contact> {

    private ArrayList<Contact> contacts;
    private Context context;
    private int resource;


    public ContactListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Contact> objects) {
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
        TextView tvfirstname = (TextView) convertView.findViewById(R.id.fnamerow);
        TextView tvAddress = (TextView) convertView.findViewById(R.id.addressrow);
        TextView tvlastname = (TextView) convertView.findViewById(R.id.lnamerow);
        TextView phone=(TextView) convertView.findViewById(R.id.phonenorow);
        TextView email =(TextView) convertView.findViewById(R.id.emailidrow);
        Button deleteBtn = (Button)convertView.findViewById(R.id.delbtn);
        Button updateBtn = (Button)convertView.findViewById(R.id.updatebtn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                Log.d("clicked button","deleted");
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage("Are you sure you want to delete contact?");
                builder1.setTitle("Delete Confirmation!");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                contacts.remove(row); //or some other task
                                notifyDataSetChanged();
                            }
                        });
                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder1.show();

            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                Log.d("click","update");
                Contact c1 = contacts.get(row);
                Intent i = new Intent(getContext(),Updatecontact.class);
                i.putExtra("selected", (Serializable) c1);
                context.startActivity(i);
                notifyDataSetChanged();
            }
        });


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

