package me.happyhls.iprofiles.tools;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DBHelper extends SQLiteOpenHelper {
	
	protected Context context;
	public SQLiteDatabase database = null;
	public static final String DATABASE_FILE = "database.db";
	public static final int DATABASE_VERSION = 1;

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		database = getWritableDatabase();
		this.context = context;
	}
	
	public DBHelper(Context context) {
		super(context, DATABASE_FILE, null, DATABASE_VERSION);
		database = getWritableDatabase();
		this.context = context;

	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		database = db;
	}


	@Override
	public void onUpgrade(SQLiteDatabase db,  int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		database = db;

	}
	
	@Override
	public synchronized void close() {
		super.close();
	}
	
	public void insert(String table,ContentValues cv){		
		if(database != null){
			database.insert(table, null, cv);
		}
	}
	
	public int update(String table,ContentValues cv,HashMap<String, String> whereKeyValuePairs){
		if(database != null){
			StringBuilder whereArgs = new StringBuilder();
			String[] whereValues = new String[whereKeyValuePairs.size()];
			int count=0;
			for (String keys : whereKeyValuePairs.keySet()) {
				if(whereArgs.length() !=0)
					whereArgs.append(" and ");
				whereArgs.append(keys);
				whereArgs.append(" = ? ");
				whereValues[count] = whereKeyValuePairs.get(keys);
				count++;
			}
			return database.update(table, cv, whereArgs.toString(), whereValues);
		}
		return -1;
	}
	
	public int delete(String table,HashMap<String, String> whereKeyValuePairs){
		if(database != null){
			StringBuilder whereArgs = new StringBuilder();
			String[] whereValues = new String[whereKeyValuePairs.size()];
			int count=0;
			for (String keys : whereKeyValuePairs.keySet()) {
				if(whereArgs.length() !=0)
					whereArgs.append(" and ");
				whereArgs.append(keys);
				whereArgs.append(" = ? ");
				whereValues[count] = whereKeyValuePairs.get(keys);
				count++;
			}
			return database.delete(table, whereArgs.toString(), whereValues);
		}
		return -1;
	}

	public Cursor rawQuery(String sql,String[] args) {
		if(database != null)
			return database.rawQuery(sql,args);
		return null;
	}
		
	public Cursor query(String table,String[] columns, HashMap<String, String> whereKeyValuePairs, String groupBy,
			String having, String orderBy) {
	    
	    if(database != null){
	    	if(whereKeyValuePairs != null){
				StringBuilder whereArgs = new StringBuilder();
				String[] whereValues = new String[whereKeyValuePairs.size()];
				int count=0;
				for (String keys : whereKeyValuePairs.keySet()) {
					if(whereArgs.length() !=0)
						whereArgs.append(" and ");
					whereArgs.append(keys);
					whereArgs.append(" = ? ");
					whereValues[count] = whereKeyValuePairs.get(keys);
					count++;
				}
				return database.query(table, columns, whereArgs.toString(), whereValues, groupBy, having, orderBy);
	    	}
	    	else{
				return database.query(table, columns, null, null, groupBy, having, orderBy);
	    	}	

	    }
	    return null;
	    
	}
	
}
