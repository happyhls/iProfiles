package me.happyhls.iprofiles.helper;

import me.happyhls.iprofiles.model.Profile;
import me.happyhls.iprofiles.model.Schedule;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "iProfiles.sqlite";
	public static final int DATABASE_VERSION = 1;
	private static SQLiteDatabase db;

	private static DatabaseHelper mInstance;
	
	private static final String MODEL_ID = "_id";
	private static final String MODEL_NAME = "name";

	private static final String PROFILE_TABLE_NAME = "profiles";
	private static final String PROFILE_MUTE = "mute";
	private static final String PROFILE_VIBRATE = "vibrate";
	private static final String PROFILE_RINGTONE = "ringtone";
	private static final String PROFILE_MEDIA = "media";
	private static final String PROFILE_NOTIFICATION = "notification";
	private static final String PROFILE_ALARM = "alarm";

	private static final String SCHEDULE_TABLE_NAME = "schedules";
	private static final String SCHEDULE_TYPE = "type";
	private static final String SCHEDULE_PROFILEID = "profileId";
	private static final String SCHEDULE_ACTION = "type";
	private static final String SCHEDULE_TIME = "time";
	private static final String SCHEDULE_NOTE = "note";

	private static final String PROFILE_CREATE_STATEMENT = "CREATE TABLE "
            + PROFILE_TABLE_NAME + "(" + MODEL_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + MODEL_NAME +" TEXT, "
            + PROFILE_MUTE + " INTEGER, "
            + PROFILE_VIBRATE + " INTEGER, "
            + PROFILE_RINGTONE + " INTEGER, "
            + PROFILE_MEDIA + " INTEGER, "
            + PROFILE_NOTIFICATION + " INTEGER , "
            + PROFILE_ALARM + " INTEGER "
            +" );";
	
	private static final String SCHEDULE_CREATE_STATEMENT = "CREATE TABLE "
            + SCHEDULE_TABLE_NAME + "(" + MODEL_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + MODEL_NAME +" TEXT, "
            + SCHEDULE_TYPE + " INTEGER, "
            + SCHEDULE_PROFILEID + " INTEGER, "
            + SCHEDULE_ACTION + " INTEGER, "
            + SCHEDULE_TIME + " INTEGER, "
            + SCHEDULE_NOTE + " TEXT "
            +" );";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		db = this.getWritableDatabase();
	}

	public static DatabaseHelper getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new DatabaseHelper(context);
		}
		return mInstance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// db.execSQL(LIGHTS_CREATE_STATEMENT);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {

		// db.execSQL("DROP TABLE IF EXISTS " + LIGHTCONTROLIMAGES_TABLE_NAME);
		onCreate(db);
	}

	
	public Profile getProfile(long id){
		String[] columns = { MODEL_ID, MODEL_NAME, PROFILE_MUTE, PROFILE_VIBRATE, PROFILE_RINGTONE,
								PROFILE_MEDIA, PROFILE_NOTIFICATION, PROFILE_ALARM};
		String whereClause = MODEL_ID + "= ? ";
		String[] whereParams = { String.valueOf(id) };
		
		Cursor cursor = db.query(PROFILE_TABLE_NAME, columns, whereClause, whereParams, null, null, null);
		
		try{
			if(cursor!=null){
				cursor.moveToFirst();
				Profile profile = new Profile(cursor.getLong(0), cursor.getString(1), cursor.getInt(2)>0,
						cursor.getInt(3)>0, cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7));
				return profile;
			}
		} catch (SQLiteException ex){
			ex.printStackTrace();
			return null;
		}
		
		return null;
	}
	
	public long addProfile(Profile profile){
		ContentValues contentValues = new ContentValues();
		if(profile.getId()>0){
			contentValues.put(MODEL_ID, profile.getId());
		}
		contentValues.put(MODEL_NAME, profile.getName());
		contentValues.put(PROFILE_MUTE, profile.isMute()?1:0);
		contentValues.put(PROFILE_VIBRATE, profile.isVibrate()?1:0);
		contentValues.put(PROFILE_RINGTONE, profile.getRingtone());
		contentValues.put(PROFILE_MEDIA, profile.getMedia());
		contentValues.put(PROFILE_NOTIFICATION, profile.getNotification());
		contentValues.put(PROFILE_ALARM, profile.getAlarm());
		return db.insert(PROFILE_TABLE_NAME, null, contentValues);
	}
	
	public Schedule getSchedule(long id){
		String[] columns = { MODEL_ID, MODEL_NAME, SCHEDULE_TYPE, SCHEDULE_PROFILEID, SCHEDULE_ACTION,
				SCHEDULE_TIME, SCHEDULE_NOTE };
		String whereClauses = MODEL_ID + "=? ";
		String[] whereParams = { String.valueOf(id) };
		
		Cursor cursor = db.query(SCHEDULE_TABLE_NAME, columns, whereClauses, whereParams, null, null, null);

		try{
			if(cursor!=null){
				cursor.moveToFirst();
				Schedule schedule = new Schedule(cursor.getLong(0), cursor.getString(1),cursor.getInt(2), 
						cursor.getInt(3), cursor.getInt(4), cursor.getLong(5), cursor.getString(6));
				return schedule;
			}
		}catch (SQLiteException ex){
			ex.printStackTrace();
			return null;
		}
		return null;
	}
	
	public long addSchedule(Schedule schedule){
		ContentValues contentValues = new ContentValues();
		if(schedule.getId()>0){
			contentValues.put(MODEL_ID, schedule.getId());
		}
		contentValues.put(MODEL_NAME, schedule.getName());
		contentValues.put(SCHEDULE_TYPE, schedule.getType());
		contentValues.put(SCHEDULE_PROFILEID, schedule.getProfileId());
		contentValues.put(SCHEDULE_ACTION, schedule.getAction());
		contentValues.put(SCHEDULE_TIME, schedule.getTime());
		contentValues.put(SCHEDULE_NOTE, schedule.getNote());
		return db.insert(SCHEDULE_TABLE_NAME, null, contentValues);
	}

}