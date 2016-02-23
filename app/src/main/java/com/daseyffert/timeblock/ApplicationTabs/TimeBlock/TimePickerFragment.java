package com.daseyffert.timeblock.ApplicationTabs.TimeBlock;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import com.daseyffert.timeblock.R;

/**
 * Created by Daniel on 2/13/2016.
 */
public class TimePickerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        TimePicker picker = new TimePicker(getContext());
        picker.setIs24HourView(true);

        return new AlertDialog.Builder(getActivity()).setTitle("Choose time").setView(picker).setPositiveButton("Set", null).create();
    }
}
