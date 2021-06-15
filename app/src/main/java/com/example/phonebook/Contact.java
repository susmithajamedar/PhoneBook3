package com.example.phonebook;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "Contact")
    public class Contact implements Serializable
    {
        @PrimaryKey(autoGenerate = true)
        @NonNull
        @ColumnInfo(name = "contactId")
        private int id;

        @ColumnInfo(name = "contactfirstName")
        private String contactfName;

        @ColumnInfo(name = "contactlastName")
        private String contactlName;

        @ColumnInfo(name = "contactPhone")
        private String contactPhone;

        @ColumnInfo(name = "contactEmail")
        private String contactEmail;

        @ColumnInfo(name = "contactAddress")
        private String contactAddress;

        //CONSTRUCTOR (Note: parameter names must match column names exactly)
        public Contact(String contactfName,String contactlName, String contactPhone, String contactEmail,String contactAddress)
        {
            this.id = id;
            this.contactfName = contactfName;
            this.contactlName = contactlName;
            this.contactPhone = contactPhone;
            this.contactEmail = contactEmail;
            this.contactAddress = contactAddress;
        }

        //GETTERS
        public int getId() {return this.id; }
        public String getContactfName() { return this.contactfName; }

        public String getContactlName() {
            return contactlName;
        }

        public void setContactlName(String contactlName) {
            this.contactlName = contactlName;
        }

        public String getContactAddress() {
            return contactAddress;
        }

        public void setContactAddress(String contactAddress) {
            this.contactAddress = contactAddress;
        }

        public String getContactPhone() { return this.contactPhone; }
        public String getContactEmail() { return this.contactEmail; }

        //SETTERS
        public void setId(int id) { this.id = id; }
        public void setContactfName(String fname) { this.contactfName = fname; }
        public void setContactPhone(String phone) { this.contactPhone = phone; }
        public void getContactEmail(String email) { this.contactEmail = email; }
    }


