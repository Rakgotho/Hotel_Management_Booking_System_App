package com.example.hotelmanagementssystemapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Hotel.db";
    public static final String TABLE_NAME = "Hotel_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "BEDS";
    public static final String COL_5 = "USERNAME";
    public static final String COL_6 = "PASSWORD";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY ,NAME TEXT, SURNAME TEXT,BEDS INTEGER, USERNAME TEXT, PASSWORD TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Id, String Name, String Surname, String Beds) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, Id);
        contentValues.put(COL_2, Name);
        contentValues.put(COL_3, Surname);
        contentValues.put(COL_4, Beds);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public long addUser(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("USERNAME", username);
        content.put("PASSWORD", password);
        long result = db.insert(TABLE_NAME, null, content);
        db.close();
        return result;


    }
    /*public boolean checkUser(String username, String password)
    {
        String[] columns = null;
        SQLiteDatabase db = getReadableDatabase();
        String select = COL_5 + "=?" + "and" + COL_6 + "=?";
        String[] selectArg = { username, password};
        Cursor cursor = db.query(TABLE_NAME, columns, select, selectArg, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return true;
        else
            return false;


    }*/

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+ TABLE_NAME,null);
        return res;
    }
    public boolean updateData(String ID,String NAME,String SURNAME,String Beds )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, ID);
        contentValues.put(COL_2, NAME);
        contentValues.put(COL_3, SURNAME);
        contentValues.put(COL_4, Beds);
        db.update(TABLE_NAME,contentValues,"ID=?",new String[] {ID});
        return true;
    }
    public Integer deleteData(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[] {ID});

    }


}
