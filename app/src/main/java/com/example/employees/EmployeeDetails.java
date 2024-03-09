package com.example.employees;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class EmployeeDetails extends AppCompatActivity {

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        TextView Name=(TextView)findViewById(R.id.NameText);
        TextView Title=(TextView)findViewById(R.id.TitleText);
        TextView Phone=(TextView)findViewById(R.id.PhoneText);
        TextView Email=(TextView)findViewById(R.id.EmailText);
        TextView Depart=(TextView)findViewById(R.id.DepText);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        int EmployeeID=getIntent().getExtras().getInt("EmpId");

        cursor= db.getemployeedata(EmployeeID);
        if( cursor != null ) {

            Name.setText("Name     "+cursor.getString(1));

            Title.setText("Title     "+cursor.getString(2));
            Phone.setText("Phone     "+cursor.getString(3));
            Email.setText("Email     "+cursor.getString(4));

            int DepartID = cursor.getInt(5);

            cursor = db.getDepartmentName(DepartID);

            Depart.setText("Department     "+cursor.getString(0));
        }
    }
}