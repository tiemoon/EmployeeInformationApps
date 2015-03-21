package com.example.oop.employeeinformationapps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OOP on 3/6/2015.
 */
public class DBHandler extends SQLiteOpenHelper  {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String EMPLOYEE_DB = "employeeDb";

    private static final String EMPLOYEE = "tbl_employee";

    private static final String ID = "id";
    private static final String EMPLOYEE_NAME = "name";
    private static final String EMPLOYEE_CODE = "code";
    private static final String EMPLOYEE_SALARY = "salary_amount";

    public DBHandler(Context context) {
        super(context, EMPLOYEE_DB, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_EMPLOYEE_TABLE = "CREATE TABLE " + EMPLOYEE + "("
                + ID + " INTEGER PRIMARY KEY," + EMPLOYEE_NAME + " TEXT,"
                + EMPLOYEE_CODE + " TEXT," + EMPLOYEE_SALARY + " REAL" + ")";
        db.execSQL(SQL_CREATE_EMPLOYEE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE);
        // Create tables again
        onCreate(db);
    }

    void addEmployee(Employee anEmployee) {
        SQLiteDatabase db = super.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EMPLOYEE_NAME, anEmployee.getName());
        values.put(EMPLOYEE_CODE, anEmployee.getCode());
        values.put(EMPLOYEE_SALARY, anEmployee.getSalary());

        // Inserting Row
        db.insert(EMPLOYEE, null, values);

        db.close(); // Closing database connection
    }

    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = new ArrayList<Employee>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + EMPLOYEE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(cursor.getString(0)));
                employee.setCode(cursor.getString(1));
                employee.setName(cursor.getString(2));
                employee.setSalary(Double.parseDouble(cursor.getString(3)));
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }
        return employeeList;
    }

    public int updateContact(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EMPLOYEE_CODE, employee.getCode());
        values.put(EMPLOYEE_NAME, employee.getName());
        values.put(EMPLOYEE_SALARY, employee.getSalary());

        return db.update(EMPLOYEE, values, ID + " = ?",
                new String[] { String.valueOf(employee.getId()) });
    }

    public void deleteContact(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EMPLOYEE, ID + " = ?",
                new String[] { String.valueOf(employee.getId()) });
        db.close();
    }
}
