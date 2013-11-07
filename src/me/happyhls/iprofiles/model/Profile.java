package me.happyhls.iprofiles.model;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * a model descirbed profile's detailed settings.
 * @author happyhls
 *
 */
public class Profile extends Model implements Serializable,Parcelable{

	/**
	 * serialversionUID
	 */
	private static final long serialVersionUID = 6485045223008981480L;

	private boolean mute;
	private boolean vibrate;
	private int ringtone;
	private int media;
	private int notification;
	private int alarm;
	
	public Profile(long id, String name, boolean mute, boolean vibrate,
			int ringtone, int media, int notification, int alarm) {
		super(id, name);
		this.mute = mute;
		this.vibrate = vibrate;
		this.ringtone = ringtone;
		this.media = media;
		this.notification = notification;
		this.alarm = alarm;
	}
	
	public Profile(String name, boolean mute, boolean vibrate,
			int ringtone, int media, int notification, int alarm) {
		super(name);
		this.mute = mute;
		this.vibrate = vibrate;
		this.ringtone = ringtone;
		this.media = media;
		this.notification = notification;
		this.alarm = alarm;
	}

	/**
	 * @return the mute
	 */
	public boolean isMute() {
		return mute;
	}

	/**
	 * @param mute the mute to set
	 */
	public void setMute(boolean mute) {
		this.mute = mute;
	}

	/**
	 * @return the vibrate
	 */
	public boolean isVibrate() {
		return vibrate;
	}

	/**
	 * @param vibrate the vibrate to set
	 */
	public void setVibrate(boolean vibrate) {
		this.vibrate = vibrate;
	}

	/**
	 * @return the ringtone
	 */
	public int getRingtone() {
		return ringtone;
	}

	/**
	 * @param ringtone the ringtone to set
	 */
	public void setRingtone(int ringtone) {
		this.ringtone = ringtone;
	}

	/**
	 * @return the media
	 */
	public int getMedia() {
		return media;
	}

	/**
	 * @param media the media to set
	 */
	public void setMedia(int media) {
		this.media = media;
	}

	/**
	 * @return the notification
	 */
	public int getNotification() {
		return notification;
	}

	/**
	 * @param notification the notification to set
	 */
	public void setNotification(int notification) {
		this.notification = notification;
	}

	/**
	 * @return the alarm
	 */
	public int getAlarm() {
		return alarm;
	}

	/**
	 * @param alarm the alarm to set
	 */
	public void setAlarm(int alarm) {
		this.alarm = alarm;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		// TODO Auto-generated method stub
		out.writeLong(getId());
		out.writeString(getName());
		out.writeInt(mute?1:0);
		out.writeInt(vibrate?1:0);
		out.writeInt(ringtone);
		out.writeInt(media);
		out.writeInt(notification);
		out.writeInt(alarm);
	}
	
	public Profile(Parcel in) {
		super(in.readLong(),in.readString());
		setMute(in.readInt()>0);
		setVibrate(in.readInt()>0);
		setRingtone(in.readInt());
		setMedia(in.readInt());
		setNotification(in.readInt());
		setAlarm(in.readInt());
	}
	
	public static final Parcelable.Creator<Profile> CREATOR = new Parcelable.Creator<Profile>() {
        public Profile createFromParcel(Parcel in) {
                return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
                // TODO Auto-generated method stub
                return new Profile[size];
        }
	};
	

}
