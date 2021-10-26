package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Called extends ColdCallingActivity {

    private String[] called;
    private ListView calledListView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_called);

        called = findCalled();

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listview_layout, called);
        calledListView = (ListView) findViewById(R.id.called_list_view);
        calledListView.setAdapter(adapter);

        backButton = (Button) findViewById(R.id.back_button_called);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public String[] findCalled() {
        int n = ((StudentArray) getApplication()).getCalled().size();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            Student student = ((StudentArray) getApplication()).getCalled().get(i);
            s[i] = student.getFirstName() + " " + student.getLastName() + ", Times Called: " + student.getCallCount();
        }
        return s;
    }
}