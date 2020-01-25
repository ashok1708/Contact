package com.example.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;


public class DataBase extends SQLiteOpenHelper {


    public static final String DATABASE_NAME="Contacts";
    public static final String TABLE_NAME="CONTACT";
    public static final String ID="ID";
    public static final String FNAME="FNAME";
    public static final String MNAME="MNAME";
    public static final String LNAME="LNAME";
    public static final String PHONE="MOBILE";
    public static final String EMAIL="EMAIL";
    public static final String IMAGE="IMAGE";


    public DataBase(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, FNAME TEXT,MNAME TEXT, LNAME TEXT, MOBILE INTEGER(12), EMAIL VARCHAR(20),IMAGE TEXT )");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);


    }

    public boolean insertData(String fname,String mname, String lname,String mobile, String email,String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FNAME, fname);
        contentValues.put(MNAME, mname);
        contentValues.put(LNAME, lname);
        contentValues.put(PHONE, mobile);
        contentValues.put(EMAIL, email);
        contentValues.put(IMAGE, image);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }



    public Cursor searchQuery(String condition)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+ " where "+ PHONE+"="+condition+" order by "+FNAME+" asc", null);
        return res;
    }

    public List<Contact> getContactList()
    {
        List<Contact> contact_list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+" order by "+FNAME+" asc", null);

        if(res.moveToFirst()){
            do{
                Contact contact=new Contact();
                contact.setFname(res.getString(1));
                contact.setMname(res.getString(2));
                contact.setLname(res.getString(3));
                contact.setMobile(res.getString(4));
                contact.setEmail(res.getString(5));
                contact.setImage(res.getString(6));

                contact_list.add(contact);
            }while (res.moveToNext());
        }
        return contact_list;
    }


}
