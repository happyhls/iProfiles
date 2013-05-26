package me.happyhls.iprofiles.profiles;

import me.happyhls.iprofiles.MainListActivity;
import me.happyhls.iprofiles.R;
import me.happyhls.iprofiles.MainListAdapter.ProfilesAdapterData;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

public class ApplyProfiles {
	
	private String TAG = "ApplyProfile";
	
	public static void SubmitProfiles(Context context,ProfilesAdapterData data){
		SharedPreferences sharedPreference = PreferenceManager
				.getDefaultSharedPreferences(context);
		
		//Show notification!
		boolean showNotification = sharedPreference.getBoolean(
				"setting_notification", true);
		showNotification=true;
		if (showNotification) {			
			NotificationManager nm = (NotificationManager) context
					.getSystemService(context.NOTIFICATION_SERVICE);
			CharSequence contentTitle = "当前情景模式:"+data.name;
			CharSequence contentText = "i情景:"+data.name;

			Notification notification = new Notification(R.drawable.samll_icon,
					contentTitle, System.currentTimeMillis());
			//Notification notification = new Notification.Builder(context)
			//								.setContentText(contentText)
			//								.setContentTitle(contentTitle)
			//								.setSmallIcon(R.drawable.samll_icon)
			//								.getNotification();

			Intent notificationIntent = new Intent(context,
					MainListActivity.class);
			PendingIntent contentIntent = PendingIntent.getActivity(
					context, 0, notificationIntent, 0);
			notification.flags = Notification.FLAG_ONGOING_EVENT;
			notification.setLatestEventInfo(context, contentTitle,
					contentText, contentIntent);
			nm.notify(101, notification);
		}
		
		
		
		
		AudioManager audioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		
		audioManager.setStreamVolume(AudioManager.STREAM_RING,
				data.ringtone, 0);
		audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION,
				data.notification, 0);
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
				data.media, 0);
		audioManager.setStreamVolume(AudioManager.STREAM_ALARM,
				data.alarm, 0);
		
		if (data.mute==1 && data.vibrate==1) {
			audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		} else if (data.mute==1 && data.vibrate==0) {
			audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		} else {
			audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}
	}
	

	public static boolean isAirplaneModeOn(Context context) {
		return Settings.System.getInt(context.getContentResolver(),
				Settings.System.AIRPLANE_MODE_ON, 0) != 0;
	}

	public static void setAirplaneModeOn(Context context, boolean enabling) {
		// Change the system setting
		Settings.System.putInt(context.getContentResolver(),
				Settings.System.AIRPLANE_MODE_ON, enabling ? 1 : 0);

		// Post the intent
		Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
		intent.putExtra("state", enabling);
		context.sendBroadcast(intent);
	}
}
