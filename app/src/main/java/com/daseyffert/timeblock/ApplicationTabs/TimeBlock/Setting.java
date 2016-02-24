package com.daseyffert.timeblock.ApplicationTabs.TimeBlock;

/**
 * Created by Daniel on 2/22/2016.
 */
public class Setting {

    private String mWorkPeriod;
    private String mRestPeriod;
    private boolean mSetLoop;
    private boolean mFriendSecurity;

    public Setting() {
    mSetLoop = false;
    mFriendSecurity = false;
    }

    public String getWorkPeriod() {
        return mWorkPeriod;
    }

    public void setWorkPeriod(String workPeriod) {
        mWorkPeriod = workPeriod;
    }

    public String getRestPeriod() {
        return mRestPeriod;
    }

    public void setRestPeriod(String restPeriod) {
        mRestPeriod = restPeriod;
    }

    public boolean isSetLoop() {
        return mSetLoop;
    }

    public void setSetLoop(boolean setLoop) {
        mSetLoop = setLoop;
    }

    public boolean isFriendSecurity() {
        return mFriendSecurity;
    }

    public void setFriendSecurity(boolean friendSecurity) {
        mFriendSecurity = friendSecurity;
    }
}
