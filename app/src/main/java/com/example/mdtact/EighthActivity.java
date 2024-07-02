package com.example.mdtact;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class EighthActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager sm;
    private Sensor accelerometerSensor, mGyro, mGravity;
    private TextView tv5, tv6, tv7;
    private float currentX, currentY, currentZ, gyroX, gyroY, gyroZ, grX, grY, grZ;
    private boolean isAccelerometerSensorAvailable, isGyroSensorAvailable, isGravitySensorAvailable, itIsNotFirstTime = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_eighth);
        ImageButton ib=(ImageButton)findViewById(R.id.imageButton2) ;
        ib.setOnClickListener((v)->{
            startActivity(new Intent(EighthActivity.this,SecondActivity.class));
        });

        tv5 = (TextView) findViewById(R.id.textView5);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        accelerometerSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            sm.registerListener(EighthActivity.this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
            isAccelerometerSensorAvailable = true;
        } else {
            tv5.setText("not available");
            isAccelerometerSensorAvailable = false;
        }

        //====gyroscope-------
        tv6 = (TextView) findViewById(R.id.textView6);
        mGyro = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (mGyro != null) {
            sm.registerListener(EighthActivity.this, mGyro, SensorManager.SENSOR_DELAY_NORMAL);
            isGyroSensorAvailable = true;
        } else {
            tv6.setText("not available");
            isGyroSensorAvailable = false;
        }

//====gravity-------
        tv7 = (TextView) findViewById(R.id.textView7);
        mGravity = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        if (mGyro != null) {
            sm.registerListener(EighthActivity.this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
            isGravitySensorAvailable = true;
        } else {
            tv7.setText("not available");
            isGravitySensorAvailable = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            tv5.setText("Accelerometer:" + "\n" + "\t\t Along X->" + event.values[0] + "m/s2" + "\n" + "\t\t Along Y->" + event.values[1] + "m/s2" + "\n" + "\t\t Along Z->" + event.values[2] + "m/s2");
            currentX = event.values[0];
            currentY = event.values[1];
            currentZ = event.values[2];
        } else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            tv6.setText("Gyroscope:" + "\n" + "\t\t Along X->" + event.values[0] + "m/s2" + "\n" + "\t\t Along Y->" + event.values[1] + "m/s2" + "\n" + "\t\t Along Z->" + event.values[2] + "m/s2");
            gyroX = event.values[0];
            gyroY = event.values[1];
            gyroZ = event.values[2];
        } else if (sensor.getType() == Sensor.TYPE_GRAVITY) {
            tv7.setText("Gravity:" + "\n" + "\t\t Along X->" + event.values[0] + "m/s2" + "\n" + "\t\t Along Y->" + event.values[1] + "m/s2" + "\n" + "\t\t Along Z->" + event.values[2] + "m/s2");
            grX = event.values[0];
            grY = event.values[1];
            grZ = event.values[2];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

