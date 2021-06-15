package com.example.phonebook;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao
{
    @Insert
    void insertContact(Contact contact);

    @Query("SELECT * FROM contact WHERE contactfirstName LIKE ('%'||:fname||'%') ORDER BY contactfirstName")
    List<Contact> findfName(String fname);

    @Query("SELECT * FROM contact WHERE contactlastName LIKE ('%'||:lname||'%') ORDER BY contactlastName")
    List<Contact> findlName(String lname);

    @Query("SELECT * FROM contact WHERE contactPhone LIKE ('%'||:phone||'%') ORDER BY contactPhone")
    List<Contact> findPhone(String phone);

    @Query("SELECT * FROM contact WHERE contactEmail LIKE ('%'||:email||'%') ORDER BY contactEmail")
    List<Contact> findEmail(String email);

    @Query("SELECT * FROM contact WHERE contactAddress LIKE ('%'||:address||'%') ORDER BY contactfirstName")
    List<Contact> findAddress(String address);


    @Query("DELETE FROM contact WHERE contactPhone = :email")
    void deleteContact(String email);


    @Query(("UPDATE CONTACT SET contactPhone =(contactPhone || ',' || :phone),contactEmail = (contactEmail || ',' || :emailupdate),contactfirstName = :fname,contactlastName = :lname ,contactAddress = :address WHERE contactEmail = :email"))
    void updateContact(String email,String phone,String fname,String lname,String address,String emailupdate);

    @Query("SELECT * FROM contact ORDER BY contactfirstName")
    List<Contact> getAllContacts();
}