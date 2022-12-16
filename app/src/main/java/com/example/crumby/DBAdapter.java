package com.example.crumby;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;
import android.widget.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DBAdapter {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";
    public static final String KEY_TOP1 = "cheese";
    public static final String KEY_TOP2 = "pepperoni";
    public static final String KEY_TOP3 = "pineapple";
    public static final String KEY_SIZE = "size";
    public static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "MyDB";
    private static final String DATABASE_TABLE = "orders";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_CREATE =
            "create table orders(_id integer primary key autoincrement,"
                    + "name text not null,phone text not null,"
                    + "date text not null,time text not null,"
                    + "cheese text,pepperoni text,pineapple text,"
                    + "size text);";

    private final DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        DBHelper = new DatabaseHelper(ctx);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }//end method onCreate

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrade database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }//end method onUpgrade
    }


    //open the database
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //close the database
    public void close() {
        DBHelper.close();
    }

    //get latest row insert
    public Cursor getLastOrder() {
        return db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME, KEY_PHONE, KEY_DATE, KEY_TIME, KEY_TOP1, KEY_TOP2, KEY_TOP3, KEY_SIZE}, null, null, null, null, KEY_ROWID + " DESC", "1");
    }

    //get latest contact name
    public String getLastContactName() {
        Cursor c = getLastOrder();
        if (c.moveToFirst()) {
            return c.getString(1);
        } else {
            return "";
        }
    }

    //drop contact database and create new
    public void resetDB() {
        db.execSQL("DROP TABLE IF EXISTS orders");
        db.execSQL(DATABASE_CREATE);
    }

    //insert a contact into the database
    public long insertContact(String name, String phone, String date, String time, String cheese, String pepperoni, String pineapple, String size) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_PHONE, phone);
        initialValues.put(KEY_DATE, date);
        initialValues.put(KEY_TIME, time);
        initialValues.put(KEY_TOP1, cheese);
        initialValues.put(KEY_TOP2, pepperoni);
        initialValues.put(KEY_TOP3, pineapple);
        initialValues.put(KEY_SIZE, size);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //delete a particular contact
    public boolean deleteContact(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //retrieve a particular contact
    public Cursor getContact(long rowId) throws SQLException {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                                KEY_NAME, KEY_PHONE, KEY_DATE, KEY_TIME, KEY_TOP1, KEY_TOP2, KEY_TOP3, KEY_SIZE}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //get all contacts
    public Cursor getAllContacts()throws SQLException{
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                                KEY_NAME, KEY_PHONE, KEY_DATE, KEY_TIME, KEY_TOP1, KEY_TOP2, KEY_TOP3, KEY_SIZE}, null, null,
                        null, null, null, null);
        if (mCursor != null)
        {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //copydb
    public void CopyDB(InputStream inputStream,
                        OutputStream outputStream) throws IOException {
        //---copy 1K bytes at a time---
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer))>0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }

    //update a contact
    public boolean updateContact(long rowId, String name, String phone, String date, String time, String cheese, String pepperoni, String pineapple, String size) {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_PHONE, phone);
        args.put(KEY_DATE, date);
        args.put(KEY_TIME, time);
        args.put(KEY_TOP1, cheese);
        args.put(KEY_TOP2, pepperoni);
        args.put(KEY_TOP3, pineapple);
        args.put(KEY_SIZE, size);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //count all rowIds
    public int countRowIds() {
        Cursor mCursor = db.rawQuery("SELECT COUNT(*) FROM " + DATABASE_TABLE, null);
        mCursor.moveToFirst();
        int count = mCursor.getInt(0);
        mCursor.close();
        return count;
    }
}
