package me.happyhls.iprofiles.profiles;

import me.happyhls.iprofiles.R;
import me.happyhls.iprofiles.R.xml;
import me.happyhls.iprofiles.profiles.AudioType.VoiceType;
import me.happyhls.iprofiles.tools.ProfilesDBHelper;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

public class ProfileFragment extends PreferenceFragment implements OnPreferenceClickListener,OnSharedPreferenceChangeListener{
	
	SharedPreferences sharedPreferences;
	
	private EditTextPreference namePreference;
	private CheckBoxPreference mutePreference;
	private CheckBoxPreference volumeAllCheckPreference;
	private AudioPickerPreference volumeAllPickerPreference;
	private CheckBoxPreference viratePreference;
	private AudioPickerPreference ringtonePreference;
	private AudioPickerPreference mediaPreference;
	private AudioPickerPreference notificationPreference;
	private AudioPickerPreference alarmPreference;
	
	private Preference lastClickedPreference = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.profile_settings);
        
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        
        namePreference = (EditTextPreference)findPreference("profile_setting_name");
        mutePreference = (CheckBoxPreference)findPreference("profile_setting_quiteall");
    	volumeAllCheckPreference = (CheckBoxPreference)findPreference("profile_setting_voiceall_check");
    	viratePreference = (CheckBoxPreference)findPreference("profile_setting_vibrate");
    	volumeAllPickerPreference = (AudioPickerPreference)findPreference(AudioType.getKey(VoiceType.Global));
    	ringtonePreference = (AudioPickerPreference)findPreference(AudioType.getKey(VoiceType.Ringtone));
    	mediaPreference = (AudioPickerPreference)findPreference(AudioType.getKey(VoiceType.Media));
    	notificationPreference = (AudioPickerPreference)findPreference(AudioType.getKey(VoiceType.Notification));
    	alarmPreference = (AudioPickerPreference)findPreference(AudioType.getKey(VoiceType.Alarm));
    	
    	//namePreference.setOnPreferenceChangeListener(this);
    	namePreference.setOnPreferenceClickListener(this);
    	
    	//mutePreference.setOnPreferenceChangeListener(this);
    	mutePreference.setOnPreferenceClickListener(this);
    	//volumeAllCheckPreference.setOnPreferenceChangeListener(this);
    	volumeAllCheckPreference.setOnPreferenceClickListener(this);
    	//volumeAllPickerPreference.setOnPreferenceChangeListener(this);
    	volumeAllPickerPreference.setOnPreferenceClickListener(this);
    	//viratePreference.setOnPreferenceChangeListener(this);
    	viratePreference.setOnPreferenceClickListener(this);
    	//ringtonePreference.setOnPreferenceChangeListener(this);
    	ringtonePreference.setOnPreferenceClickListener(this);
    	//mediaPreference.setOnPreferenceChangeListener(this);
    	mediaPreference.setOnPreferenceClickListener(this);
    	//notificationPreference.setOnPreferenceChangeListener(this);
    	notificationPreference.setOnPreferenceClickListener(this);
    	//alarmPreference.setOnPreferenceChangeListener(this);
    	alarmPreference.setOnPreferenceClickListener(this);
    	
    	ProfilesDBHelper profilesDBHelper = new ProfilesDBHelper(getActivity());
    	
    	
    }
	
	@Override  
	public void onResume() {  
		super.onResume();  
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}  
	
	@Override  
	public void onPause() {  
		super.onPause();  
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}
	  
	
	@Override
	public boolean onPreferenceClick(Preference preference) {
		// TODO Auto-generated method stub
        Log.i("ProfileFragment", "onPreferenceClick----->"+String.valueOf(preference.getKey()));  
        lastClickedPreference = preference;
        if (namePreference == preference) {
			namePreference.setTitle(namePreference.getEditText().getText().toString());
		}
		return true;
	}  

	@Override
	public void onSharedPreferenceChanged(SharedPreferences preference, String arg1) {
		// TODO Auto-generated method stub
		//if(lastClickedPreference.getKey().equals(ringtonePreference.getKey()))
		
		if( lastClickedPreference instanceof AudioPickerPreference){
			Log.i("ProfileFragment", "onPreferenceChange----->"+String.valueOf(preference.getInt(lastClickedPreference.getKey(), -1)));  

		}
		else if (lastClickedPreference == namePreference) {
			namePreference.setTitle(preference.getString(namePreference.getKey(), ""));
		}
		
		
	}



}
