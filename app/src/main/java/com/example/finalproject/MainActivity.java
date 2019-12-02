package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class MainActivity extends AppCompatActivity {

    JsonParser jsonParser = new JsonParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    final TextView textView = (TextView) findViewById(R.id.text);
// ...

    // Instantiate the RequestQueue.
    /*RequestQueue queue = Volley.newRequestQueue(this);
    String url ="http://deckofcardsapi.com";

    // Request a string response from the provided URL.
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Display the first 500 characters of the response string.
                    textView.setText("Response is: "+ response.substring(0,500));
                    jsonParser.parse(stringRequest).getAsJsonObject(); //maybe?
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            textView.setText("That didn't work!");
        }
    });
    // Add the request to the RequestQueue.
    queue.add(stringRequest);

     */

    private SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    private Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
}

//idea
//use rotation sensor for tilt controls
//light up pieces of screen, die if user is not in area after certain time