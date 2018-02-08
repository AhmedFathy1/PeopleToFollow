package com.example.android.af.follower;


public class PersonData {
    private int mId;
    private String mUserName;
    private String mGoogleId;
    private String mTwitterId;
    private String mInstagram;

    public PersonData(String mUserName, String googleId, String mTwitterId, String instagram_id) {

        this.mUserName = mUserName;
        this.mGoogleId = googleId;
        this.mTwitterId = mTwitterId;
        this.mInstagram = instagram_id;
    }

    public PersonData(int id, String mUserName, String googleId, String mTwitterId, String instagram_id) {
        this.mId = id;
        this.mUserName = mUserName;
        this.mGoogleId = googleId;
        this.mTwitterId = mTwitterId;
        this.mInstagram = instagram_id;
    }

    public PersonData() {
    }

    // getting ID
    public int getID() {
        return this.mId;
    }

    // setting id
    public void setID(int id) {
        this.mId = id;
    }

    public PersonData(int i, String string, String mUserName) {
        this.mUserName = mUserName;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getGoogleId() {
        return mGoogleId;
    }

    public void setGoogleId(String googleId) {
        mGoogleId = googleId;
    }

    public String getTwitterId() {
        return mTwitterId;
    }

    public void setTwitterId(String mTwitterId) {
        this.mTwitterId = mTwitterId;
    }

    public String getInstagram() {
        return mInstagram;
    }

    public void setInstagram(String instagram) {
        mInstagram = instagram;
    }
}
