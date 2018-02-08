package com.example.android.af.follower;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "NewPersonManager";

    // New Person table name
    private static final String TABLE_NEW_PERSON = "newPerson";

    // New Person Table Columns names
    private static final String KEY_NAME = "name";
    private static final String KEY_ID = "id";
    private static final String KEY_GOOGLE_ID = "google_id";
    private static final String KEY_TWITTER_ID = "twitter_id";
    private static final String KEY_INSTAGRAM_ID = "instagram_id";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NEW_PERSON_TABLE = "CREATE TABLE " + TABLE_NEW_PERSON +
                "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT, " + KEY_GOOGLE_ID + " TEXT," + KEY_TWITTER_ID + " TEXT, " + KEY_INSTAGRAM_ID + " TEXT )";
        db.execSQL(CREATE_NEW_PERSON_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEW_PERSON);

        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    public void addNewPerson(PersonData newPerson) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, newPerson.getUserName()); // Person Name
        values.put(KEY_GOOGLE_ID, newPerson.getGoogleId()); // Person Facebook ID
        values.put(KEY_TWITTER_ID, newPerson.getTwitterId()); //Person Twitter ID
        values.put(KEY_INSTAGRAM_ID, newPerson.getInstagram());
        // Inserting Row
        db.insert(TABLE_NEW_PERSON, null, values);
        db.close(); // Closing database connection

    }

    // Getting single Person
    public PersonData getPerson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NEW_PERSON, new String[]{KEY_ID, KEY_NAME, KEY_GOOGLE_ID,
                        KEY_TWITTER_ID, KEY_INSTAGRAM_ID}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        PersonData newPerson = new PersonData
                (Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        // return New Person
        return newPerson;
    }

    // Getting All Persons


    public void getAllPeople() {
        PeopleList.getInstance().getList().clear();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NEW_PERSON + " order by " + KEY_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PersonData newPerson = new PersonData();
                newPerson.setID(cursor.getInt(0));
                newPerson.setUserName(cursor.getString(1));
                newPerson.setGoogleId(cursor.getString(2));
                newPerson.setTwitterId(cursor.getString(3));
                newPerson.setInstagram(cursor.getString(4));
                // Adding person to list
                PeopleList.getInstance().getList().add(newPerson);
            } while (cursor.moveToNext());
        }
    }

    // Getting PEOPLE Count
    public int getPeopleCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NEW_PERSON;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating single person
    public int updatePerson(PersonData _person_data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, _person_data.getUserName());
        values.put(KEY_GOOGLE_ID, _person_data.getGoogleId());
        values.put(KEY_TWITTER_ID, _person_data.getTwitterId());
        values.put(KEY_INSTAGRAM_ID, _person_data.getInstagram());
        // updating row
        return db.update(TABLE_NEW_PERSON, values, KEY_ID + "='" + String.valueOf(_person_data.getID()) + "'", null);
    }

    // Deleting single person
    public void deletePerson(PersonData newPerson) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NEW_PERSON, KEY_ID + " = ?",new String[]{String.valueOf(newPerson.getID())});
        db.close();
    }


}
