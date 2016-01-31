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
import android.widget.ImageView;
import android.widget.TextView;

import com.daseyffert.timeblock.R;

import java.util.Collections;
import java.util.Comparator;
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

    //Setup the adapter vy getting all the information needed
    private void setUpAdapter(){
        Intent startUpIntent = new Intent(Intent.ACTION_MAIN);
        startUpIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager pm = getActivity().getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(startUpIntent, 0);

        Collections.sort(activities, new Comparator<ResolveInfo>() {
            @Override
            public int compare(ResolveInfo a, ResolveInfo b) {
                PackageManager packageManager = getActivity().getPackageManager();
                return String.CASE_INSENSITIVE_ORDER.compare(a.loadLabel(packageManager).toString(), b.loadLabel(packageManager).toString());
            }
        });

        Log.i(TAG, "Found " + activities.size() + " activities.");
        mRecyclerView.setAdapter(new ApplicationAdapter(activities));
    }

    private class ApplicationHolder extends RecyclerView.ViewHolder {

        private ResolveInfo mResolveInfo;
        private ImageView mAppImageView;
        private CheckBox mCheckBox;
        private TextView mAppTextView;

        public ApplicationHolder(View itemView) {
            super(itemView);
            mAppImageView = (ImageView) itemView.findViewById(R.id.application_list_selection_image);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.application_list_selection_check);
            mAppTextView = (TextView) itemView.findViewById(R.id.applications_list_selection_text);
        }

        public void bindActivity(ResolveInfo resolveInfo) {
            mResolveInfo = resolveInfo;
            PackageManager pm = getActivity().getPackageManager();
            String appName = mResolveInfo.loadLabel(pm).toString();
            mAppTextView.setText(appName);
            mAppImageView.setImageDrawable(mResolveInfo.loadIcon(pm));
        }
    }

    private class ApplicationAdapter extends RecyclerView.Adapter<ApplicationHolder> {
        private final List<ResolveInfo> mApplications;

        private ApplicationAdapter(List<ResolveInfo> applications) {
            mApplications = applications;
        }

        @Override
        public ApplicationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.application_list_selection, parent, false);
            return new ApplicationHolder(view);
        }

        @Override
        public void onBindViewHolder(ApplicationHolder holder, int position) {
            ResolveInfo resolveInfo = mApplications.get(position);
            holder.bindActivity(resolveInfo);
        }

        @Override
        public int getItemCount() {
            return mApplications.size();
        }
    }
}