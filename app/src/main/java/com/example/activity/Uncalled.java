package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Uncalled extends AppCompatActivity {

    private String[] uncalled;
    private ListView uncalledListView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uncalled);

        uncalled = findUncalled();

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listview_layout, uncalled);
        uncalledListView = (ListView) findViewById(R.id.uncalled_list_view);
        uncalledListView.setAdapter(adapter);

        backButton = (Button) findViewById(R.id.back_button_uncalled);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public String[] findUncalled() {
        int n = ((StudentArray) getApplication()).getUncalled().size();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            Student student = ((StudentArray) getApplication()).getUncalled().get(i);
            s[i] = student.getFirstName() + " " + student.getLastName();
        }
        return s;
    }
}