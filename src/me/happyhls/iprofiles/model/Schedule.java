package me.happyhls.iprofiles.model;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Schedule extends Model implements Serializable, Parcelable{
	
	/**
	 * serialversionUID
	 */
	private static final long serialVersionUID = 8469219936642685328L;
	
	public static final int SCHEDULE_TYPE_PROFILE = 1;
	public static final int SCHEDULE_TYPE_AIRPLANE = 0;
	
	public static final int SCHEDULE_ACTION_ON = 1;
	public static final int SCHEDULE_ACTION_OFF = 0;
	/**
	 * Profile or Airplane
	 */
	private int type;
	private long profileId;
	
	/**
	 * @return the profileId
	 */
	public long getProfileId() {
		return profileId;
	}

	/**
	 * @param profileId the profileId to set
	 */
	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	/**
	 * Action:on/off
	 */
	private int action;
	
	/**
	 * the time schedule take effect
	 */
	private long time;
	
	private String note;
	
	
	public Schedule(long id, String name, int type, int profileId, int action, long time,
			String note) {
		super(id, name);
		this.type = type;
		this.profileId = profileId;
		this.action = action;
		this.time = time;
		this.note = note;
	}

	public Schedule(String name, int type, int profileId, int action, long time,
			String note) {
		super(name);
		this.type = type;
		this.profileId = profileId;
		this.action = action;
		this.time = time;
		this.note = note;
	}
	
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the action
	 */
	public int getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(int action) {
		this.action = action;
	}

	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
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
		out.writeInt(type);
		out.writeLong(profileId);
		out.writeInt(action);
		out.writeLong(time);
		out.writeString(note);
	}
	
	public Schedule(Parcel in) {
		super(in.readLong(),in.readString());
		setType(in.readInt());
		setProfileId(in.readLong());
		setAction(in.readInt());
		setTime(in.readLong());
		setNote(in.readString());
	}
	
	public static final Parcelable.Creator<Schedule> CREATOR = new Parcelable.Creator<Schedule>() {
        public Schedule createFromParcel(Parcel in) {
                return new Schedule(in);
        }

        @Override
        public Schedule[] newArray(int size) {
                // TODO Auto-generated method stub
                return new Schedule[size];
        }
	};
	
	
}
