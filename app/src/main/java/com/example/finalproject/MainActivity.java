package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    private Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
    private ImageView imgMove;
    private Timer timer;
    private int direction = 0;
    private static int cycle = 20;
    Button start = findViewById(R.id.btnStart);
    TextView title = findViewById(R.id.gameId);
    TextView level = findViewById(R.id.level);
    TextView win = findViewById(R.id.winMessage);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                start.setVisibility(View.INVISIBLE);
                title.setVisibility(View.INVISIBLE);
                level.setVisibility(View.VISIBLE);
                win.setVisibility(View.INVISIBLE);
                moveCycle();
            }
        });
        SensorManager sensorManager =
                (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor rotationVectorSensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        // Create a listener
        SensorEventListener rvListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float[] rotationMatrix = new float[16];
                SensorManager.getRotationMatrixFromVector(
                        rotationMatrix, sensorEvent.values);
                // Remap coordinate system
                float[] remappedRotationMatrix = new float[16];
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_X,
                        SensorManager.AXIS_Z,
                        remappedRotationMatrix);

                // Convert to orientations
                float[] orientations = new float[3];
                SensorManager.getOrientation(remappedRotationMatrix, orientations);
                for(int i = 0; i < 3; i++) {
                    orientations[i] = (float)(Math.toDegrees(orientations[i]));
                }
                if(orientations[2] > 10) {
                    direction = 1;
//                    ObjectAnimator animation = ObjectAnimator.ofFloat(txtMove, "translationX", 100f);
//                    animation.setDuration(500);
//                    animation.start();
                } else if(orientations[2] < -10) {
                    direction = -1;
//                    ObjectAnimator animation = ObjectAnimator.ofFloat(txtMove, "translationX", -100f);
//                    animation.setDuration(500);
//                    animation.start();

                } else if(Math.abs(orientations[2]) < 10) {
                    direction = 0;
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                }
                System.out.println("ORIENTATION: " + orientations[2]);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        // Register it
        sensorManager.registerListener(rvListener,
                rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    }

    public void moveCycle() {
        int[] views = new int[]{R.id.imageView, R.id.imageView2, R.id.imgMove, R.id.imageView4,
                R.id.imageView5, R.id.imageView6, R.id.imageView7};
        views = randomizeArray(views);
        final ImageView im1 = findViewById(views[0]);
        final ImageView im2 = findViewById(views[1]);
        final ImageView im3 = findViewById(views[2]);
        final ImageView im4 = findViewById(views[3]);
        final ImageView im5 = findViewById(views[4]);
        final ImageView im6 = findViewById(views[5]);
        final ImageView im7 = findViewById(views[6]);

        final float startY = im1.getY();

        //1
        final Handler handler = new Handler();
        final Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                final ObjectAnimator animation = ObjectAnimator.ofFloat(im1, "translationY", startY, 1850);
                animation.setDuration(3000 - (cycle * 40));
                animation.start();
                String levelString = "Level " + (cycle + 1);
                level.setText(levelString);
                if (cycle < 25) {
                    handler.postDelayed(this, 9000 - (cycle * 240));
                }
            }
        };
        handler.postDelayed(runnableCode, 0);
        //2
        final Handler handler2 = new Handler();
        Runnable runnableCode2 = new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animation2 = ObjectAnimator.ofFloat(im2, "translationY", startY, 1850);
                animation2.setDuration(3000 - (cycle * 40));
                animation2.start();
                if (cycle < 25) {
                    handler2.postDelayed(this, 9000 - (cycle * 240));
                }
            }
        };
        handler2.postDelayed(runnableCode2, 1000);
        //3
        final Handler handler3 = new Handler();
        Runnable runnableCode3 = new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animation3 = ObjectAnimator.ofFloat(im3, "translationY", startY, 1850);
                animation3.setDuration(3000 - (cycle * 40));
                animation3.start();
                if (cycle < 25) {
                    handler3.postDelayed(this, 9000 - (cycle * 240));
                }
            }
        };
        handler3.postDelayed(runnableCode3, 2000);
        //4
        final Handler handler4 = new Handler();
        Runnable runnableCode4 = new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animation4 = ObjectAnimator.ofFloat(im4, "translationY", startY, 1850);
                animation4.setDuration(3000 - (cycle * 40));
                animation4.start();
                if (cycle < 25) {
                    handler4.postDelayed(this, 9000 - (cycle * 240));
                }
            }
        };
        handler4.postDelayed(runnableCode4, 3000);
        //5
        final Handler handler5 = new Handler();
        Runnable runnableCode5 = new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animation5 = ObjectAnimator.ofFloat(im5, "translationY", startY, 1850);
                animation5.setDuration(3000 - (cycle * 40));
                animation5.start();
                if (cycle < 25) {
                    handler5.postDelayed(this, 9000 - (cycle * 240));
                }
            }
        };
        handler5.postDelayed(runnableCode5, 4000);
        //6
        final Handler handler6 = new Handler();
        Runnable runnableCode6 = new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animation6 = ObjectAnimator.ofFloat(im6, "translationY", startY, 1850);
                animation6.setDuration(3000 - (cycle * 40));
                animation6.start();
                if (cycle < 25) {
                    handler6.postDelayed(this, 9000 - (cycle * 240));
                }
            }
        };
        handler6.postDelayed(runnableCode6, 5000);
        //7
        final Handler handler7 = new Handler();
        Runnable runnableCode7 = new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animation7 = ObjectAnimator.ofFloat(im7, "translationY", startY, 1850);
                animation7.setDuration(3000 - (cycle * 40));
                animation7.start();
                if (cycle < 25) {
                    handler7.postDelayed(this, 9000 - (cycle * 240));
                }
            }
        };
        handler7.postDelayed(runnableCode7, 6000);
        //reset
        final Handler handlerReset = new Handler();
        Runnable runnableCodeReset = new Runnable() {
            @Override
            public void run() {
                im1.setY(startY);
                im2.setY(startY);
                im3.setY(startY);
                im4.setY(startY);
                im5.setY(startY);
                im6.setY(startY);
                im7.setY(startY);
                cycle++;
                if (cycle >= 25)  {
                    level.setVisibility(View.INVISIBLE);
                    win.setVisibility(View.VISIBLE);
                    return;
                }
                if (cycle < 25) {
                    handlerReset.postDelayed(this, 9000 - (cycle * 240));
                }
            }
        };
        handlerReset.postDelayed(runnableCodeReset, 9000);
    }

    public static int[] randomizeArray(int[] array){
        Random rgen = new Random();  // Random number generator

        for (int i = 0; i < array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }
        return array;
    }
    public void move() {
        imgMove = findViewById(R.id.imgMove);
        //Declare the timer
        Timer t = new Timer();
        //Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {
                                  @Override
                                  public void run() {
                                      runOnUiThread(new Runnable() {

                                          @Override
                                          public void run() {
                                              if (direction == 1) {
                                                  imgMove.setX(imgMove.getX() + 1);
                                              }
                                              if (direction == -1) {
                                                  imgMove.setX(imgMove.getX() - 1);
                                              }
                                          }

                                      });
                                  }

                              },
                //Set how long before to start calling the TimerTask (in milliseconds)
                0,
                //Set the amount of time between each execution (in milliseconds)
                2);
    }
}