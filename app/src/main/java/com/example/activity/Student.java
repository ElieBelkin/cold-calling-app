package com.example.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;

import java.util.Locale;

public class Student extends Application {

    private String mFirstName, mLastName;
    private int mTotalCount = 0; // total amount of times the student was called
    private int mCallCount = 0; // resets after 2 times in 40 minutes
    private long mTimeCall;

    public Student(String firstName, String lastName) {
        mFirstName = firstName;
        mLastName = lastName;
    }

    // name getters and setters
    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    //count getters and setters
    public int getTotalCount() {
        return mTotalCount;
    }

    public void setTotalCount(int totalCount) {
        mTotalCount = totalCount;
    }

    public int getCallCount() {
        return mCallCount;
    }

    public void setCallCount(int callCount) {
        mCallCount = callCount;
    }

    public void incrementCount() { mCallCount++; mTotalCount++; }

    //time getters and setters
    public long getTimeCall() {
        return mTimeCall;
    }

    public void setTimeCall(long timeCall) {
        mTimeCall = timeCall;
    }
}
