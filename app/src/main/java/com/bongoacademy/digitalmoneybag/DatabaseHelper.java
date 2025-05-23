package com.bongoacademy.digitalmoneybag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "Digita_Moneybag", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table expense(id INTEGER primary key autoincrement,amount DUBLE,reason TEXT,time DOUBLE)");
        db.execSQL("create table income(id INTEGER primary key autoincrement,amount DUBLE,reason TEXT,time DOUBLE)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists expense");
        db.execSQL("drop table if exists income");

    }

    //========================================================
    public void addExpense(Double amount,String reason){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues conval = new ContentValues();
        conval.put("amount",amount);
        conval.put("reason",reason);
        conval.put("time",System.currentTimeMillis());
        db.insert("expense",null,conval);

    }

    //========================================================

    //========================================================
    public void addIncome(Double amount,String reason){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues conval = new ContentValues();
        conval.put("amount",amount);
        conval.put("reason",reason);
        conval.put("time",System.currentTimeMillis());
        db.insert("income",null,conval);

    }
    //========================================================

    //========================================================

    public double CalculatetotalExpense(){
        double totalExpense =0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor  = db.rawQuery("select * from expense",null);
        if (cursor!=null & cursor.getCount()>0){
            while (cursor.moveToNext()){
                double amount = cursor.getDouble(1);
                totalExpense = totalExpense+amount;
            }
        }

            return totalExpense;

    }
   //===================================================================


    //========================================================

    public double CalculatetotalIncome(){
        double totalincome =0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor  = db.rawQuery("select * from income",null);
        if (cursor!=null & cursor.getCount()>0){
            while (cursor.moveToNext()){
                double amount = cursor.getDouble(1);
                totalincome = totalincome+amount;
            }
        }

        return totalincome;

    }
    //===================================================================

    //===================================================================

    public Cursor getAllExpense(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from expense order by id desc",null);

        return cursor;
    }

    public Cursor getAllIncome(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from income order by id desc",null);

        return cursor;
    }

    //===================================================================

    public void deleteExpense(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from expense where id like "+id);
    }
    public void deleteIncome(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from income where id like "+id);
    }

}

