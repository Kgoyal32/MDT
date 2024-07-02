package com.example.mdtact;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class SensorAvailable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_available);
        ImageButton ib=(ImageButton)findViewById(R.id.imageButton2) ;
        ib.setOnClickListener((v)->{
            startActivity(new Intent(SensorAvailable.this,SecondActivity.class));
        });
        Thread thread= new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(9999);
                }
                catch (Exception e){
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(SensorAvailable.this,SecondActivity.class);
                    startActivity(intent);

                }
            }
        };thread.start();



        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensorInfoBuilder = new StringBuilder();
        for (Sensor sensor : sensorList) {
            sensorInfoBuilder.append(sensor.getName()).append("\t\t\t"+"\n"); // Add sensor name

        }

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView sensorInfoTextView = findViewById(R.id.textView1);
        sensorInfoTextView.setText(sensorInfoBuilder.toString());

    }


}