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
public class TenthActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sm;
    private Sensor mAir, mHumidity, mTemp;
    private TextView tv5, tv6, tv7;
    private boolean isAirSensorAvailable, isHumiditySensorAvailable, isTempSensorAvailable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tenth);

        ImageButton ib = (ImageButton) findViewById(R.id.imageButton2);
        ib.setOnClickListener((v) -> {
            startActivity(new Intent(TenthActivity.this, SecondActivity.class));
        });

        tv5 = (TextView) findViewById(R.id.textView5);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        mAir = sm.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (sm.getDefaultSensor(Sensor.TYPE_PRESSURE) != null) {
            sm.registerListener(TenthActivity.this, mAir, SensorManager.SENSOR_DELAY_NORMAL);
            isAirSensorAvailable = true;
        } else {
            tv5.setText("Air Pressure: "+"\n"+"\t\t Sensor not available");
            isAirSensorAvailable = false;
        }

        //====Humidity-------
        tv6 = (TextView) findViewById(R.id.textView6);
        mHumidity = sm.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if (mHumidity != null) {
            sm.registerListener(TenthActivity.this, mHumidity, SensorManager.SENSOR_DELAY_NORMAL);
            isHumiditySensorAvailable = true;
        } else {
            tv6.setText("Humidity: "+"\n"+"\t\t Sensor not available");
            isHumiditySensorAvailable = false;
        }

        //====temperature-------
        tv7 = (TextView) findViewById(R.id.textView7);
        mTemp = sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (mTemp != null) {
            sm.registerListener(TenthActivity.this, mTemp, SensorManager.SENSOR_DELAY_NORMAL);
            isTempSensorAvailable = true;
        } else {
            tv7.setText("Ambient Temperature: "+"\n"+"\t\t Sensor not available");
            isTempSensorAvailable = false;
        }



    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        if (sensor.getType() == Sensor.TYPE_PRESSURE) {
            tv5.setText("Air Pressure:" + "\n" + "\t\t Value->" + event.values[0]);
        }else if (sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            tv6.setText("Temperature:" + "\n" + "\t\t Value->" + event.values[0]);

        }else if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            tv7.setText("Temperature:" + "\n" + "\t\t Value->" + event.values[0]);

        }

    }
    @Override
    public void onAccuracyChanged (Sensor sensor,int accuracy){

    }
}