package me.happyhls.iprofiles;

import java.util.ArrayList;

import me.happyhls.iprofiles.helper.DatabaseHelper;
import me.happyhls.iprofiles.helper.IntCallBack;
import me.happyhls.iprofiles.helper.ProfilesListAdapter;
import me.happyhls.iprofiles.model.Profile;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ProfilesActivity extends Activity{
	
	ArrayList<Profile> profiles;
	DatabaseHelper databaseHelper;
	ProfilesListAdapter profilesListAdapter; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.profile_activity);
		profiles = new ArrayList<Profile>();
		databaseHelper = DatabaseHelper.getInstance(this);
		
		profiles = databaseHelper.getAllProfiles();
		profilesListAdapter = new ProfilesListAdapter(this, profiles);
		profilesListAdapter.setClickCallBack(new IntCallBack() {
			
			@Override
			public void callback(int pos) {
				// TODO Auto-generated method stub
				
			}
		});
		
		ListView listView = (ListView)findViewById(R.id.listview);
		listView.setAdapter(profilesListAdapter);
	}
}