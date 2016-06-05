package com.example.conga.bmi.databases;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.conga.bmi.constants.Constant;
import com.example.conga.bmi.interfaces.ICRUDNotebook;
import com.example.conga.bmi.models.NoteBook;
import com.example.conga.bmi.utils.DbBitmapUtility;

import java.util.ArrayList;

public class NoteBookDatabaseHelper extends SQLiteOpenHelper implements ICRUDNotebook {
    private static String TAG = NoteBookDatabaseHelper.class.getSimpleName();
    private DbBitmapUtility mDbBitmapUtility;
    public NoteBookDatabaseHelper(Context context) {
        super(context, Constant.DB_NAME, null, Constant.DB_VERSION);
        mDbBitmapUtility = new DbBitmapUtility();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(Constant.CREATE_TABLE);
            Log.d(TAG, " create table successful!");
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, " can not create table successful!");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try{
            sqLiteDatabase.execSQL(Constant.DROP_TABLE);
            onCreate(sqLiteDatabase);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void addNewNoteBook(NoteBook noteBook) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constant.KEY_SUBJECT , noteBook.getSubject());
            contentValues.put(Constant.KEY_CONTENT, noteBook.getContent());
//            contentValues.put(Constant.KEY_IMAGE, noteBook.getImageArrays());
            contentValues.put(Constant.KEY_DATE , noteBook.getDate());
            sqLiteDatabase.insert(Constant.TABLE_NAME, null, contentValues);
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<NoteBook> getAllNoteBook() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ArrayList<NoteBook> notesList = new ArrayList<NoteBook>();
        try {
            String QUERY = " SELECT * FROM " + Constant.TABLE_NAME;
            Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
            if (cursor.moveToFirst() && cursor.getCount() >= 1) {
                do {
                    NoteBook noteBook = new NoteBook();
                    noteBook.setId(cursor.getInt(0));
                    noteBook.setSubject(cursor.getString(1));
                    noteBook.setContent(cursor.getString(2));
//                    noteBook.setImageArrays(cursor.getBlob(3));
                    noteBook.setDate((cursor.getString(3)));

                    notesList.add(noteBook);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR LOAD TASK" + e + "");
        }
        // mDatabase.close();
        return notesList;
    }

    @Override
    public void editNotebook(NoteBook noteBook) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
          //  ContentValues values = new ContentValues();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constant.KEY_SUBJECT , noteBook.getSubject());
            contentValues.put(Constant.KEY_CONTENT, noteBook.getContent());
//            contentValues.put(Constant.KEY_IMAGE, noteBook.getImageArrays());
            contentValues.put(Constant.KEY_DATE , noteBook.getDate());

            if (sqLiteDatabase.update(Constant.TABLE_NAME, contentValues, Constant.KEY_ID
                    + " = " + noteBook.getId(), null) > 0) {
                Log.d(TAG, "CAN NOT UPDATE");
            } else {
                Log.d(TAG, "update success");
            }
            //     mDatabase.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteNotebook(int id_note) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            sqLiteDatabase.delete(Constant.TABLE_NAME, Constant.KEY_ID
                    + " =" + id_note, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public NoteBookDatabaseHelper open() throws SQLException {
        //   SQLiteDatabase.CursorFactory ctx;
        //   TaskDbAdapter  taskdb= new TaskDbAdapter(Context ctx);
        SQLiteDatabase data = this.getWritableDatabase();
        return this;
    }

    // dong ket noi voi CSDL
    public void closeDatabase() {
        SQLiteDatabase database = getWritableDatabase();
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

}
