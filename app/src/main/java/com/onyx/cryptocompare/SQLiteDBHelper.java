package com.onyx.cryptocompare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by onyekaanene on 06/11/2017.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "application_database";
    public static final String CURRENCIES_TABLE_NAME = "person";
    public static final String CURRENCIES_COLUMN_ID = "_id";
    public static final String CURRENCIES_COLUMN_CODE = "code";
    public static final String CURRENCIES_COLUMN_NAME = "name";

    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + CURRENCIES_TABLE_NAME + " (" +
                CURRENCIES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CURRENCIES_COLUMN_CODE + " TEXT, " +
                CURRENCIES_COLUMN_NAME + " TEXT" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CURRENCIES_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
