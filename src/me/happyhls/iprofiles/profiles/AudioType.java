package me.happyhls.iprofiles.profiles;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;

public class AudioType {
	
	public enum VoiceType{
		Global,Ringtone,Media,Notification,Alarm
	}
	
	public class VoiceTypeSettings{
		Uri mediaURI = null;
		int mediaVolume = -1;
		int mediaMaxVolume = -1;
		String tipString = null;
	}
	
	private AudioManager audioManager;
	private static AudioType audioType = null;
	private Context context;
	private SharedPreferences sharedPreferences = null;
	
	public AudioType(Context context, SharedPreferences preferences){
		this.context = context;
		this.sharedPreferences = preferences;
		audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

	}
	
	public static AudioType getSharedAudioType(Context context,SharedPreferences sharedPreferences){
		if (audioType == null)
			audioType = new AudioType(context, sharedPreferences);
		return audioType;
	}
	
	public static String getKey(VoiceType voiceType){
		switch (voiceType) {
		case Global:
			return "profile_setting_voiceall";
		case Ringtone:
			return "profile_setting_ringtone";
		case Media:
			return "profile_setting_media";
		case Notification:
			return "profile_setting_notification";
		case Alarm:
			return "profile_setting_alarm";
		default:
			return null;
		}
	}
	
	public static VoiceType getVoiceType(String key){
		if(key.equals("profile_setting_voiceall")){
			return VoiceType.Global;
		}
		else if(key.equals("profile_setting_ringtone")){
			return VoiceType.Ringtone;
		}
		else if(key.equals("profile_setting_media")){
			return VoiceType.Media;
		}
		else if(key.equals("profile_setting_notification")){
			return VoiceType.Notification;
		}
		else if(key.equals("profile_setting_alarm")){
			return VoiceType.Alarm;
		}
		else{
			return null;
		}
	}
	
	public int resetDefaultVolumeFromPreference(VoiceType voiceType){
		return sharedPreferences.getInt(getKey(voiceType), 0);
	}
	
	public void setVolumePreference(VoiceType voiceType,int value){
        Editor editor = sharedPreferences.edit();  
    	//currentVolumeSet = currentVolumeSet * voiceTypeSettings.mediaMaxVolume / averageVolumeMax;
        editor.putInt(getKey(voiceType), value);
        editor.commit();
	}
	
	public VoiceTypeSettings initializeSettingsFromDefault(VoiceType voiceType){
		
		VoiceTypeSettings voiceTypeSettings = new VoiceTypeSettings();
		switch (voiceType) {
		case Global:
			voiceTypeSettings.mediaURI = RingtoneManager.getActualDefaultRingtoneUri(context,RingtoneManager.TYPE_ALL);
			voiceTypeSettings.mediaVolume = 25;
			voiceTypeSettings.mediaMaxVolume = 100;
			voiceTypeSettings.tipString = "全局音量设置，详细设置会替换全局音量";
			break;

		case Ringtone:
			voiceTypeSettings.mediaURI = RingtoneManager.getActualDefaultRingtoneUri(context,RingtoneManager.TYPE_RINGTONE);
			voiceTypeSettings.mediaVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
			voiceTypeSettings.mediaMaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
			voiceTypeSettings.tipString = "铃声音量";
			break;
			
		case Media:
			voiceTypeSettings.mediaURI = RingtoneManager.getActualDefaultRingtoneUri(context,RingtoneManager.TYPE_NOTIFICATION);
			voiceTypeSettings.mediaVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
			voiceTypeSettings.mediaMaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
			voiceTypeSettings.tipString = "铃声音量";
			break;
			
		case Notification:
			voiceTypeSettings.mediaURI = RingtoneManager.getActualDefaultRingtoneUri(context,RingtoneManager.TYPE_NOTIFICATION);
			voiceTypeSettings.mediaVolume = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
			voiceTypeSettings.mediaMaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION);
			voiceTypeSettings.tipString = "提示音音量";
			break;
			
		case Alarm:
			voiceTypeSettings.mediaURI = RingtoneManager.getActualDefaultRingtoneUri(context,RingtoneManager.TYPE_ALARM);
			voiceTypeSettings.mediaVolume = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);
			voiceTypeSettings.mediaMaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
			voiceTypeSettings.tipString = "闹钟音量";
			break;
			
		default:
			break;
		}
		if(voiceTypeSettings.mediaURI == null){
			voiceTypeSettings.mediaURI = RingtoneManager.getActualDefaultRingtoneUri(context,RingtoneManager.TYPE_NOTIFICATION);
		}
		
		return voiceTypeSettings;
	}
}
