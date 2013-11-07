package me.happyhls.iprofiles.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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

}