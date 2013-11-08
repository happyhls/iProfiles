package me.happyhls.iprofiles.util;

import java.io.IOException;

import me.happyhls.iprofiles.util.AudioType.VoiceType;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.DialogPreference;
import android.provider.MediaStore.Audio.Media;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.app.AlertDialog.Builder;
 
public class AudioPickerPreference extends DialogPreference {
		
	private Context context;
	private VoiceType voiceType;
	private AudioType.VoiceTypeSettings voiceTypeSettings;
	private AudioManager audioManager;
	private MediaPlayer mediaPlayer;
	private AudioType audioType;
	private int averageVolumeMax = 15;
	private int currentVolumeSet = -1;
		
	public AudioPickerPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		

	}
	
	enum ProfileAddEditStatus{
		Add,Edit
	}
	
	protected void onPrepareDialogBuilder(Builder builder) {

		Log.i(getClass().getName(),"Begin:"+System.currentTimeMillis());
		final ProfileAddEditStatus profileStatus = super.getSharedPreferences().getBoolean("ProfileAddNewFlag",true)?ProfileAddEditStatus.Add:ProfileAddEditStatus.Edit; 
		
		audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		audioType = AudioType.getSharedAudioType(context, getSharedPreferences());
		voiceType = AudioType.getVoiceType(getKey());
		voiceTypeSettings = audioType.initializeSettingsFromDefault(voiceType);
		voiceTypeSettings.mediaVolume = audioType.resetDefaultVolumeFromPreference(voiceType);		
		Log.i(getClass().getName(),"Begin Layout:"+System.currentTimeMillis());

		LinearLayout layout =  new LinearLayout(context);
		layout.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.setMinimumWidth(400);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setPadding(20, 20, 20, 20);

		TextView textnote = new TextView(context);
		textnote.setText(voiceTypeSettings.tipString); 
		textnote.setPadding(20, 5, 20, 5);
		textnote.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		layout.addView(textnote);
		
		SeekBar volumeSeekbar = new SeekBar(context);
		volumeSeekbar.setMax(voiceTypeSettings.mediaMaxVolume);
		volumeSeekbar.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		volumeSeekbar.setProgress(voiceTypeSettings.mediaVolume);
		
		volumeSeekbar.setPadding(20, 5, 20, 5);
		layout.addView(volumeSeekbar);
		builder.setView(layout);
		Log.i(getClass().getName(),"After Layout:"+System.currentTimeMillis());

		mediaPlayer = MediaPlayer.create(getContext(), voiceTypeSettings.mediaURI);
		Log.i(getClass().getName(),"Finish MediaPlayer:"+System.currentTimeMillis());

		volumeSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
				// TODO Auto-generated method stub
				
				currentVolumeSet = progress;

				try {
					if (!mediaPlayer.isPlaying()) {
						try {
							mediaPlayer.prepare();
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						mediaPlayer.start();
					}
				} catch (IllegalStateException e) {
					e.printStackTrace();
				}
				audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
						Math.round(averageVolumeMax * progress / voiceTypeSettings.mediaMaxVolume ), 0);
			}
		}); 
		Log.i(getClass().getName(),"Finally:"+System.currentTimeMillis());

	}

	protected void onDialogClosed(boolean positiveResult) {

		//Log.i("VoicePicker",positiveResult?"True":"False");
		if(positiveResult){
			//Log.i("VoicePicker","currentVolumeSet:"+currentVolumeSet);
			if(currentVolumeSet!= -1){
				//Log.i("VoicePicker","currentVolumeSet:"+currentVolumeSet);
            	//currentVolumeSet = currentVolumeSet * voiceTypeSettings.mediaMaxVolume / averageVolumeMax;
	            if(voiceType == VoiceType.Global){
	            	setSummary("当前音量设置 : "+currentVolumeSet+"%");
	            }
	            else{
	            	setSummary("当前音量设置 : "+currentVolumeSet);
	            }
	            audioType.setVolumePreference(voiceType, currentVolumeSet);
	            
			}
		}
		
		try {
			if (mediaPlayer != null)
				mediaPlayer.release();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,voiceTypeSettings.mediaVolume, 0);
		


	}
	

	
}

