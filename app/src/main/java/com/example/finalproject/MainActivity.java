package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    private Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView basket = findViewById(R.id.imageView3);
        ObjectAnimator animation = ObjectAnimator.ofFloat(basket, "translationX", 100f);
        animation.setDuration(2000);
        animation.start();
    }
}