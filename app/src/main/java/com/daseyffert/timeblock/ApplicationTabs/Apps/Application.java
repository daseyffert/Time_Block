package com.daseyffert.timeblock.ApplicationTabs.Apps;

/**
 * Created by Daniel on 1/30/2016.
 * Model Class for each Application
 */
public class Application {

    private String mApplicationName;
    private boolean mIsSelected;

    public Application() {
        mIsSelected = false;
    }

    //Generate Getter and Setter
    public String getApplicationName() {
        return mApplicationName;
    }
    public void setApplicationName(String applicationName) {
        mApplicationName = applicationName;
    }
    public boolean getIsSelected() {
        return mIsSelected;
    }
    public void setIsSelected(boolean isSelected) {
        mIsSelected = isSelected;
    }
}
