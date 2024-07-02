package com.example.mdtact;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fourth);
        ImageButton ib=(ImageButton)findViewById(R.id.imageButton2) ;
        ib.setOnClickListener((v)->{
            startActivity(new Intent(FourthActivity.this,SecondActivity.class));
        });

        TextView textview =(TextView)findViewById(R.id.textView17);
        String osVersion = "OS Version:- "+"\n"+
                Build.VERSION.RELEASE;
        textview.setText(osVersion);

        TextView textview2 =(TextView)findViewById(R.id.textView18);
        String modeln="Model Number:- "+"\n"+
                Build.MODEL;
        textview2.setText(modeln);


        TextView textview3 =(TextView)findViewById(R.id.textView19);
        String hardvs="Hardware Version:- "+"\n"+
                Build.HARDWARE;
        textview3.setText(hardvs);


         TextView textview4 =(TextView)findViewById(R.id.textView111);
        String n="Android update:- "+"\n"+
                Build.VERSION.SECURITY_PATCH;
        textview4.setText(n);

        TextView textview5 =(TextView)findViewById(R.id.textView267);
        String ct="Compile Time:- "+"\n"+
                Build.VERSION.RELEASE+
                Build.VERSION.INCREMENTAL;
        textview5.setText(ct);

        TextView textview6 =(TextView)findViewById(R.id.textView210);
        String kv="Kernel Version:- "+"\n"+System.getProperty("os.version");
        textview6.setText(kv);

        TextView textview7 =(TextView)findViewById(R.id.textView200);
        String bn="Build Number:- "+"\n"+
                Build.DISPLAY;
        textview7.setText(bn);

    }
}