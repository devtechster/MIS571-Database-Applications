package com.example.tutorial3_yourname;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.File;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "some_data.db";
    private static final int DATABASE_VERSION = 1;
    private static String DATABASE_PATH = "";  // Dynamically get the path
    private SQLiteDatabase myDatabase;
    private final Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        DATABASE_PATH = context.getDatabasePath(DATABASE_NAME).getPath(); // Dynamically determine path
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // No need to create tables as we are using a pre-populated database
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade if necessary
    }

    // Method to create the database by copying it from assets
    public void createDatabase() throws IOException {
        boolean dbExist = checkDatabase();
        if (!dbExist) {
            this.getReadableDatabase(); // Creates an empty database in the default system path
            try {
                copyDatabase(); // Copy the pre-populated database from assets
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    // Check if the database already exists to avoid re-copying it
    private boolean checkDatabase() {
        File dbFile = new File(DATABASE_PATH);
        return dbFile.exists();
    }

    // Copy the database from the assets folder to the system path
    private void copyDatabase() throws IOException {
        InputStream input = context.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH;
        OutputStream output = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }

        output.flush();
        output.close();
        input.close();
    }

    // Open the database to perform operations on it
    public void openDatabase() throws SQLiteException {
        myDatabase = SQLiteDatabase.openDatabase(DATABASE_PATH, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (myDatabase != null) {
            myDatabase.close();
        }
        super.close();
    }

    // Query to fetch all users
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM users", null);
    }
}
