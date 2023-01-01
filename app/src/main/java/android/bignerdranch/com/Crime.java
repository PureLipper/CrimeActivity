package android.bignerdranch.com;


import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.*;

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private int mRequiresPolice;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
        mRequiresPolice = 0;
        mSolved = false;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDate() {
        return mDate.toString();
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public int getRequires() {
        return mRequiresPolice;
    }

    public void setRequiresPolice(int requiresPolice) {
        mRequiresPolice = requiresPolice;
    }

    ;
}
