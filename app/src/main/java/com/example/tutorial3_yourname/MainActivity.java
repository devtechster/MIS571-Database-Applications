package com.example.tutorial3_yourname;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = findViewById(R.id.tableLayout);
        dbHelper = new DBHelper(this);

        // Try to create the database from assets if it doesn't already exist
        try {
            dbHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error - perhaps display a Toast or log the error
            return;
        }

        // Open the database to allow access to data
        dbHelper.openDatabase();

        // Load and display data in the table
        loadData();
    }

    @SuppressLint("SetTextI18n")
    private void loadData() {
        // Get a cursor to all users in the database
        Cursor cursor = dbHelper.getAllUsers();
        if (cursor.moveToFirst()) {
            do {
                // Extract user data from each row
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
                @SuppressLint("Range") int age = cursor.getInt(cursor.getColumnIndex("age"));

                // Create a new table row
                TableRow row = new TableRow(this);

                // Create TextViews for each column and set their text
                TextView idTextView = new TextView(this);
                idTextView.setText(String.valueOf(id));
                row.addView(idTextView);

                TextView nameTextView = new TextView(this);
                nameTextView.setText(name);
                row.addView(nameTextView);

                TextView emailTextView = new TextView(this);
                emailTextView.setText(email);
                row.addView(emailTextView);

                TextView ageTextView = new TextView(this);
                ageTextView.setText(String.valueOf(age));
                row.addView(ageTextView);

                // Add the row to the table layout
                tableLayout.addView(row);
            } while (cursor.moveToNext());
        }
        cursor.close();  // Close cursor after use
    }
}
