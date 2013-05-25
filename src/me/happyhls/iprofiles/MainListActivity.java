package me.happyhls.iprofiles;

import me.happyhls.iprofiles.profiles.ProfileFragment;
import me.happyhls.iprofiles.profiles.ProfileSettingsActivity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * An activity representing a list of Items. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link SchedulesActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link MainListFragment} and the item details (if present) is a
 * {@link SchedulsFragment}.
 * <p>
 * This activity also implements the required {@link MainListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class MainListActivity extends FragmentActivity implements
		MainListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_list);

		if (findViewById(R.id.item_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((MainListFragment) getSupportFragmentManager().findFragmentById(
					R.id.item_list)).setActivateOnItemClick(true);
			
		}
		
		

		// TODO: If exposing deep links into your app, handle intents here.
	}
	

	/**
	 * Callback method from {@link MainListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(SchedulsFragment.ARG_ITEM_ID, id);
			//SchedulsFragment fragment = new SchedulsFragment();			
			
			
			
			
			FragmentManager fragmentManager = getFragmentManager();  
	        FragmentTransaction fragmentTransaction =   
	            fragmentManager.beginTransaction();  
	        ProfileFragment fragmentProfile = new ProfileFragment();  
	        fragmentTransaction.replace(android.R.id.content, fragmentProfile);          
	        fragmentTransaction.addToBackStack(null);   
	        fragmentTransaction.commit();  
			
			
			

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
/*			Intent detailIntent = new Intent(this, SchedulesActivity.class);
			detailIntent.putExtra(SchedulsFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);*/
			Intent profileIntent = new Intent(this, ProfileSettingsActivity.class);
			profileIntent.putExtra("ID", id);
			startActivity(profileIntent);
		}
	}
}
