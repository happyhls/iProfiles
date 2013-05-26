package me.happyhls.iprofiles.tools;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.inputmethodservice.Keyboard.Key;
import android.util.Log;

public class ProfilesDBHelper extends DBHelper{
	
	public static String TABLE = "profiles";
	
	private ProfilesDBHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public ProfilesDBHelper(Context context) {
		super(context);

		// TODO Auto-generated constructor stub
	}
	
	//数据库第一次创建时会调用，一般在其中创建数据库表
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//使用execSQL()方法执行DDL语句，如果没有异常，这个方法没有返回值		
		db.execSQL("create table " + TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ ProfileDBHelperKey.name + " varchar(20)," 
                    + ProfileDBHelperKey.mute + " INTEGER,"
                    + ProfileDBHelperKey.vibrate + " INTEGER,"
                    + ProfileDBHelperKey.ringtone + " INTEGER,"
                    + ProfileDBHelperKey.media + " INTEGER,"
                    + ProfileDBHelperKey.notification + " INTEGER,"
                    + ProfileDBHelperKey.alarm + " INTEGER"
                    + ")"
                    ); 
		setDefaultProfiles();
	}
	
	private void setDefaultProfiles(){
		insertValues("户外", 0, 1, 7, 7, 7, 7); 
		insertValues("标准", 0, 1, 5, 5, 5, 5);
		insertValues("安静", 0, 1, 3, 3, 3, 3);
		insertValues("震动", 1, 1, 0, 0, 0, 0);
		insertValues("静音", 1, 0, 0, 0, 0, 0);
		Log.i("ProfilesDBHelper","Initialize Datas");

	}
	
	public void insertValues(String name, int mute, int vibrate, int ringtone, int media, int notification, int alarm){
		ContentValues cv = new ContentValues();
		cv.put(ProfileDBHelperKey.name, name);
		cv.put(ProfileDBHelperKey.mute, mute);
		cv.put(ProfileDBHelperKey.vibrate, vibrate);
		cv.put(ProfileDBHelperKey.ringtone, ringtone);
		cv.put(ProfileDBHelperKey.media, media);
		cv.put(ProfileDBHelperKey.notification, notification);
		cv.put(ProfileDBHelperKey.alarm, alarm);
		super.insert(TABLE,cv);
	}
	
	public Cursor queryAll(){
		return query(TABLE,null, null, null, null, null);
	}
	
	public Cursor queryById(int id){
		HashMap<String, String> tmp = new HashMap<String, String>();
		tmp.put(ProfileDBHelperKey.id, id+"");
		return query(TABLE,null, tmp, null, null, null);
	}
	
	public int deleteById(int id){
		HashMap<String, String> tmp = new HashMap<String, String>();
		tmp.put(ProfileDBHelperKey.id, id+"");
		return delete(TABLE,tmp);
	}
		
	public int updateById(int id, ContentValues cv){
		HashMap<String, String> tmp = new HashMap<String, String>();
		tmp.put(ProfileDBHelperKey.id, id+"");
		return update(TABLE,cv, tmp);
	}
	

}


