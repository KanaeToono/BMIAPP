package com.example.conga.bmi.constants;



public class Constant {
    private static String TAG = Constant.class.getSimpleName();

    public static final int DB_VERSION = 2;
    public static final String DB_NAME = "notebook.db";
    public static final String TABLE_NAME = "note";
    public static final String KEY_ID = "_id";
    public static final String KEY_SUBJECT = "_subject";
    public static final String KEY_CONTENT = "_content";
    public static final String KEY_DATE = "_date";
   // public static final String KEY_IMAGE= "_image";


    public  static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_SUBJECT + " TEXT(100)," +
            KEY_CONTENT + " TEXT(100)," + KEY_DATE +" TEXT(100));";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
