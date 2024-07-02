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
public class NinthActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sm;
    private Sensor mMagnet, mOrient, mProxy;
    private TextView tv5, tv6, tv7;
    private float currentX, currentY, currentZ,proxy;
    private boolean isMagnetSensorAvailable, isOrientationSensorAvailable, isProximitySensorAvailable, itIsNotFirstTime = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ninth);
        ImageButton ib = (ImageButton) findViewById(R.id.imageButton2);
        ib.setOnClickListener((v) -> {
            startActivity(new Intent(NinthActivity.this, SecondActivity.class));
        });

        tv7 = (TextView) findViewById(R.id.textView7);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        mMagnet = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            sm.registerListener(NinthActivity.this, mMagnet, SensorManager.SENSOR_DELAY_NORMAL);
            isMagnetSensorAvailable = true;
        } else {
            tv7.setText("not available");
            isMagnetSensorAvailable = false;
        }

        //====proximity-------
        tv5 = (TextView) findViewById(R.id.textView5);
        mProxy = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (mProxy != null) {
            sm.registerListener(NinthActivity.this, mProxy, SensorManager.SENSOR_DELAY_NORMAL);
            isProximitySensorAvailable = true;
        } else {
            tv5.setText("not available");
            isProximitySensorAvailable = false;
        }







        //====orientation-------
        tv6 = (TextView) findViewById(R.id.textView6);
        mOrient = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        if (mOrient != null) {
            sm.registerListener(NinthActivity.this, mOrient, SensorManager.SENSOR_DELAY_NORMAL);
            isOrientationSensorAvailable = true;
        } else {
            tv5.setText("not available");
            isOrientationSensorAvailable = false;
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        if(sensor.getType() == Sensor.TYPE_PROXIMITY)  {
            tv5.setText("Proximity:" + "\n" + "\t\t Value->" + event.values[0] + "cm" );
                proxy = event.values[0];


        } else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
                tv7.setText("Magnetometer:" + "\n" + "\t\t Along X->" + event.values[0] + "m/s2" + "\n" + "\t\t Along Y->" + event.values[1] + "m/s2" + "\n" + "\t\t Along Z->" + event.values[2] + "m/s2");
            currentX = event.values[0];
            currentY = event.values[1];
            currentZ = event.values[2];

            }else if (sensor.getType() == Sensor.TYPE_ORIENTATION){

            int degree= Math.round(event.values[0]);
            int degree2= Math.round(event.values[1]);
            int degree3= Math.round(event.values[2]);
            tv6.setText("Orientation:" + "\n" + "\t\t Along X->" + degree+"°"+"\n"+"\t\t Along Y->"+degree2+"°"+"\n"+"\t\t Along Z->"+degree3+"°");

        }

    }
        @Override
        public void onAccuracyChanged (Sensor sensor,int accuracy){

        }
    }
