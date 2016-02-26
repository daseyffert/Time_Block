package com.daseyffert.timeblock.ApplicationTabs.TimeBlock;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TimePicker;

import com.daseyffert.timeblock.R;

import java.util.Date;

/**
 * Created by Daniel on 12/21/2015.
 */
public class Setup extends Fragment {

    private Setting mTimeBlockSettings;
    private Button mWorkTime;
    private Button mRestTime;
    private Switch mTimeLoop;
    private Switch mFriendSecurity;
    private Button mHelp;
    private Button mStart;
    private Button mStop;

    private static final String DIALOG_TIME = "DialogTime";

    //Call this method when need of creating it
    public static Setup newInstance() {
        return new Setup();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_block_setup, container, false);

        //Get reference to mTimeButton and set date of crime
        mWorkTime = (Button) view.findViewById(R.id.fragment_time_block_setup_work_time);
        mRestTime = (Button) view.findViewById(R.id.fragment_time_block_setup_rest_time);
//        mTimeLoop = (Switch) view.findViewById(R.id.fragment_time_block_setup_loop);
//        mFriendSecurity = (Switch) view.findViewById(R.id.fragment_time_block_setup_friend_security);
//        mHelp = (Button) view.findViewById(R.id.fragment_time_block_setup_security_help);
//        mStart = (Button) view.findViewById(R.id.fragment_time_block_setup_start);
//        mStop = (Button) view.findViewById(R.id.fragment_time_block_setup_stop);

        mTimeBlockSettings = new Setting();

        updateRestTime();
        updateWorkTime();

        mWorkTime.setOnClickListener(editWorkTimeOnClick);
        mRestTime.setOnClickListener(editRestTimeOnClick);



        return view;
    }

    private View.OnClickListener editWorkTimeOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            FragmentManager manager = getFragmentManager();
            TimePickerFragment dialog = new TimePickerFragment();

            dialog.show(manager, DIALOG_TIME);
        }
    };

    private View.OnClickListener editRestTimeOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            FragmentManager manager = getFragmentManager();
            TimePickerFragment dialog = new TimePickerFragment();
            dialog.show(manager, DIALOG_TIME);
        }
    };

    private void updateRestTime() {
        mRestTime.setText(DateFormat.format("h:mm a", new Date()));
    }

    private void updateWorkTime() {
        mWorkTime.setText(DateFormat.format("h:mm a", new Date()));
    }
}
