package com.example.dummynote_32;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DB {
	private static final String DATABASE_NAME="notes.db";
	private static final int DATABASE_VERSION=1;
	private static final String DATABASE_TABLE="notes";
	private static final String DATABASE_CREATE=
			"create table notes("
			+"_id INTEGER PRIMARY KEY,"
		    +"note TEXT,"
			+"created INTEGER,"
		    +"modified INTEGER"
			+");";
	
	private static class DatabaseHelper extends SQLiteOpenHelper{
		
		/*public SQLiteOpenHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version)

Added in API level 1
Create a helper object to create, open, and/or manage a database. This method always returns very quickly. The database is not actually created or opened until one of getWritableDatabase() or getReadableDatabase() is called.

Parameters
context	to use to open or create the database
name	of the database file, or null for an in-memory database
factory	to use for creating cursor objects, or null for the default
version	number of the database (starting at 1); if the database is older, onUpgrade(SQLiteDatabase, int, int) will be used to upgrade the database; if the database is newer, onDowngrade(SQLiteDatabase, int, int) will be used to downgrade the database
*/
		
		public DatabaseHelper(Context context){ // 建構子
			super(context,DATABASE_NAME,null,DATABASE_VERSION);//把資料庫名稱與版本資訊放入
		}
		
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	private Context mCtx = null;
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;
	
	public DB(Context ctx){ // 此class DB 的建構子
		this.mCtx = ctx;
	}
	
	public DB open() throws SQLException{
		dbHelper = new DatabaseHelper(mCtx);
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public static final String KEY_ROWID="_id";
	public static final String KEY_NOTE="note";
	public static final String KEY_CREATED="created";
	
	public Cursor getAll(){
		return db.query(DATABASE_TABLE, new String[]{KEY_ROWID,KEY_NOTE,KEY_CREATED},null, null, null, null, null);
	}
			
	//創造新的database
	public long create(String Note){
		Date now = new Date();
		ContentValues args = new ContentValues();
		args.put(KEY_NOTE, Note);
		args.put(KEY_CREATED, now.getTime());
		return db.insert(DATABASE_TABLE, null, args);
	}
	
	//刪除資料
	public boolean delete(long rowId){
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null)>0;
	}
	
}

