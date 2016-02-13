package com.daseyffert.timeblock.ApplicationTabs.Apps;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
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

import com.daseyffert.timeblock.ApplicationTabs.Database.DBAdapter;
import com.daseyffert.timeblock.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by Daniel on 12/21/2015.
 */
public class AppsListFragment extends Fragment {
    private static final String TAG = "AppsListFragment";
    private static final String SAVEDLIST = "onSavedInstanceStateList";

    private RecyclerView mRecyclerView;
    //TODO figure out how put checked apps into list
    //TODO then use that list to access database
//    private DBAdapter mDatabaseSchema;
//    private SQLiteDatabase mSQLiteDatabase;
    //List stores All applications installed on phone
    public List<Applications> mApplications = new ArrayList<>();

    //Call this method when need of creating it
    public static AppsListFragment newInstance() {
        return new AppsListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize db and dbSchema
//        mDatabaseSchema = new DBAdapter(getActivity());
//        mSQLiteDatabase = mDatabaseSchema.mDatabaseHelper.getReadableDatabase();

        /**
         * Add stored tasks to List
         */
//        readDB();
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
            //app.setName(ri.activityInfo.name);
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

    //TODO everything below is part of TODO on top
//    private void readDB() {
//        //cursor contains all data in the database
//        Cursor cursor = mDatabaseSchema.storedTasks(mSQLiteDatabase);
////        NotesSingleton notesSingleton = NotesSingleton.get(getActivity());
//        mApplications = new ArrayList<>();
//
//
//        Applications application;
//
//        //Checks if cursor has any data
//        if (cursor.moveToFirst()) {
//            cursor.moveToFirst();
//
//            do{
//                //Creates new NotesItem object with task stored in databases
//                application = new Applications();
//                application.setId(UUID.fromString(cursor.getString(cursor.getColumnIndex("_id"))));
//                application.setApplicationLabel(cursor.getString(cursor.getColumnIndex("app_name")));
//
//                //Converts date that is stored in the database as text into Date object
////                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
////                try {
////
////                    notesItem.setDate(formatter.parse(cursor.getString(cursor.getColumnIndex("date"))));
////
////                } catch (ParseException e) {
////                    e.printStackTrace();
////                }
//
//                //Adds new NotesItem into List in NotesSingleton class
//                mApplications.add(application);
//                //continues until cursor is empty
//            }while(cursor.moveToNext());
//            //close cursor
//            cursor.close();
//        }
//        //close database
//        mSQLiteDatabase.close();
//    }
}