package com.daseyffert.timeblock.ApplicationTabs.Apps;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.UUID;

/**
 * Created by Daniel on 1/30/2016.
 */
public class Applications {
    private String mPackageName;
    private UUID mId;
    private String mApplicationLabel;
    private Drawable mApplicationIcon;

    public void setId(UUID id) {
        mId = id;
    }

    private boolean mIsCheck;

    public Applications() {
        mId = UUID.randomUUID();
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
    public void setPackageName(String packageName) {
        mPackageName = packageName;
    }
    public Drawable getApplicationIcon() {
        return mApplicationIcon;
    }
    public void setApplicationIcon(Drawable applicationIcon) {
        mApplicationIcon = applicationIcon;
    }
    public UUID getId() {
        return mId;
    }
    public String getPackageName() {
        return mPackageName;
    }
}

