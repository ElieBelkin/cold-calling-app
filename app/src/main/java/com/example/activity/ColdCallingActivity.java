package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ColdCallingActivity extends AppCompatActivity {

    private TextView studentName;
    private ImageView studentPicture;
    private Button random, called, uncalled;
    private Student[] students;
    private Context context;
    private int tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentName = findViewById(R.id.student_name_text);
        studentPicture = findViewById(R.id.student_picture_image);

        random = findViewById(R.id.random_button);
        called = findViewById(R.id.called_button);
        uncalled = findViewById(R.id.uncalled_button);

        students = ((StudentArray) getApplication()).getStudents();
        context = studentPicture.getContext();

        tracker = 0;
        int total = students.length * 2;

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLists();
                if (tracker >= total) {
                    Toast.makeText(getApplicationContext(), "You've called on every student.", Toast.LENGTH_SHORT).show();
                } else {
                    boolean searchForStudent = true;
                    Student student = null;
                    long currentTime = Calendar.getInstance().getTimeInMillis();
                    while (searchForStudent) {
                        student = students[(int) (Math.random() * students.length)];
                        if (student.getCallCount() < 2) {
                            searchForStudent = false;
                        } else if (currentTime - student.getRecentTime() > 2400000){
                            student.setCallCount(0);
                            tracker-=2;
                            searchForStudent = false;
                        }
                    }

                    student.setRecentTime(currentTime);
                    if (student.getTotalCount() == 0) {
                        ((StudentArray) getApplication()).getUncalled().remove(student);
                        ((StudentArray) getApplication()).getCalled().add(student);
                    }
                    student.incrementCount();
                    tracker++;

                    studentName.setText(student.getFirstName() + " " + student.getLastName());
                    String s = (student.getFirstName() + "_" + student.getLastName()).toLowerCase();
                    studentPicture.setImageDrawable(getDrawable(s));
                }
            }
        });

        called.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLists();
                Intent startCalled = new Intent(ColdCallingActivity.this, Called.class);
                startActivity(startCalled);
            }
        });

        uncalled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLists();
                Intent startUncalled = new Intent(ColdCallingActivity.this, Uncalled.class);
                startActivity(startUncalled);
            }
        });
    }

    public Drawable getDrawable(String name) {
        int resourceId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        return context.getResources().getDrawable(resourceId);
    }

    public void updateLists() {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        ArrayList<Student> uncalled = ((StudentArray) getApplication()).getUncalled();
        ArrayList<Student> called = ((StudentArray) getApplication()).getCalled();
        for (Student student : students) {
            if (currentTime - student.getRecentTime() > 86400000 && !uncalled.contains(student)) {
                uncalled.add(student);
                if (called.contains(student)) {
                    called.remove(student);
                }
            }
        }
    }
}