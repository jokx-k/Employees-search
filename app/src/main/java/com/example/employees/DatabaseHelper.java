package com.example.employees;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String databaseName = "Employees Database";
    SQLiteDatabase EmployeesDatabase;

    public DatabaseHelper(Context context)
    {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table department(DeptID integer primary key autoincrement, name text)");
        db.execSQL("create table employee(EmpId integer primary key autoincrement,Name text not null, Title text not null,Phone text not null, Email text not null, Dept_ID integer, FOREIGN KEY(Dept_ID) REFERENCES Department (DeptID));");



        ContentValues cv=new ContentValues();


        cv.put("name","Cyber Security");
        db.insert("department",null,cv);
        cv.clear();

        cv.put("Name","jokx");
        cv.put("Title","Cyber Security Analyst");
        cv.put("Phone","023456");
        cv.put("Email","omar@gmail.com");
        cv.put("Dept_ID",1);
        db.insert("employee",null,cv);
        cv.clear();

        cv.put("name","AI");
        db.insert("department",null,cv);
        cv.clear();
        cv.put("Name","Abdelrahman");
        cv.put("Title","Data Scienctist");
        cv.put("Phone","012345678");
        cv.put("Email","aboood@gmail.com");
        cv.put("Dept_ID",2);
        db.insert("employee",null,cv);
        cv.clear();

        cv.put("name","Multimedia");
        db.insert("department",null,cv);
        cv.clear();

        cv.put("Name","Omar");
        cv.put("Title","Game Programmer");
        cv.put("Phone","987652");
        cv.put("Email","3ammor@gmail.com");
        cv.put("Dept_ID",3);
        db.insert("employee",null,cv);
        cv.clear();

        cv.put("name","Cyber Security");
        db.insert("department",null,cv);
        cv.clear();

        cv.put("Name","Ezz");
        cv.put("Title","Pentester");
        cv.put("Phone","023456");
        cv.put("Email","ezz@gmail.com");
        cv.put("Dept_ID",1);
        db.insert("employee",null,cv);
        cv.clear();




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists department");
        db.execSQL("drop table if exists employee");
        onCreate(db);
    }

    public Cursor getemployee(String name)
    {
        EmployeesDatabase =getReadableDatabase();
        String[] arg={name};
        Cursor cursor=EmployeesDatabase.rawQuery("Select * from employee where name like ?",arg );
        cursor.moveToFirst();
        EmployeesDatabase.close();

        return cursor;
    }



    public Cursor getemployeedata(int EmployeeId)
    {
        EmployeesDatabase =getReadableDatabase();


        Cursor cursor=EmployeesDatabase.rawQuery("select * from employee where EmpID="+EmployeeId,null);
        cursor.moveToFirst();
        EmployeesDatabase.close();

        return cursor;
    }
    public Cursor getDepartmentName(int DepartmentId)
    {
        EmployeesDatabase =getReadableDatabase();


        Cursor cursor=EmployeesDatabase.rawQuery("select Name from department where DeptID="+DepartmentId,null);
        cursor.moveToFirst();
        EmployeesDatabase.close();

        return cursor;
    }





}