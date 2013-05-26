package me.happyhls.iprofiles.profiles;

import me.happyhls.iprofiles.MainListActivity;
import me.happyhls.iprofiles.R;
import me.happyhls.iprofiles.R.layout;
import me.happyhls.iprofiles.tools.ProfilesDBHelper;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;

public class ProfileSettingsActivity extends FragmentActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_item_detail);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();  
        boolean addflag = intent.getBooleanExtra("Add", false);
        int id= intent.getIntExtra("ID", -1);
        Log.i("ProfileSettingActivity","Add:"+addflag+" ID:"+id);
		// savedInstanceState is non-null when there is fragment state
		// saved from previous configurations of this activity
		// (e.g. when rotating the screen from portrait to landscape).
		// In this case, the fragment will automatically be re-added
		// to its container so we don't need to manually add it.
		// For more information, see the Fragments API guide at:
		//
		// http://developer.android.com/guide/components/fragments.html
		//
		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			FragmentManager fragmentManager = getFragmentManager();  
	        FragmentTransaction fragmentTransaction =   
	            fragmentManager.beginTransaction();  
	        ProfileFragment fragmentProfile = new ProfileFragment(this,addflag,id);
	        fragmentTransaction.replace(android.R.id.content, fragmentProfile);          
	        fragmentTransaction.addToBackStack(null);   
	        fragmentTransaction.commit();  
	        
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpTo(this,
					new Intent(this, MainListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
