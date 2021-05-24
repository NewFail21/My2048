package com.example.my2048;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class RecordAdapter extends ArrayAdapter<Record> {
    public RecordAdapter(@NonNull Context context, List<Record> objects) {
        super(context, R.layout.record_adapter_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Record record = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.record_adapter_item, null);
        }

        TextView placeTextView = convertView.findViewById(R.id.place_tw);
        TextView scoreTextView = convertView.findViewById(R.id.score_tw);

        placeTextView.setText(String.valueOf(position + 1));
        scoreTextView.setText(String.valueOf(record.score));

        return convertView;
    }
}
