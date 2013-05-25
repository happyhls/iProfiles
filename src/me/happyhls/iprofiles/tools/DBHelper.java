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
	protected SQLiteDatabase database = null;
	protected static final String DATABASE_FILE = "database.db";
	protected static final int DATABASE_VERSION = 1;
	protected String table  = null;

	public DBHelper(Context context, String name, CursorFactory factory,
			int version, String table) {
		super(context, name, factory, version);
		this.context = context;
		this.database = getWritableDatabase(); 
		this.table = table;
	}
	
	public DBHelper(Context context,String table) {
		super(context, DATABASE_FILE, null, DATABASE_VERSION);
		this.context = context;
		this.database = getWritableDatabase(); 
		this.table = table;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db,  int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}
	
	@Override
	public synchronized void close() {
		super.close();
	}
	
	public void insert(ContentValues cv){
		if(database != null){
			database.insert(table, null, cv);
		}
	}
	
	public int update(ContentValues cv,HashMap<String, String> whereKeyValuePairs){
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
	
	public int delete(HashMap<String, String> whereKeyValuePairs){
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
		
	//使用SELECT语句段构建查询，SELECT语句内容做为query()方法的参数
	public Cursor query(String[] columns, HashMap<String, String> whereKeyValuePairs, String groupBy,
			String having, String orderBy) {
	    
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
			return database.query(table, columns, whereArgs.toString(), whereValues, groupBy, having, orderBy);
	    }
	    return null;
	    
	}
	
}
