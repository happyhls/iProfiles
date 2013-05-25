package me.happyhls.iprofiles;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import me.happyhls.iprofiles.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen. This fragment is either
 * contained in a {@link MainListActivity} in two-pane mode (on tablets) or a
 * {@link SchedulesActivity} on handsets.
 */
public class SchedulsFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private DummyContent.DummyItem mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public SchedulsFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_item_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			//((TextView) rootView.findViewById(R.id.profiles_detail))
			//		.setText(mItem.content);
			ListView list = (ListView) rootView.findViewById(R.id.profiles_detail);  
			
			//
		    ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  
		    for(int i=0;i<30;i++)  
		    {  
		        HashMap<String, String> map = new HashMap<String, String>();  
		        map.put("ItemTitle", "This is Title.....");  
		        map.put("ItemText", "This is text.....");  
		        mylist.add(map);  
		    }  
		    //ListItem  
		    SimpleAdapter mScheduleAdapter =new SimpleAdapter(getActivity(),
		    		mylist,R.layout.profile_listview,
		    		new String[]{"ItemTitle"},
		    		new int[]{R.id.ItemTitle}
		    		);

		    //
		    list.setAdapter(mScheduleAdapter);  
		}

		return rootView;
	}
}
