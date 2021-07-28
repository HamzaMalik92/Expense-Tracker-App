package com.hamzamalik.expensetracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.hamzamalik.expensetracker.recyclerView.expense_record;

import java.util.ArrayList;

public class dbHandler extends SQLiteOpenHelper {

    public dbHandler(@Nullable Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String db_Query = "Create table " + Params.TABLE_NAME + "("
                + Params.KEY_ID + " Integer Primary Key ," + Params.KEY_CATEGORY + " Text , " +
                Params.KEY_DATE + " TEXT ," + Params.KEY_SPENDING + " Text );";
        db.execSQL(db_Query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addRecord(expense_record er) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_ID, er.getID());
        values.put(Params.KEY_CATEGORY, er.getCategory());
        values.put(Params.KEY_DATE, er.getDate());
        values.put(Params.KEY_SPENDING, er.getSpending());

        db.insert(Params.TABLE_NAME, null, values);
    }

    public ArrayList<expense_record> fetchAllRecords() {
        ArrayList<expense_record> record_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String db_Query = "Select * from " + Params.TABLE_NAME;

        Cursor cursor = db.rawQuery(db_Query, null);

        // loop through row
        if (cursor.moveToFirst()) {
            do {
                expense_record record = new expense_record();
                record.setID(Integer.parseInt(cursor.getString(0)));
                record.setCategory(cursor.getString(1));
                record.setDate(cursor.getString(2));
                record.setSpending(Integer.parseInt(cursor.getString(3)));

                record_list.add(record);
            } while (cursor.moveToNext());
        }

        return record_list;
    }

    public void deleteRecordByID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] arg = {id + ""};
        db.delete(Params.TABLE_NAME, Params.KEY_ID + "= ?", arg);
    }

    public int getLastID() {
        SQLiteDatabase db = this.getReadableDatabase();
        int lastID = -1;
        String db_Query = "Select id from " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(db_Query, null);
        if (cursor.moveToLast()) {
            lastID = Integer.parseInt(cursor.getString(0));
        }
        return lastID;
    }

    public int getCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String db_Query = "Select id from " +
                Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(db_Query, null);
        return cursor.getCount();
    }
}
