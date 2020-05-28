package com.example.modelpaper2020.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "prof.db";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ENTRIES_USERS =
                "CREATE TABLE " + UserProfile.Users.TABLE_USERS + " (" +
                        UserProfile.Users._ID + " INTEGER PRIMARY KEY," +
                        UserProfile.Users.COLOUMN_NAME_USERNAME+ " TEXT," +
                        UserProfile.Users.COLOUMN_NAME_PASSWORD+ " TEXT," +
                        UserProfile.Users.COLOUMN_NAME_DOB + " TEXT," +
                        UserProfile.Users.COLOUMN_NAME_GENDER+ " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES_USERS);

    }


    public boolean addInfo(String username,String pw,  String dob , String gender){
        //Gets data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        //Create new map of values
        ContentValues cv = new ContentValues();
        cv.put(UserProfile.Users.COLOUMN_NAME_USERNAME,username);
        cv.put(UserProfile.Users.COLOUMN_NAME_PASSWORD,pw);
        cv.put(UserProfile.Users.COLOUMN_NAME_DOB,dob);
        cv.put(UserProfile.Users.COLOUMN_NAME_GENDER,gender);

        long newRowId;
        newRowId = db.insert(UserProfile.Users.TABLE_USERS,null,cv);
        if(newRowId == -1){
            return false;
        }
        else{
            return true;
        }
    }


    public boolean updateInfo(String name, String dob, String pw, String gender){
        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.COLOUMN_NAME_USERNAME, name);
        values.put(UserProfile.Users.COLOUMN_NAME_PASSWORD, pw);
        values.put(UserProfile.Users.COLOUMN_NAME_DOB, dob);
        values.put(UserProfile.Users.COLOUMN_NAME_GENDER, gender);


        String selection = UserProfile.Users.COLOUMN_NAME_USERNAME + " LIKE ?";
        String[] selectionArgs = {"userName"};
       long updateid =  db.update(UserProfile.Users.TABLE_USERS, values, selection, selectionArgs);

        if(updateid == -1)
        {
            return false;
        }
        else
            return true;

    }


    public List readAllInfo() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                UserProfile.Users._ID,
                UserProfile.Users.COLOUMN_NAME_USERNAME,
                UserProfile.Users.COLOUMN_NAME_PASSWORD,
                UserProfile.Users.COLOUMN_NAME_DOB,
                UserProfile.Users.COLOUMN_NAME_GENDER


        };

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_USERS, // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        List usernames = new ArrayList<>();
        List passwords = new ArrayList<>();
        List dobs = new ArrayList<>();
        List genders = new ArrayList<>();



        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLOUMN_NAME_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLOUMN_NAME_PASSWORD));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLOUMN_NAME_DOB));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLOUMN_NAME_GENDER));


            usernames.add(name);
            passwords.add(password);
            dobs.add(dob);
            genders.add(gender);
        }
        cursor.close();
        return usernames;

    }


    public boolean readAllInfo(String userName){
        SQLiteDatabase db = getReadableDatabase();
        boolean value = false;
        String[] projection = {
                UserProfile.Users._ID,
                UserProfile.Users.COLOUMN_NAME_USERNAME,
                UserProfile.Users.COLOUMN_NAME_PASSWORD,
                UserProfile.Users.COLOUMN_NAME_DOB,
                UserProfile.Users.COLOUMN_NAME_GENDER,
        };

        String selection = UserProfile.Users.COLOUMN_NAME_USERNAME + " LIKE ?";
        String[] selectionArgs= {userName};

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_USERS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLOUMN_NAME_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLOUMN_NAME_PASSWORD));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLOUMN_NAME_DOB));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLOUMN_NAME_GENDER));


            value =  userName.equals(name);
        }
        cursor.close();
        return value;
    }

    public int deleteInfo(String username) {
        SQLiteDatabase db = getReadableDatabase();


        String selection = UserProfile.Users.COLOUMN_NAME_USERNAME + " LIKE ?";
        String[] selectionArgs = {username};
       return db.delete(UserProfile.Users.TABLE_USERS, selection, selectionArgs);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
