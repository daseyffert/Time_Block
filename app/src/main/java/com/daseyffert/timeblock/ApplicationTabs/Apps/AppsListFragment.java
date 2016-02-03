package com.daseyffert.timeblock.ApplicationTabs.Apps;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.daseyffert.timeblock.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Daniel on 12/21/2015.
 */
public class AppsListFragment extends Fragment {
    private static final String TAG = "AppsListFragment";

    private RecyclerView mRecyclerView;

    //Call this method when need of creating it
    public static AppsListFragment newInstance() {
        return new AppsListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate view with layout that has recycler view
        View view = inflater.inflate(R.layout.fragment_apps_list, container, false);
        //Wire up and set RecyclerView to Grid Layout
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_apps_list_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        //Setup adapter of RecyclerView
        setUpAdapter();

        return view;
    }


    //Setup the adapter by getting all the information needed
    private void setUpAdapter() {
        //List stores All applications installed on phone
        List<Applications> mApplications = new ArrayList<>();

        Intent startUpIntent = new Intent(Intent.ACTION_MAIN);
        startUpIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager pm = getActivity().getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(startUpIntent, 0);

        Collections.sort(activities, new ResolveInfo.DisplayNameComparator(pm));
        //Iterate through List type ResolveInfo and store key values into Objects
        for (ResolveInfo ri : activities) {
            //Applications info = new Applications(ri.activityInfo.name);
            Applications app = new Applications();
            //Update information to particular instance of Application
            app.setPackageName(ri.activityInfo.packageName);
            app.setName(ri.activityInfo.name);
            app.setApplicationLabel(ri.loadLabel(pm).toString());
            app.setApplicationIcon(ri.activityInfo.loadIcon(pm));
            //Log.d(TAG, "Adding name " + app.getApplicationLabel());

            //Add application to List
            mApplications.add(app);
        }

        //Log.i(TAG, "Found " + activities.size() + " activities.");
        //Set up Adapter of RecyclerView
        mRecyclerView.setAdapter(new ApplicationAdapter(mApplications));
    }

    //ViewHolder Class
    private class ApplicationHolder extends RecyclerView.ViewHolder {

        private Applications mApplication;
        private ImageView mAppImageView;
        private CheckBox mCheckBox;
        private TextView mAppTextView;

        //Wire up Views needed for each Application representation
        public ApplicationHolder(View itemView) {
            super(itemView);
            mAppImageView = (ImageView) itemView.findViewById(R.id.application_list_selection_image);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.application_list_selection_check);
            mAppTextView = (TextView) itemView.findViewById(R.id.applications_list_selection_text);
        }

        public void bindActivity(Applications application) {
            mApplication = application;

            //Set up Views with their necessary components
            mAppTextView.setText(mApplication.getApplicationLabel());
            mAppImageView.setImageDrawable(mApplication.getApplicationIcon());
            mCheckBox.setChecked(mApplication.isCheck());

            //Set up checkChangeListener to catch actions into object boolean
            mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    mApplication.setIsCheck(isChecked);
                }
            });
        }
    }

    //Adapter of RecyclerView Class
    private class ApplicationAdapter extends RecyclerView.Adapter<ApplicationHolder> {
        private final List<Applications> mApplications;

        private ApplicationAdapter(List<Applications> applications) {
            mApplications = applications;
        }

        @Override
        public ApplicationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //inflate the layout with how each RecyclerView looks
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.application_list_selection, parent, false);
            return new ApplicationHolder(view);
        }

        @Override
        public void onBindViewHolder(ApplicationHolder holder, int position) {
            //Extract position then bind components to View Holder
            Applications application = mApplications.get(position);
            holder.bindActivity(application);
        }

        @Override
        public int getItemCount() {
            return mApplications.size();
        }
    }
}