package com.example.mdtact;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView;
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_second);

        Spinner spinnerMob=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.mobileinfoandspec,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerMob.setAdapter(adapter);

        spinnerMob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Check Mobile Info")){
                         Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                         startActivity(intent);

                     }
                if(parent.getItemAtPosition(position).equals("Software And Hardware")){
                    Intent intent = new Intent(SecondActivity.this, FourthActivity.class);
                    startActivity(intent);

                }

                if(parent.getItemAtPosition(position).equals("Battery Condition")){
                    Intent intent = new Intent(SecondActivity.this, FifthActivity.class);
                    startActivity(intent);

                }
                if(parent.getItemAtPosition(position).equals("Network Signal Status")){
                    Intent intent = new Intent(SecondActivity.this, SixthActivity.class);
                    startActivity(intent);

                }
                if(parent.getItemAtPosition(position).equals("Camera,Sound and Display")){
                    Intent intent = new Intent(SecondActivity.this, SeventhActivity.class);
                    startActivity(intent);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Spinner spinnerMob2=(Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence>adapter2=ArrayAdapter.createFromResource(this,R.array.mobileinfoandspec2,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerMob2.setAdapter(adapter2);

        spinnerMob2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                if(parent.getItemAtPosition(position).equals("Sensors Available")){
                    Intent intent = new Intent(SecondActivity.this, SensorAvailable.class);
                    startActivity(intent);

                }
                if(parent.getItemAtPosition(position).equals("Motion")){
                    Intent intent = new Intent(SecondActivity.this, EighthActivity.class);
                    startActivity(intent);

                }

                if(parent.getItemAtPosition(position).equals("Position")){
                    Intent intent = new Intent(SecondActivity.this, NinthActivity.class);
                    startActivity(intent);

                }

                if(parent.getItemAtPosition(position).equals("Environment")){
                    Intent intent = new Intent(SecondActivity.this, TenthActivity.class);
                    startActivity(intent);

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner spinnerMob3=(Spinner)findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence>adapter3=ArrayAdapter.createFromResource(this,R.array.mobileinfoandspec3,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerMob3.setAdapter(adapter3);

        spinnerMob3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                if(parent.getItemAtPosition(position).equals("Call Logs")){
                    Intent intent = new Intent(SecondActivity.this, ElevenActivity.class);
                    startActivity(intent);

                }
                if(parent.getItemAtPosition(position).equals("Int. And Ext. Memory")){
                    Intent intent = new Intent(SecondActivity.this, TwelveActivity.class);
                    startActivity(intent);

                }
                if(parent.getItemAtPosition(position).equals("Report Generation")){
                    Intent intent = new Intent(SecondActivity.this, ThirteenActivity.class);
                    startActivity(intent);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}