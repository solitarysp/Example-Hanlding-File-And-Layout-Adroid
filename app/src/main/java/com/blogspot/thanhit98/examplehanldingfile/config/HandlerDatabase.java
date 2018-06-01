package com.blogspot.thanhit98.examplehanldingfile.config;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.blogspot.thanhit98.examplehanldingfile.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class HandlerDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_V = 1;
    private static final String DATABASE_NAME = "example_data1";
    private static final String TABLE_STUDENT = "student";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_NAME = "name";

    public HandlerDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_V);
    }

    private void init() {
        onUpgrade(this.getWritableDatabase(), DATABASE_V, DATABASE_V + 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateStudent = "CREATE TABLE "
                + TABLE_STUDENT
                + "(" + KEY_PHONE + " TEXT," + KEY_NAME + " TEXT" + ")";
        db.execSQL(CreateStudent);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }

    public void addStudent() {
        try {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, "thanh");
            values.put(KEY_PHONE, "0966211618");
            db.insert(TABLE_STUDENT, null, values);
            db.close();
        } catch (Exception e){
            System.out.println("d");
        }
    }

    public List<Student> getAllStudent() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT  * FROM  " + TABLE_STUDENT;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
           do {
               Student student = new Student();
               student.setName(cursor.getString(0));
               student.setPhone(cursor.getString(1));
               students.add(student);
           }while (cursor.moveToNext());
        }
        return students;
    }
}
