package com.example.my2048;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class Settingsactivity extends AppCompatActivity {

    Button buttongraphics, buttoncontrol;
    ImageButton buttonback;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        buttongraphics = findViewById(R.id.graphics);
        buttoncontrol = findViewById(R.id.control);
        buttonback = findViewById(R.id.back);

        buttongraphics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.my2048.Graphicsactivity.class);
                startActivity(intent);
            }
        });

        buttoncontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Controlactivity.class);
                startActivity(intent);
            }
        });

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
