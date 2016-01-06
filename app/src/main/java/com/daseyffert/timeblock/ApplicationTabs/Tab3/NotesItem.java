package com.daseyffert.timeblock.ApplicationTabs.Tab3;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Daniel on 12/23/2015.
 */
public class NotesItem {

    private UUID mId;
    private String mTitle;
    private Date mDate;

    //Generate unique identifier and
    //Default mChecked to false
    public NotesItem(){
        mId = UUID.randomUUID();
        //mDate = new Date();
    }

    //Getters and Setters
    public UUID getId() {
        return mId;
    }
    public void setId(UUID id) {
        mId = id;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }
    public Date getDate() {
        return mDate;
    }
    public void setDate(Date date) {
        mDate = date;
    }
}
