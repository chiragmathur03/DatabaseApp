package com.example.dbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataBaseHandler extends  SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "newdb";
    private static final String TABLE_NAME = "registrations";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_DOB = "dob";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_STATE = "state";
    private static final String KEY_PH_NO = "phone_number";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_DOB + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_STATE + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addRecord(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME , record._name);
        values.put(KEY_PASSWORD , record._password);
        values.put(KEY_EMAIL , record._email);
        values.put(KEY_DOB , record._dob);
        values.put(KEY_STATE , record._state);
        values.put(KEY_PH_NO , record._phone_number);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    Record getRecord(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] {
                KEY_ID, KEY_NAME,KEY_PH_NO,KEY_PASSWORD,KEY_EMAIL,KEY_DOB,KEY_STATE}, KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null,null,null);

        if(cursor != null) {
            cursor.moveToFirst();
        }
        Record record = new Record(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6)
        );

        return record;
    }
    public ArrayList<Record> getAllRecords(String search) {
        ArrayList<Record> recordList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + KEY_NAME + " LIKE '%" + search + "%' OR "
                + KEY_PH_NO + " LIKE '%" + search + "%' OR "
                + KEY_STATE + " LIKE '%" + search + "%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Record record = new Record();
                record.setId(Integer.parseInt(cursor.getString(0)));
                record.setName(cursor.getString(1));
                record.setPhoneNumber(cursor.getString(2));
                record.setPassword(cursor.getString(3));
                record.setEmail(cursor.getString(4));
                record.setDob(cursor.getString(5));
                record.setState(cursor.getString(6));

                recordList.add(record);
            } while(cursor.moveToNext());
        }

        return recordList;
    }
    public int updateRecord(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, record.getName());
        values.put(KEY_PH_NO, record.getPhoneNumber());
        values.put(KEY_STATE, record.getState());
        values.put(KEY_PASSWORD, record.getPassword());
        values.put(KEY_EMAIL, record.getEmail());
        values.put(KEY_DOB, record.getDob());

        return db.update(TABLE_NAME, values, KEY_ID + "=?", new String[] { String.valueOf(record.getId())});
    }

    public void deleteRecord(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID+ " = ?", new String[] {String.valueOf(record.getId())});

        db.close();
    }
}
