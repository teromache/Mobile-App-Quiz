package android.demo.entities.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.demo.entities.Account;
import android.demo.entities.Mark;
import android.widget.Toast;

public class AccountDB extends SQLiteOpenHelper {

    private Context context;
    private static String dbName = "userDB";
    private static String tableName = "account";
    public static String tableName2 = "User_Mark";
    public static String idColumn = "id";
    public static String columnUsername = "username";
    private static String columnPassword = "password";
    private static String columnEmail = "email";
    private static String columnFullname = "Fullname";
    private static String cMark = "Correct_Mark";
    private static String wMark = "Incorrect_Mark";
    private static String date = "Date";
    private static String userid = "UserID";
    private static String subject = "Subject";

    private static final String CREATE_TABLE_MARK = "CREATE TABLE "
            + tableName2 + "(" + idColumn + " integer PRIMARY KEY,"
            + userid + " integer,"
            + columnUsername + " text,"
            + subject + " text,"
            + cMark + " integer,"
            + date + " text,"
            + wMark + " integer)";

    public AccountDB(Context context){
        super(context, dbName, null,7);

    }

    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("create table " + tableName + "(" +
                idColumn + " integer primary key autoincrement, " +
                columnUsername + " text, " +
                columnPassword + " text," +
                columnEmail + " text," +
                columnFullname + " text " +
                ")");

        sqLiteDatabase.execSQL(CREATE_TABLE_MARK);

    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName2);
        onCreate(sqLiteDatabase);
    }

    public boolean create (Account account)
    {
        boolean result = true;

        try{
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(columnUsername, account.getUsername());
            contentValues.put(columnPassword, account.getPassword());
            contentValues.put(columnFullname, account.getFullname());
            contentValues.put(columnEmail, account.getEmail());
            result = sqLiteDatabase.insert(tableName, null, contentValues) > 0;


        } catch (Exception e)
        {
            result = false;
        }
        return result;
    }

    public boolean create_Mark(Mark mark)
    {
        boolean result = true;

        try{
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(userid, mark.getUserid());
            contentValues.put(columnUsername, mark.getUsername());
            contentValues.put(subject, mark.getSubject());
            contentValues.put(cMark, mark.getcMark());
            contentValues.put(date, mark.getDate());
            contentValues.put(wMark, mark.getwMark());
            result = sqLiteDatabase.insert(tableName2, null, contentValues) > 0;

        } catch (Exception e)
        {
            result = false;
        }
        return result;
    }

    public boolean update (Account account)
    {
        boolean result = true;

        try{
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(columnUsername, account.getUsername());
            contentValues.put(columnPassword, account.getPassword());
            contentValues.put(columnFullname, account.getFullname());
            contentValues.put(columnEmail, account.getEmail());
            result = sqLiteDatabase.update(tableName, contentValues, idColumn + " = ?", new String[] {String.valueOf(account.getId()) }) > 0;


        } catch (Exception e)
        {
            result = false;
        }
        return result;
    }

    public Account login(String username, String password)
    {
        Account account = null;
        try{
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where username = ? and password =?", new String[] {username,password});
            if(cursor.moveToFirst())
            {
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                account.setEmail(cursor.getString(3));
                account.setFullname(cursor.getString(4));
            }

        } catch (Exception e){
            account = null;
        }return account;
    }

    public boolean checkUser(String username){
        String[] columns = {
                idColumn
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = columnUsername + " = ?";
        String[] selectionArgs = { username };

        Cursor cursor = db.query(tableName,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    public Account checkUsername(String username)
    {
        Account account = null;
        try{
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where username = ?", new String[] {username});
            if(cursor.moveToFirst())
            {
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                account.setEmail(cursor.getString(3));
                account.setFullname(cursor.getString(4));
            }

        } catch (Exception e){
            account = null;
        }return account;
    }

    public Account find(int id)
    {
        Account account = null;
        try{
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where id = ?", new String[] {String.valueOf(id)});
            if(cursor.moveToFirst())
            {
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                account.setEmail(cursor.getString(3));
                account.setFullname(cursor.getString(4));
            }
        } catch (Exception e){
            account = null;
        }return account;
    }

    public Mark score(int id)
    {
        Mark mark = null;
        try{
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName2 + " where id = ?", new String[] {String.valueOf(id)});
            if(cursor.moveToFirst())
            {
                mark = new Mark();
                mark.setId(cursor.getInt(1));
                mark.setUsername(cursor.getString(2));
                mark.setSubject(cursor.getString(3));
                mark.setcMark(cursor.getInt(4));
                mark.setwMark(cursor.getInt(5));
                mark.setDate(cursor.getString(6));
            }
        } catch (Exception e){
            mark = null;
        }return mark;
    }

    public Cursor readAllData(int id){
        //String query = "SELECT * FROM " + tableName2;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("select * from " + tableName2 + " where UserID = ?", new String[] {String.valueOf(id)});;
        }
        return cursor;
    }

    public void deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + tableName2 + " WHERE " +userid+ " = " +id+"");
    }

   // public void deleteAllData(){
       // SQLiteDatabase db = this.getWritableDatabase();
       // db.execSQL("DELETE FROM " + tableName2);
   // }

    public void updatePassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(columnPassword, password);
        db.update(tableName, values, columnUsername+" = ?",new String[] { email });
        db.close();
    }

}
