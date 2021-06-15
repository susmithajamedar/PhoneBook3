package com.example.phonebook;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Contact.class}, version = 2, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase
{
    public abstract ContactDao contactDao();
    private static ContactDatabase INSTANCE;

    static ContactDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (ContactDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactDatabase.class, "contact").build();
                }
            }
        }
        return INSTANCE;
    }
}