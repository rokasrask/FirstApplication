package mydomain.firstapplication;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    public static final String TABLE_USERS = "Users";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_ADMIN = "adminlevel";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_MAIL = "mail";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_ADMIN + " INTEGER," +
                COLUMN_NAME + " TEXT," +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_MAIL + " TEXT"+
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user){
        ContentValues  values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_ADMIN, user.getAdminlevel());
        values.put(COLUMN_MAIL, user.getMail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    //Delete user

    public void deleteUser(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_USERS + " WHERE " + COLUMN_NAME + " =\"" + name + "\";");
    }

    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE 1";
        //Curson point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to first row in results
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("name")) != null) {
                dbString += c.getString(c.getColumnIndex("name"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

    public boolean validateUser(String name, String password){
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT * FROM " +TABLE_USERS + " WHERE "
                + COLUMN_NAME + "='" + name + "'AND " +COLUMN_PASSWORD+ "='" + password +"'" , null);
        if (c.getCount()>0)
            return true;
        return false;

    }

}

