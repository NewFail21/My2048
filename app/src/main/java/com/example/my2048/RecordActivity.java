package com.example.my2048;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RecordActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        listView = findViewById(R.id.record_listview);
        RecordDB recordDB = new RecordDB(this);
        ArrayList<Record> records = recordDB.selectAll();
        Collections.sort(records, new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o2.score - o1.score;
            }
        });

        RecordAdapter recordAdapter = new RecordAdapter(this, records);
        listView.setAdapter(recordAdapter);
    }
}
