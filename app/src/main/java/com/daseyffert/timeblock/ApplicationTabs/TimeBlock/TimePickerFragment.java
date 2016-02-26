package com.daseyffert.timeblock.ApplicationTabs.TimeBlock;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import com.daseyffert.timeblock.R;


/**
 * Created by Daniel on 2/13/2016.
 */
public class TimePickerFragment extends DialogFragment {

//    public TimePickerFragment(){}
//
//    public static TimePickerFragment newInstance() {
//        return new TimePickerFragment();
//    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        TimePicker picker = new TimePicker(getContext());
        picker.setIs24HourView(true);

        //return new AlertDialog.Builder(getActivity(), R.style.AppTheme).setTitle("Choose time").setView(picker).setPositiveButton("Set", null).create();

        AlertDialog.Builder alertDB = new AlertDialog.Builder(getActivity(), R.style.AppDialogTheme);
        alertDB.setTitle("Select Time");
        alertDB.setView(picker);
        alertDB.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDB.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        return alertDB.create();

    }
}
