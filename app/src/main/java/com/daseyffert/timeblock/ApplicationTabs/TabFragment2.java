package com.daseyffert.timeblock.ApplicationTabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daseyffert.timeblock.R;

/**
 * Created by Daniel on 12/21/2015.
 */
public class TabFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_time_block_setup, container, false);
    }
}
