package com.example.phonebook;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchContact extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] items = new String[]{"First Name", "Last Name", "EmailId","Phone Number", "Address"};
    ListView lvsearch;
    ArrayList<Contact> contactArrayList = new ArrayList<>();
    List<Contact> contacts;
    SearchContactListAdapter searchadapter;
    Button search;
    String selected;
    int selectedindex;
    EditText searchvalue;
    TextView errortext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);

        Spinner dropdown = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        lvsearch = (ListView) findViewById(R.id.lvsearchcontact);
        search = (Button) findViewById(R.id.search);
        searchvalue =(EditText) findViewById(R.id.searchvalue);
        errortext = (TextView) findViewById(R.id.errortext);
        dropdown.setOnItemSelectedListener(this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchdata(selectedindex);
            }
        });

    }


    private void searchdata(int selectedindex) {
        Log.d("selected", "firstname" + selectedindex);
        selected = searchvalue.getText().toString();

        if (selected.equals("")) {

            Toast.makeText(getApplicationContext(), "Please enter data to search for", Toast.LENGTH_LONG).show();

        } else {


            switch (selectedindex) {
                case 0:
                    List<Contact> contacts = MainActivity.connectioncontactslist.contactDao().findfName(selected.toLowerCase());
                    contactArrayList = (ArrayList<Contact>) contacts;
                    searchadapter = new SearchContactListAdapter(getApplicationContext(), R.layout.searchlist_row, contactArrayList);

                    if(contactArrayList.size() == 0){
                       errortext.setText("There are no matching results");

                        lvsearch.setAdapter(searchadapter);
                        searchvalue.setText("");
                    }else{
                        searchadapter = new SearchContactListAdapter(getApplicationContext(), R.layout.searchlist_row, contactArrayList);

                        lvsearch.setAdapter(searchadapter);
                        searchvalue.setText("");
                        errortext.setText("");

                        break;
                    }


                case 1:
                    contacts = MainActivity.connectioncontactslist.contactDao().findlName(selected.toLowerCase());
                    contactArrayList = (ArrayList<Contact>) contacts;
                    searchadapter = new SearchContactListAdapter(getApplicationContext(), R.layout.searchlist_row, contactArrayList);

                    if(contactArrayList.size() == 0){
                        errortext.setText("There are no matching results");
                        lvsearch.setAdapter(searchadapter);
                        searchvalue.setText("");
                        break;
                    }else{

                        lvsearch.setAdapter(searchadapter);
                        searchvalue.setText("");
                        errortext.setText("");
                        break;
                    }
                case 2:
                    contacts = MainActivity.connectioncontactslist.contactDao().findEmail(selected.toLowerCase());
                    contactArrayList = (ArrayList<Contact>) contacts;
                    searchadapter = new SearchContactListAdapter(getApplicationContext(), R.layout.searchlist_row, contactArrayList);

                    if(contactArrayList.size() == 0){
                        errortext.setText("There are no matching results");
                        lvsearch.setAdapter(searchadapter);
                        searchvalue.setText("");
                    }else{
                        searchadapter = new SearchContactListAdapter(getApplicationContext(), R.layout.searchlist_row, contactArrayList);

                        lvsearch.setAdapter(searchadapter);
                        searchvalue.setText("");
                        errortext.setText("");

                    }
                    break;
                case 3:
                    contacts = MainActivity.connectioncontactslist.contactDao().findPhone(selected.toLowerCase());
                    contactArrayList = (ArrayList<Contact>) contacts;
                    searchadapter = new SearchContactListAdapter(getApplicationContext(), R.layout.searchlist_row, contactArrayList);


                    if(contactArrayList.size() == 0){
                        errortext.setText("There are no matching results");
                        lvsearch.setAdapter(searchadapter);
                        searchvalue.setText("");
                    }else{

                        lvsearch.setAdapter(searchadapter);
                        searchvalue.setText("");
                        errortext.setText("");


                    }
                    break;
                case 4:
                    contacts = MainActivity.connectioncontactslist.contactDao().findAddress(selected.toLowerCase());
                    contactArrayList = (ArrayList<Contact>) contacts;
                    searchadapter = new SearchContactListAdapter(getApplicationContext(), R.layout.searchlist_row, contactArrayList);


                    if(contactArrayList.size() == 0){
                        errortext.setText("There are no matching results");
                        lvsearch.setAdapter(searchadapter);
                        searchvalue.setText("");
                    }else{

                        lvsearch.setAdapter(searchadapter);
                        searchvalue.setText("");
                        errortext.setText("");


                    }
                    break;
                default:
                    errortext.setText("Please enter valid search data");

            }
        }
    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selected=items[i];
        selectedindex= i;
        switch (i) {
            case 0:
                Log.d("selected", "firstname");

                break;
            case 1:
                Log.d("selected", "last");

                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}