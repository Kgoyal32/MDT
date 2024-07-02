package com.example.mdtact;

import static android.telephony.TelephonyManager.NETWORK_TYPE_CDMA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EDGE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_0;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_A;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_B;
import static android.telephony.TelephonyManager.NETWORK_TYPE_GPRS;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSDPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSPAP;
import static android.telephony.TelephonyManager.NETWORK_TYPE_IDEN;
import static android.telephony.TelephonyManager.NETWORK_TYPE_LTE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_NR;
import static android.telephony.TelephonyManager.NETWORK_TYPE_UMTS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class SixthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sixth);
        ImageButton ib = (ImageButton) findViewById(R.id.imageButton2);
        ib.setOnClickListener((v) -> {
            startActivity(new Intent(SixthActivity.this, SecondActivity.class));
        });

        //Mobile network status code--
        TextView tv = (TextView) findViewById(R.id.textView2);
        boolean wifiConnected;
        boolean mobileConnected;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = cm.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected) {
                tv.setText("NetworkStatus:- Connected to Wi-fi");
            } else if (mobileConnected) {
                tv.setText("NetworkStatus:- Connected ");
            }
        } else {
            tv.setText("NetworkStatus:- Disconnected");
        }

        TextView tv5 = (TextView) findViewById(R.id.textView5);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PackageManager.PERMISSION_GRANTED);
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        switch (tm.getDataNetworkType()) {
            case NETWORK_TYPE_EDGE:
            case NETWORK_TYPE_GPRS:
            case NETWORK_TYPE_CDMA:
            case NETWORK_TYPE_IDEN:
                tv5.setText("NetworkType:- 2G");
                break;
            case NETWORK_TYPE_UMTS:
            case NETWORK_TYPE_HSDPA:
            case NETWORK_TYPE_HSPA:
            case NETWORK_TYPE_HSPAP:
            case NETWORK_TYPE_EVDO_0:
            case NETWORK_TYPE_EVDO_A:
            case NETWORK_TYPE_EVDO_B:
                tv5.setText("NetworkType:- 3G");
                break;
            case NETWORK_TYPE_LTE:
                tv5.setText("NetworkType:- 4G");
                break;
            case NETWORK_TYPE_NR:
                tv5.setText("NetworkType:- 5G");
                break;

            default:
                tv5.setText("NetworkType:- Unknown");
        }
//Roaming status

        TextView tv7=(TextView)findViewById(R.id.textView7) ;

        if (tm.isNetworkRoaming()) {
            // The phone is currently roaming
            tv7.setText("Roaming:- Roaming");
        } else {
            // The phone is not roaming
            tv7.setText("Roaming:- No Roaming");
        }
//signal strength code--
        TextView tv4 = (TextView)findViewById(R.id.textView4) ;
        PhoneStateListener phoneStateListener = new PhoneStateListener() {
            @Override
            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                super.onSignalStrengthsChanged(signalStrength);
                int signalStrengthValue = signalStrength.getGsmSignalStrength();
                // Do something with the signal strength value here
                tv4.setText("SignalStrength:- " + signalStrengthValue);
            }
        };

        tm.listen(phoneStateListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

//Service status code--
        TextView tv6 = (TextView)findViewById(R.id.textView6);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (tm.getServiceState().getState() == ServiceState.STATE_IN_SERVICE) {
                // The phone service is currently in service
                tv6.setText("ServiceStatus:- In Service");
            } else {
                // The phone service is not in service
                tv6.setText("ServiceStatus:- Not in Service");
            }
        }
// get instance of TelephonyManager
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        // get the name of the mobile network operator
        String networkOperatorName = tm.getNetworkOperatorName();
        TextView tvnew= (TextView) findViewById(R.id.textView3);
// print the name of the network operator
        tvnew.setText("NetworkOperator:- "+networkOperatorName);
    }




}