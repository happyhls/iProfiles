package me.happyhls.iprofiles.helper;

import java.util.ArrayList;

import me.happyhls.iprofiles.R;
import me.happyhls.iprofiles.model.Profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProfilesListAdapter extends BaseAdapter{

	private Context context;
	private LayoutInflater layoutInflater;
	
	ArrayList<Profile> profiles = new ArrayList<Profile>();
	private IntCallBack callBack = null;
	
	public ProfilesListAdapter(Context context, ArrayList<Profile> profiles){
		this.context = context;
		this.layoutInflater = LayoutInflater.from(context);
		this.profiles.clear();
		this.profiles.addAll(profiles);
		
	}
	
	public void notifyDataSetChanged(ArrayList<Profile> profiles){
		this.profiles.clear();
		this.profiles.addAll(profiles);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return profiles.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return profiles.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	
	public void setClickCallBack(IntCallBack callback){
		this.callBack = callback;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final int id = position;
		ContainerView listItemView = null;
		if(convertView==null){
			listItemView = new ContainerView();
			convertView = layoutInflater.inflate(R.layout.profile_listview_item,null);
			listItemView.titleTextView = (TextView)convertView.findViewById(R.id.item);
			convertView.setTag(listItemView);
		}
		listItemView.titleTextView.setText(profiles.get(id).getName());
		listItemView.titleTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(callBack!=null)
					callBack.callback(id);
			}
		});
		return convertView;
	}
	
	class ContainerView{
		public TextView titleTextView;
	}
}
