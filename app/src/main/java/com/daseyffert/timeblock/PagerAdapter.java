package com.daseyffert.timeblock;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.daseyffert.timeblock.ApplicationTabs.Apps.AppsListFragment;
import com.daseyffert.timeblock.ApplicationTabs.TimeBlock.Setup;
import com.daseyffert.timeblock.ApplicationTabs.List.ToDoList;

/**
 * Created by Daniel on 12/21/2015.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;
    private AppsListFragment mTab1;
    private Setup mTab2;
    private ToDoList mTab3;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    /**
     * Create proper Fragment in proper position
     * @param position of what tab application is viewing
     * @return new proper Fragment instance of corresponding position
     */
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return mTab1 = new AppsListFragment();
            case 1:
                return mTab2 = new Setup();
            case 2:
                return mTab3 = new ToDoList();
            default:
                return null;
        }
    }

    /**
     * Get count of total tabs
     * @return total number of tabs
     */
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}