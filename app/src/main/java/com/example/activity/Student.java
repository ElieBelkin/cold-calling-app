package com.example.activity;

import android.app.Application;

public class Student extends Application {

    private String mFirstName, mLastName;
    private int mTotalCount = 0; // total amount of times the student was called
    private int mCallCount = 0; // resets after 2 times in 40 minutes
    private long mRecentTime;

    public Student(String firstName, String lastName) {
        mFirstName = firstName;
        mLastName = lastName;
    }

    // name getters and setters
    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }


    //count getters and setters
    public int getTotalCount() {
        return mTotalCount;
    }

    public int getCallCount() {
        return mCallCount;
    }

    public void setCallCount(int callCount) {
        mCallCount = callCount;
    }

    public void incrementCount() { mCallCount++; mTotalCount++; }

    //time getters and setters
    public long getRecentTime() { return mRecentTime; }

    public void setRecentTime(long currentTime) { mRecentTime = currentTime; }
}
