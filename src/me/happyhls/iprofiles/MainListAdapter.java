package me.happyhls.iprofiles;

import java.util.ArrayList;

import me.happyhls.iprofiles.tools.*;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainListAdapter extends BaseAdapter {

	public class ProfilesAdapterData{
		public int id;
		public String name;
		public int mute;
		public int vibrate;
		public int ringtone;
		public int media;
		public int notification;
		public int alarm;
		public ProfilesAdapterDataType type;
		
	}
	public enum ProfilesAdapterDataType{
		Profiles,Setting,AirplaneMode,AddProfiles
	}
	
	public ArrayList<ProfilesAdapterData> datas = null;
	private Context context;
	private LayoutInflater layoutInflater;  
	
	public MainListAdapter(Context context){
		super();
		this.context = context;
		datas = new ArrayList<MainListAdapter.ProfilesAdapterData>();
		layoutInflater = LayoutInflater.from(context);
		initializeDatasFromDataBase();
	}
	 
	private void initializeDatasFromDataBase(){
		ProfilesDBHelper profilesDBHelper = new ProfilesDBHelper(context);
		Cursor datasCursor = profilesDBHelper.queryAll();
		datasCursor.moveToFirst();
		while (true){
			ProfilesAdapterData profilesAdapterData = new ProfilesAdapterData();
			profilesAdapterData.id = datasCursor.getInt(datasCursor.getColumnIndex(ProfileDBHelperKey.id));
			profilesAdapterData.name = datasCursor.getString(datasCursor.getColumnIndex(ProfileDBHelperKey.name));
			profilesAdapterData.mute = datasCursor.getInt(datasCursor.getColumnIndex(ProfileDBHelperKey.mute));
			profilesAdapterData.vibrate = datasCursor.getInt(datasCursor.getColumnIndex(ProfileDBHelperKey.vibrate));
			profilesAdapterData.ringtone = datasCursor.getInt(datasCursor.getColumnIndex(ProfileDBHelperKey.ringtone));
			profilesAdapterData.media = datasCursor.getInt(datasCursor.getColumnIndex(ProfileDBHelperKey.media));
			profilesAdapterData.notification = datasCursor.getInt(datasCursor.getColumnIndex(ProfileDBHelperKey.notification));
			profilesAdapterData.alarm = datasCursor.getInt(datasCursor.getColumnIndex(ProfileDBHelperKey.alarm));
			profilesAdapterData.type = ProfilesAdapterDataType.Profiles;
			datas.add(profilesAdapterData);
			if(!datasCursor.moveToNext()){
				break;
			}
		}
		
		ProfilesAdapterData profilesAdapterData = new ProfilesAdapterData();
		profilesAdapterData.id = -1;
		profilesAdapterData.name = "飞行模式";
		profilesAdapterData.type = ProfilesAdapterDataType.AirplaneMode;
		datas.add(profilesAdapterData);
		
		profilesAdapterData = new ProfilesAdapterData();
		profilesAdapterData.id = -2;
		profilesAdapterData.name = "添加情景模式";
		profilesAdapterData.type = ProfilesAdapterDataType.AddProfiles;
		datas.add(profilesAdapterData);
		
		profilesAdapterData = new ProfilesAdapterData();
		profilesAdapterData.id = -3;
		profilesAdapterData.name = "设置";
		profilesAdapterData.type = ProfilesAdapterDataType.Setting;
		datas.add(profilesAdapterData);
		
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub 
		if (arg0 < datas.size()) {  
            return datas.get(arg0);  
        } else
            return null;  
	}

	@Override
	public long getItemId(int arg0) { 
		// TODO Auto-generated method stub
		return datas.get(arg0).id;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//Log.i(getClass().getName(),"getView");
		ContentView contentView = null; 
		if (convertView == null) {   
			contentView = new ContentView();    
            //获取布局文件的视图   
            convertView = layoutInflater.inflate(android.R.layout.simple_list_item_activated_1, null);   
            //获取控件对象   
            contentView.textView = (TextView)convertView.findViewById(android.R.id.text1);
            contentView.id = datas.get(position).id;
            contentView.textView.setText(datas.get(position).name);
/*            final ContentView tmpContentView = contentView;
            contentView.textView.setOnLongClickListener(new OnLongClickListener() {
				
				@Override
				public boolean onLongClick(View v) { 
					// TODO Auto-generated method stub
					Log.i(getClass().getName(),"长按:"+((TextView)v).getText().toString()+" "+tmpContentView.id);

					return true;
				}
			});
            
            contentView.textView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.i(getClass().getName(),"短按:"+((TextView)v).getText().toString()+" "+tmpContentView.id);

				}
			});*/
            
            //设置控件集到convertView   
            
            convertView.setTag(contentView);   
        }else {   
        	contentView = (ContentView)convertView.getTag();   
        }   
		return convertView; 
	}
	
	class ContentView{
		int id;
		TextView textView;
	}

}
