package me.happyhls.iprofiles.tools;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class ProfilesDBHelper extends DBHelper{

	static class Keys{
		public static final String name = "name";
		public static final String mute = "mute";
		public static final String vibrate = "vibrate";
		public static final String ringtone = "ringtone";
		public static final String media = "media";
		public static final String notification = "notification";
		public static final String alarm = "alarm";
	}
	
	public static final String PROFILETABLE_STRING = "profiles";
	
	public ProfilesDBHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version,PROFILETABLE_STRING);
		// TODO Auto-generated constructor stub
	}
	
	public ProfilesDBHelper(Context context) {
		super(context, PROFILETABLE_STRING);
		// TODO Auto-generated constructor stub
	}
	
	//数据库第一次创建时会调用，一般在其中创建数据库表
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//使用execSQL()方法执行DDL语句，如果没有异常，这个方法没有返回值
		db.execSQL("create table " + PROFILETABLE_STRING + "(id INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ Keys.name + " varchar(20)," 
                    + Keys.mute + " INTEGER,"
                    + Keys.vibrate + " INTEGER,"
                    + Keys.ringtone + " INTEGER,"
                    + Keys.media + " INTEGER,"
                    + Keys.notification + " INTEGER,"
                    + Keys.alarm + " INTEGER"
                    + ")"
                    ); 
	}
	
	public void insert(ContentValues cv){
		super.insert(cv);
	}
	
	public int update(ContentValues cv,HashMap<String, String> whereKeyValuePairs){
		
		return super.update(cv, whereKeyValuePairs);
	}
	
	public int delete(HashMap<String, String> whereKeyValuePairs){
		
		return super.delete(whereKeyValuePairs);
	}

	public Cursor rawQuery(String sql,String[] args) {
		
		return super.rawQuery(sql, args);
	}
		
	//使用SELECT语句段构建查询，SELECT语句内容做为query()方法的参数
	public Cursor query(String[] columns, HashMap<String, String> whereKeyValuePairs, String groupBy,
			String having, String orderBy) {
	    
		return query(columns, whereKeyValuePairs, groupBy, having, orderBy);	    
	}

	


}
