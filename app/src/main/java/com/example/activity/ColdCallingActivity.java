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

import java.util.Calendar;

public class ColdCallingActivity extends AppCompatActivity {

    private TextView studentName;
    private ImageView studentPicture;
    private Button random, called, uncalled;
    protected StudentArray stArray;
    private Student[] students;
    private Context context;

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

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((StudentArray) getApplication()).getUncalled().isEmpty()) {

                } else {
                    boolean searchForStudent = true;
                    Student student = null;
                    while (searchForStudent) {
                        student = students[(int) (Math.random() * students.length)];
                        if (Calendar.getInstance().getTimeInMillis() - student.getTimeCall() > 240000) {
                            student.setCallCount(0);
                        }

                        if (student.getCallCount() != 2) {
                            searchForStudent = false;
                        }
                    }

                    if (student.getCallCount() == 0) {
                        student.setTimeCall(Calendar.getInstance().getTimeInMillis());
                    }

                    if (student.getTotalCount() == 0) {
                        ((StudentArray) getApplication()).getUncalled().remove(student);
                        ((StudentArray) getApplication()).getCalled().add(student);
                    }

                    student.incrementCount();

                    studentName.setText(student.getFirstName() + " " + student.getLastName());
                    String s = (student.getFirstName() + "_" + student.getLastName()).toLowerCase();
                    studentPicture.setImageDrawable(getDrawable(s));
                }
            }
        });

        called.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startCalled = new Intent(ColdCallingActivity.this, Called.class);
                startActivity(startCalled);
            }
        });

        uncalled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startUncalled = new Intent(ColdCallingActivity.this, Uncalled.class);
                startActivity(startUncalled);
            }
        });
    }

    public Drawable getDrawable(String name) {
        int resourceId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        return context.getResources().getDrawable(resourceId);
    }
}