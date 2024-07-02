package com.example.mdtact;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class FifthActivity extends AppCompatActivity {

    private TextView battery;

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
            battery.setText("Battery Status:- "+
                    "\n"+  "\t\t\t\t"  +   String.valueOf(level)+"%");
        }
    };
    private int BATTERY_PROPERTY_LAST_FULL_CHARGE_TIME;
    private int BATTERY_PROPERTY_HEALTH;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fifth);
        battery= (TextView) findViewById(R.id.textView20);
        this.registerReceiver(this.br,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        ImageButton ib=(ImageButton)findViewById(R.id.imageButton2) ;
        ib.setOnClickListener((v)->{
            startActivity(new Intent(FifthActivity.this,SecondActivity.class));
        });

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        boolean isPowerSaveMode = pm.isPowerSaveMode();
        // check if battery saver mode is enabled
        TextView tvbatt= (TextView) findViewById(R.id.textView100);
// print the battery saver status to the console
        if (isPowerSaveMode) {
            tvbatt.setText("Battery Saver:- "+ "\n"+"\t\t\t\t"+"On");
        } else {
            tvbatt.setText("Battery Saver:- "+"\n"+"\t\t\t\t"+ "Off");
        }
//----LAST CHARGE------
        // get instance of BatteryManager
        BatteryManager batteryManager = (BatteryManager) getSystemService(Context.BATTERY_SERVICE);

// get the time when the phone was last fully charged

        long lastFullChargeTime = batteryManager.getLongProperty(BATTERY_PROPERTY_LAST_FULL_CHARGE_TIME);
        long currentTime = System.currentTimeMillis();
        long hoursSinceLastFullCharge = (currentTime - lastFullChargeTime) / (1000 * 60 * 60);

        // format the hours as a human-readable string
        String formattedHours = "";
        if (hoursSinceLastFullCharge < 1) {
            formattedHours = "\n"+"\t\t\t\t"+"less than an hour ago";
        } else if (hoursSinceLastFullCharge == 1) {
            formattedHours = "\n"+"\t\t\t\t"+"1 hour ago";
        } else if (hoursSinceLastFullCharge == 2) {
            formattedHours = "\n"+"\t\t\t\t"+"2 hour ago";
        }
        else if (hoursSinceLastFullCharge == 3) {
            formattedHours = "\n"+"\t\t\t\t"+"3 hour ago";
        }
        else if (hoursSinceLastFullCharge == 4) {
            formattedHours = "\n"+"\t\t\t\t"+"4 hour ago";
        }
        else if (hoursSinceLastFullCharge == 5) {
            formattedHours = "\n"+"\t\t\t\t"+"5 hour ago";
        }else if (hoursSinceLastFullCharge == 6) {
            formattedHours = "\n"+"\t\t\t\t"+"6 hour ago";
        }
        else if (hoursSinceLastFullCharge == 7) {
            formattedHours = "\n"+"\t\t\t\t"+"7 hour ago";
        }else if (hoursSinceLastFullCharge == 8) {
            formattedHours = "\n"+"\t\t\t\t"+"8 hour ago";
        }else if (hoursSinceLastFullCharge == 9) {
            formattedHours = "\n"+"\t\t\t\t"+"9 hour ago";
        }else if (hoursSinceLastFullCharge == 10) {
            formattedHours = "\n"+"\t\t\t\t"+"10 hour ago";
        }else if (hoursSinceLastFullCharge == 11) {
            formattedHours = "\n"+"\t\t\t\t"+"11 hour ago";
        }else if (hoursSinceLastFullCharge == 12) {
            formattedHours = "\n"+"\t\t\t\t"+"12 hour ago";
        }
        else {
            formattedHours = hoursSinceLastFullCharge + " hours ago";
        }
        TextView tvlast = (TextView) findViewById(R.id.textView102);
// print the time since the last full charge to the console
        tvlast.setText("Last Charge:- " + formattedHours);

//SCREEN USAGE SINCE FULL CHARGE-------------
        // get instance of UsageStatsManager
        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);

// get the start and end time for the screen usage query
        long endTime = System.currentTimeMillis();
        long startTime = endTime - (1000 * 60 * 60); // look back one hour from the current time

// get the screen usage stats since the last full charge
        UsageEvents usageEvents = usageStatsManager.queryEvents(startTime, endTime);
        long totalScreenUsageTime = 0;
        UsageEvents.Event event = new UsageEvents.Event();
        while (usageEvents.hasNextEvent()) {
            usageEvents.getNextEvent(event);
            if (event.getEventType() == UsageEvents.Event.SCREEN_INTERACTIVE
                    || event.getEventType() == UsageEvents.Event.SCREEN_NON_INTERACTIVE) {
                totalScreenUsageTime += event.getTimeStamp();
            }
        }
        TextView tvchrg=(TextView)findViewById(R.id.textView4) ;
// format the total screen usage time as a human-readable string
        String formattedTime = String.format("%d minutes, %d seconds",
                TimeUnit.MILLISECONDS.toMinutes(totalScreenUsageTime),
                TimeUnit.MILLISECONDS.toSeconds(totalScreenUsageTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalScreenUsageTime)));

// print the screen usage since the last full charge to the console
        tvchrg.setText("Screen Usage Since Last Charge:- "+"\n"+"\t\t"+formattedTime);

//-------Background power consumption code-------
// get instance of PowerManager
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        TextView tvconsump=(TextView)findViewById(R.id.textView101) ;
// check if background consumption power management is enabled
        boolean isBackgroundPowerSavingEnabled = powerManager.isPowerSaveMode();

// print the background consumption power management status to the console
        if (isBackgroundPowerSavingEnabled) {
            tvconsump.setText("Power Management:- "+"\n"+"\t\t\t\t"+"enabled");
        } else {
            tvconsump.setText("Power Management:- "+"\n"+"\t\t\t\t"+"Disabled");
        }

//------code for APPS ARE RUNNING NORMALLY OR NOT---

        // get instance of BatteryManager
        batteryManager = (BatteryManager) getSystemService(Context.BATTERY_SERVICE);

// check if apps are running normally or not

        int batteryHealth = batteryManager.getIntProperty(BATTERY_PROPERTY_HEALTH);
        boolean isBatteryOkay = (batteryHealth == BatteryManager.BATTERY_HEALTH_GOOD);
        TextView tvrun=(TextView) findViewById(R.id.textView103);
// print the battery status to the console
        if (isBatteryOkay) {
            tvrun.setText("Apps are running normally:- "+"running normally");
        } else {
            tvrun.setText("Apps are running normally:- "+"not running normally");
        }
    }
    private ChargerReceiver mChargingReceiver = new ChargerReceiver();

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mChargingReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mChargingReceiver);
    }
}