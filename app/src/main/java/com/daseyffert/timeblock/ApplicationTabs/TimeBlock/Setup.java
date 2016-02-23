package com.daseyffert.timeblock.ApplicationTabs.TimeBlock;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import com.daseyffert.timeblock.R;

import java.util.Date;

/**
 * Created by Daniel on 12/21/2015.
 */
public class Setup extends Fragment {

    private Button mTimeButton;

    private static final String DIALOG_TIME = "DialogTime";

    //Call this method when need of creating it
    public static Setup newInstance() {
        return new Setup();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_block_setup, container, false);


        //Get reference to mTimeButton and set date of crime
        mTimeButton = (Button) view.findViewById(R.id.fragment_time_block_setup_work_time);
//        updateTime();

        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = new TimePickerFragment();
                dialog.show(manager, "DialogTime");
            }
        });



        return view;
    }

    private void updateTime() {
        mTimeButton.setText(DateFormat.format("h:mm a", new Date()));
    }
}
