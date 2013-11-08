package me.happyhls.iprofiles.util;

import me.happyhls.iprofiles.ProfilesActivity;
import me.happyhls.iprofiles.R;
import me.happyhls.iprofiles.SettingActivity;
import me.happyhls.iprofiles.SystemConfigure;
import me.happyhls.iprofiles.helper.DatabaseHelper;
import me.happyhls.iprofiles.model.Profile;
import me.happyhls.iprofiles.model.Schedule;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

public class ApplyProfiles extends Activity{
	
	private String TAG = "ApplyProfile";
	private long profileId;
	private int profileType;
	
	public static final String APPLYPROFILES_INTENT_PROFILEID = "profileId";
	public static final String APPLYPROFILES_INTENT_PROFILETYPE = "profileType";
	
	public static final String APPLYPROFILES_INTENT_SETAIRPLANEMODE = "setAirplaneMode";
	private SharedPreferences sharedPreferences;
	private boolean showNotification;
	private NotificationManager notificationManager;
	
	private DatabaseHelper databaseHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.profile_activity);
		
		databaseHelper = DatabaseHelper.getInstance(this);
		
		Intent intent = getIntent();
		profileId = intent.getLongExtra(APPLYPROFILES_INTENT_PROFILEID, -1);
		profileType = intent.getIntExtra(APPLYPROFILES_INTENT_PROFILETYPE, -1);
		
		if( profileId<0 || profileType<0 ){
			this.finish();
		}
		
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		showNotification = sharedPreferences.getBoolean(SettingActivity.SETTINGACTIVITY_PREFERENCE_SHOWNOTIFICATION, true);
		notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		
		switch (profileType) {
		case Schedule.SCHEDULE_TYPE_PROFILE:
			Profile profile = databaseHelper.getProfile(profileId);
			if(profile==null){
				this.finish();
			}
			AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
			audioManager.setStreamVolume(AudioManager.STREAM_RING, profile.getRingtone(), 0);
			audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, profile.getNotification(), 0);
			audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, profile.getMedia(), 0);
			audioManager.setStreamVolume(AudioManager.STREAM_ALARM, profile.getAlarm(), 0);
			
			if(profile.isVibrate()){
				if(profile.isMute()){
					audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
				}else{
					audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
				}
			}else{
				if(profile.isMute()){
					audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
				}else{
					audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				}
			}

			break;

		case Schedule.SCHEDULE_TYPE_AIRPLANE:
			boolean setAirPlaneMode = intent.getBooleanExtra(APPLYPROFILES_INTENT_SETAIRPLANEMODE, false);
			AirplaneModeService.setAirPlaneMode(this, setAirPlaneMode);	
			showAirplaneModeNotification(setAirPlaneMode);
			break;
		default:
			break;
		}
		
		
		
	}
	
	public void showProfileNotification(String title, String message){
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle(title)
		        .setContentText(message);
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(this, ProfilesActivity.class);

		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(ProfilesActivity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager =
		    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		
		mNotificationManager.notify(SystemConfigure.NOTIFICATION_ID_PROFILE, mBuilder.build());
		
	}
	
	public void showAirplaneModeNotification(boolean isOn){
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle("飞行模式")
		        .setContentText(isOn?"已启用，点击关闭飞行模式":"已停用");
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(this, ApplyProfiles.class);
		resultIntent.putExtra(APPLYPROFILES_INTENT_PROFILETYPE, Schedule.SCHEDULE_TYPE_AIRPLANE);
		resultIntent.putExtra(APPLYPROFILES_INTENT_PROFILEID, 1);
		resultIntent.putExtra(APPLYPROFILES_INTENT_SETAIRPLANEMODE, false);
		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(ProfilesActivity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );

		NotificationManager mNotificationManager =
		    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		if(isOn){
			mBuilder.setContentIntent(resultPendingIntent);			
		}else{
			mBuilder.setAutoCancel(true);
		}
		mNotificationManager.notify(SystemConfigure.NOTIFICATION_ID_AIRPLANEMODE, mBuilder.build());
		
	}
}
