package com.example.phonebook;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;



public class CallMessage extends Fragment {

   Contact contact;

    private Object ContextCompat;


    public CallMessage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_call_message, container, false);
        Bundle bundle = this.getArguments();
        Log.d("enetered frag","enter");

            Object o = bundle.getSerializable("selected");
            contact = (Contact)o;

            Log.d("object data","email"+contact.getContactEmail());


        EditText emailadd =(EditText)v.findViewById(R.id.emailto);

        emailadd.setText(contact.getContactEmail());


        ImageView callbutton=(ImageView)v.findViewById(R.id.callbutton);
        callbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String number = contact.getContactPhone();
                String formattedPhoneNumber = "tel:" + number;

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + formattedPhoneNumber));

                if (Build.VERSION.SDK_INT > 23) {
                    startActivity(intent);
                } else {

                    if (ActivityCompat.checkSelfPermission(getContext(),
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getContext(), "Permission Not Granted ", Toast.LENGTH_SHORT).show();
                    } else {
                        final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                        ActivityCompat.requestPermissions(getActivity(), PERMISSIONS_STORAGE, 9);
                        startActivity(intent);
                    }
                }
                //Tell android that you want to open the default DIAL screen
//                Intent i = new Intent(Intent.ACTION_CALL);
//                // sending the phone number to the dial screen
//                i.setData(Uri.parse(formattedPhoneNumber));
//                // 3. run the intent
//                if (i.resolveActivity(getActivity().getPackageManager()) != null) {
//                    startActivity(i);
//                }
//                else {
//                    Log.d("", "ERROR: Cannot find app that matches ACTION_CALL intent");
//                }
            }
        });

        final EditText etMessage = (EditText) v.findViewById(R.id.etMessage);
        ImageView msg = (ImageView) v.findViewById(R.id.messagebutton);
        msg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 final int PERMISSION_REQUEST_CODE = 1;

                String number = contact.getContactPhone();
                // get the message
                // do some SMS configuration with your cell phone carrier
                //  Configure the SMSC (Short Message Service Center)
                String message = etMessage.getText().toString();

                if (message.equals("")) {
                    Toast.makeText(getContext(), "Message cannot be empty!", Toast.LENGTH_SHORT).show();
                } else {


                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

                        if (ActivityCompat.checkSelfPermission(getContext(),
                                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(getContext(), "Permission Not Granted ", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            String[] permissions = {Manifest.permission.SEND_SMS};

                            requestPermissions(permissions, PERMISSION_REQUEST_CODE);
                            String scAddress = null;
                   // create some required intent variables
                    PendingIntent sentIntent = null;
                    PendingIntent deliveryIntent = null;
                    // Send the SMS using SMSManager (built in Android class)
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(number, scAddress, message, sentIntent, deliveryIntent);
                    Toast.makeText(getContext(), "SMS sent", Toast.LENGTH_SHORT).show();
                    etMessage.setText("");

                        }
                    }
//
                }
            }
        });

        ImageView email=(ImageView)v.findViewById(R.id.email);
        final EditText editTextTo=(EditText)v.findViewById(R.id.emailto);
        final EditText editTextSubject=(EditText)v.findViewById(R.id.editText2);
        final EditText editTextMessage=(EditText)v.findViewById(R.id.editText3);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String to=editTextTo.getText().toString();
                String subject=editTextSubject.getText().toString();
                String message=editTextMessage.getText().toString();

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, message);

                try {
                    emailIntent.setType("message/rfc822");
                    startActivity(Intent.createChooser(emailIntent, "Send mail via"));
                    Toast.makeText(getContext(), "Email sent", Toast.LENGTH_SHORT).show();
                    editTextMessage.setText("");
                    editTextSubject.setText("");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return v;
    }

}




