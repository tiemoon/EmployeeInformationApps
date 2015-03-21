package com.example.oop.employeeinformationapps;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    EditText nameEditText;
    EditText codeEditText;
    EditText salaryEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveButtonClick(View aView)
    {
        codeEditText = (EditText)findViewById(R.id.codeEditText);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        salaryEditText = (EditText) findViewById(R.id.salaryEditText);

        Employee anEmployee = new Employee();
        anEmployee.setCode(codeEditText.getText().toString());
        anEmployee.setName(nameEditText.getText().toString());
        anEmployee.setSalary(Double.parseDouble(salaryEditText.getText().toString()));

        DBHandler dbHandler = new DBHandler(this);
        dbHandler.addEmployee(anEmployee);
    }
}
