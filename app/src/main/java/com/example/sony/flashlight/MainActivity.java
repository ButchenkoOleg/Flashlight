package com.example.sony.flashlight;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton mToggleButton;

    public static Camera cam = null;// has to be static, otherwise onDestroy() destroys it

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        mToggleButton = (ToggleButton)findViewById(R.id.toggleButton);
    }


    public void flashLightOn(View view) {

        if (mToggleButton.isChecked()) {
            if (getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                cam = Camera.open();
                Camera.Parameters p = cam.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                cam.setParameters(p);
                cam.startPreview();
                Toast.makeText(MainActivity.this, "Flashlight is ON", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(MainActivity.this, "camera light is not available", Toast.LENGTH_SHORT).show();
        }else {
            if (getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                cam.stopPreview();
                cam.release();
                cam = null;
                Toast.makeText(MainActivity.this, "Flashlight is OFF", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(MainActivity.this, "camera light is not available", Toast.LENGTH_SHORT).show();
        }


    }
}
