package com.daseyffert.timeblock.ApplicationTabs.TimeBlock;

import java.util.Date;

/**
 * Created by Daniel on 2/22/2016.
 */
public class Setting {

    private Date mWorkPeriod;
    private Date mRestPeriod;
    private boolean mSetLoop;
    private boolean mFriendSecurity;

    public Setting() {
    mSetLoop = false;
    mFriendSecurity = false;
    }

    public Date getWorkPeriod() {
        return mWorkPeriod;
    }

    public void setWorkPeriod(Date workPeriod) {
        mWorkPeriod = workPeriod;
    }

    public Date getRestPeriod() {
        return mRestPeriod;
    }

    public void setRestPeriod(Date restPeriod) {
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
