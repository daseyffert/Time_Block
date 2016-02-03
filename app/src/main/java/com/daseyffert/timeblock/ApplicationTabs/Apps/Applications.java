package com.daseyffert.timeblock.ApplicationTabs.Apps;

import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by Daniel on 1/30/2016.
 */
public class Applications {
    private String mName;
    private String mPackageName;
    private String mApplicationLabel;
    private Drawable mApplicationIcon;
    private boolean mIsCheck;

    public Applications() {
        mIsCheck = false;
    }

    public boolean isCheck() {
        return mIsCheck;
    }
    public void setIsCheck(boolean isCheck) {
        mIsCheck = isCheck;
    }
    public String getApplicationLabel() {
        return mApplicationLabel;
    }
    public void setApplicationLabel(String label) {
        mApplicationLabel = label;
    }
    public void setName(String name) {
        mName = name;
    }
    public void setPackageName(String packageName) {
        mPackageName = packageName;
    }
    public Drawable getApplicationIcon() {
        return mApplicationIcon;
    }
    public void setApplicationIcon(Drawable applicationIcon) {
        mApplicationIcon = applicationIcon;
    }
}

