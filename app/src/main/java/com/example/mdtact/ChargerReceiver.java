package com.example.mdtact;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class ChargerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
        if (isCharging) {
            // Phone is charging
            Toast.makeText(context, "Phone is charging", Toast.LENGTH_SHORT).show();
        } else {
            // Phone is not charging
            Toast.makeText(context, "Phone is not charging", Toast.LENGTH_SHORT).show();
        }
    }
}