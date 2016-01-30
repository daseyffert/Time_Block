package com.daseyffert.timeblock.ApplicationTabs;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daseyffert.timeblock.R;

import java.util.List;

/**
 * Created by Daniel on 12/21/2015.
 */
public class AppsListFragment extends Fragment {
    private static final String TAG = "AppsListFragment";

    private RecyclerView mRecyclerView;

    public static AppsListFragment newInstance() {
        return new AppsListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_apps_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_apps_list_recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        setUpAdapter();

        return view;
    }

    private void setUpAdapter(){
        Intent startUpIntent = new Intent(Intent.ACTION_MAIN);
        startUpIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager pm = getActivity().getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(startUpIntent, 0);

        Log.i(TAG, "Found " +  activities.size() + " activities.");
    }

}