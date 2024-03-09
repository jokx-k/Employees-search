package com.example.employees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText EmployeeName = (EditText) findViewById(R.id.EmployeeName);
        ImageButton SearchButton = (ImageButton) findViewById(R.id.SearchButton);
        ListView EmployeeList = (ListView) findViewById(R.id.EmployeeList);
        ArrayAdapter<String> EmployeesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);


        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                 cursor = db.getemployee(EmployeeName.getText().toString());
                EmployeesAdapter.clear();

                if(cursor.getCount()==0){

                    Toast.makeText(MainActivity.this, "No Name Found", Toast.LENGTH_LONG).show();
                }
                else
                    while (!cursor.isAfterLast())
                    {
                    EmployeesAdapter.add(cursor.getString(1));
                    EmployeeList.setAdapter(EmployeesAdapter);

                        cursor.moveToNext();
                    }
            }
        });

        EmployeeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Intent i =new Intent(MainActivity.this,EmployeeDetails.class);

                cursor.moveToPosition(position);
                i.putExtra("EmpId",cursor.getInt(0));
                startActivity(i);



            }
        });




    }
}

