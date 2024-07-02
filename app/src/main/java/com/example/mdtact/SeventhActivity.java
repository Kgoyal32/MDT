package com.example.mdtact;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import androidx.appcompat.app.AppCompatActivity;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SeventhActivity extends AppCompatActivity {

    private Camera camera;

    private TextView textView;
    private TextView textView3;
    private TextView textView4;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private AudioManager audioManager;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_seventh);

        ImageButton ib=(ImageButton)findViewById(R.id.imageButton2) ;
        ib.setOnClickListener((v)->{
            startActivity(new Intent(SeventhActivity.this,SecondActivity.class));
        });


        Button btnOpenCamera =(Button) findViewById(R.id.button);
        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                dispatchTakePictureIntent();
            }
        });



        textView = findViewById(R.id.textView2);

        // Check if the device has a camera
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            textView.setText("This device does not have a camera.");
            return;
        }

        // Open the default camera
        try {
            camera = Camera.open();
        } catch (Exception e) {
            textView.setText("Failed to open the camera.");
            e.printStackTrace();
            return;
        }

        // Test if the camera is working properly
        Camera.Parameters params = camera.getParameters();
        if (params == null) {
            textView.setText("Failed to get camera parameters.");
            camera.release();
            camera = null;
            return;
        }

        camera.release();
        camera = null;
        textView.setText("Camera is working properly");





        //---sound check--
        textView3 = findViewById(R.id.textView3);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Set a volume change listener to update the text view when the volume changes
        audioManager.setStreamVolume(
                AudioManager.STREAM_MUSIC, // The audio stream type to monitor
                audioManager.getStreamVolume(AudioManager.STREAM_MUSIC), // The initial volume
                AudioManager.FLAG_SHOW_UI // Show the volume change UI
        );
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0); // Reset the initial volume

        audioManager.setStreamVolume(
                AudioManager.STREAM_MUSIC, // The audio stream type to monitor
                audioManager.getStreamVolume(AudioManager.STREAM_MUSIC), // The initial volume
                AudioManager.FLAG_SHOW_UI // Show the volume change UI
        );
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0); // Reset the initial volume

        audioManager.setStreamVolume(
                AudioManager.STREAM_MUSIC, // The audio stream type to monitor
                audioManager.getStreamVolume(AudioManager.STREAM_MUSIC), // The initial volume
                AudioManager.FLAG_SHOW_UI // Show the volume change UI
        );
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0); // Reset the initial volume


        textView4 = findViewById(R.id.textView4);

        // Get the default display object
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

        // Get the display metrics
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        // Display the properties
        textView4.setText("Display properties:\n");
        textView4.append("\n"+"\t\t"+"Resolution: " + metrics.widthPixels + "x" + metrics.heightPixels + "\n");
        textView4.append("\t\t"+"Density: " + metrics.densityDpi + " dpi\n");
        textView4.append("\t\t"+"Rotation: " + display.getRotation() + "\n");


    }



    @Override
    protected void onPause() {
        super.onPause();

        // Release the camera when the activity is paused
        if (camera != null) {
            camera.release();
            camera = null;
        }

    }







    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // The picture was taken and saved to disk
            // You can retrieve the image data from the Intent's data parameter
        }
    }
    //--sound check--
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP || keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            updateVolumeStatus();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void updateVolumeStatus() {
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        textView3.setText(String.format("\t\t"+"Volume: %d/%d", currentVolume, maxVolume));
// Get the current ringer mode
        int ringerMode = audioManager.getRingerMode();
        switch (ringerMode) {
            case AudioManager.RINGER_MODE_NORMAL:
                textView3.append("\n"+"\t\t"+"Ringer mode: Normal");
                break;
            case AudioManager.RINGER_MODE_SILENT:
                textView3.append("\n"+"\t\t"+"Ringer mode: Silent");
                break;
            case AudioManager.RINGER_MODE_VIBRATE:
                textView3.append("\n"+"\t\t"+"Ringer mode: Vibrate");
                break;
        }

    }

}
