package com.daseyffert.timeblock.ApplicationTabs.Tab3.SingleNote;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.daseyffert.timeblock.ApplicationTabs.Tab3.NotesItem;
import com.daseyffert.timeblock.ApplicationTabs.Tab3.NotesSingleton;
import com.daseyffert.timeblock.R;

import java.util.UUID;


/**
 * Created by Daniel on 12/23/2015.
 */
public class SingleNoteFragment extends Fragment {

    private static final String ARG_NOTE_ID = "note_id";

    private NotesItem mNotesItem;
    private EditText mTitleField;
    private EditText mDescriptionField;
    private CheckBox mPriority;

    /**
     * Create a method that creates new Instances of the Fragment
     * by first putting information into arguments then setting them
     * when we start the newInstance
     * @return a new Instance of BeatBoxFragment
     */
    public static SingleNoteFragment newInstance(UUID noteId) {
        //create Bundle and store noteId into arguments
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTE_ID, noteId);
        //make a new fragment and attach arguments to create new Instance
        SingleNoteFragment fragment =  new SingleNoteFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);

        //getActionBar().setDisplayHomeAsUpEnabled(true);

        //Retrieve id from particular note through extracting the
        // fragment's arguments then find it in the singleton
        UUID noteId = (UUID) getArguments().getSerializable(ARG_NOTE_ID);
        mNotesItem = NotesSingleton.get(getActivity()).getNotesItem(noteId);
    }

    /**
     * Create onCreateView of the Fragment where we wire up Views
     * @return the view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle onSavedInstanceState) {
        //inflate the View
        View view = inflater.inflate(R.layout.fragment_note_taking, container, false);
        //Wire up Widgets
        mTitleField = (EditText) view.findViewById(R.id.fragment_note_taking_title);
        mDescriptionField = (EditText) view.findViewById(R.id.fragment_note_taking_notes);
        //TODO uncomment once make the layout
        //mPriority = view.findViewById(R.id.fragment_note_taking_priority);

        //Set the objects to what the item pressed it
        mTitleField.setText(mNotesItem.getTitle());
        mDescriptionField.setText(mNotesItem.getDescription());


        //Make sure Title and Description update when the text is changed
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mNotesItem.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //intentionally left blank
            }
        });
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mNotesItem.setDescription(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //intentionally left blank
            }
        });



        return view;
    }
}
