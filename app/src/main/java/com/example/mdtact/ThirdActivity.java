package com.example.mdtact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_third);

        ImageButton ib=(ImageButton)findViewById(R.id.imageButton2) ;
        ib.setOnClickListener((v)->{
            startActivity(new Intent(ThirdActivity.this,SecondActivity.class));
        });

       TextView textview =(TextView)findViewById(R.id.textView17);
        String stringBuildModel ="Device Name:- "+"\n"+
                Build.DEVICE;
        textview.setText(stringBuildModel);

       TextView textview1 = (TextView)findViewById(R.id.textView18);
        String stringBuildModel1 ="Brand Name:- "+"\n"+
                Build.BRAND;
        textview1.setText(stringBuildModel1);

        TextView textview2 =(TextView)findViewById(R.id.textView19);
        String stringBuildModel2="Model Number:- "+"\n"+
                Build.MODEL;
        textview2.setText(stringBuildModel2);

        TextView textview3 =(TextView)findViewById(R.id.textView20);
        String stringBuildModel3 ="Display:- "+"\n"+
                Build.DISPLAY;
        textview3.setText(stringBuildModel3);

        TextView textview4 =(TextView)findViewById(R.id.textView21);
        String stringBuildModel4 ="Hardware:- "+"\n"+
                Build.HARDWARE;
        textview4.setText(stringBuildModel4);
    }
}