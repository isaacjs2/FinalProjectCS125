package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Rectangle;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    //private SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    //private Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
    private ImageView imgMove;
    private Timer timer;
    private int direction = 0;
    private static int cycle = 0;
    private int scoreCount = 0;
    private TextView score;
    private Button start;
    private TextView title;
    private TextView level;
    private TextView win;
    private ImageView stocking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = findViewById(R.id.score);
        start = findViewById(R.id.btnStart);
        title = findViewById(R.id.gameId);
        level = findViewById(R.id.level);
        win = findViewById(R.id.winMessage);
        stocking = findViewById(R.id.stocking);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                start.setVisibility(View.INVISIBLE);
                title.setVisibility(View.INVISIBLE);
                level.setVisibility(View.VISIBLE);
                win.setVisibility(View.INVISIBLE);
                move();
                moveCycle();
            }
        });
    }

    public void moveCycle() {
        int[] views = new int[]{R.id.ornament1, R.id.ornament2, R.id.ornament3, R.id.ornament4,
                R.id.ornament5, R.id.ornament6, R.id.ornament7};
        views = randomizeArray(views);
        final ImageView im1 = findViewById(views[0]);
        final ImageView im2 = findViewById(views[1]);
        final ImageView im3 = findViewById(views[2]);
        final ImageView im4 = findViewById(views[3]);
        final ImageView im5 = findViewById(views[4]);
        final ImageView im6 = findViewById(views[5]);
        final ImageView im7 = findViewById(views[6]);

        final float startY = im1.getY();

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
                //System.out.println("ORIENTATION: " + orientations[2]);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        // Register it
        sensorManager.registerListener(rvListener,
                rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);

        //1
        final Handler handler = new Handler();
        final Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                String levelString = "Level " + (cycle + 1);
                level.setText(levelString);
                //final ObjectAnimator animation = ObjectAnimator.ofFloat(im1, "translationY", startY, cycle + 1);
                //animation.setDuration(3000 - (cycle * 40));
                //animation.start();
                if (cycle % 2 == 0) {
                    moveOrnament(im1);
                }
                if (cycle < 25) {
                    handler.postDelayed(this, 8700 - (50 * cycle)/*9000 - (cycle * 240)*/);
                }

            }
        };
        handler.postDelayed(runnableCode, 0);
        //2
        final Handler handler2 = new Handler();
        final Runnable runnableCode2 = new Runnable() {
            @Override
            public void run() {
                //ObjectAnimator animation2 = ObjectAnimator.ofFloat(im2, "translationY", startY, 1850);
                //animation2.setDuration(3000 - (cycle * 40));
                //animation2.start();
                moveOrnament2(im2);
                if (cycle < 25) {
                    handler2.postDelayed(this, 8700 - (50 * cycle)/* - (cycle * 240)*/);//prob something other than 9000
                }
            }
        };
        handler2.postDelayed(runnableCode2, 1000);
        //3
        final Handler handler3 = new Handler();
        Runnable runnableCode3 = new Runnable() {
            @Override
            public void run() {
                //ObjectAnimator animation3 = ObjectAnimator.ofFloat(im3, "translationY", startY, 1850);
                //animation3.setDuration(3000 - (cycle * 40));
                //animation3.start();
                moveOrnament3(im3);
                if (cycle < 25) {
                    handler3.postDelayed(this, 8700 - (50 * cycle)/* - (cycle * 240)*/);
                }
            }
        };
        handler3.postDelayed(runnableCode3, 2000);
        //4
        final Handler handler4 = new Handler();
        Runnable runnableCode4 = new Runnable() {
            @Override
            public void run() {
                /*ObjectAnimator animation4 = ObjectAnimator.ofFloat(im4, "translationY", startY, 1850);
                animation4.setDuration(3000 - (cycle * 40));
                animation4.start();*/
                moveOrnament4(im4);
                if (cycle < 25) {
                    handler4.postDelayed(this, 8700 - (50 * cycle)/* - (cycle * 240)*/);
                }
            }
        };
        handler4.postDelayed(runnableCode4, 3000);
        //5
        final Handler handler5 = new Handler();
        Runnable runnableCode5 = new Runnable() {
            @Override
            public void run() {
                /*ObjectAnimator animation5 = ObjectAnimator.ofFloat(im5, "translationY", startY, 1850);
                animation5.setDuration(3000 - (cycle * 40));
                animation5.start();*/
                moveOrnament5(im5);
                if (cycle < 25) {
                    handler5.postDelayed(this, 8700 - (50 * cycle)/* - (cycle * 240)*/);
                }
            }
        };
        handler5.postDelayed(runnableCode5, 4000);
        //6
        final Handler handler6 = new Handler();
        Runnable runnableCode6 = new Runnable() {
            @Override
            public void run() {
                /*ObjectAnimator animation6 = ObjectAnimator.ofFloat(im6, "translationY", startY, 1850);
                animation6.setDuration(3000 - (cycle * 40));
                animation6.start();*/
                if (cycle != 1) {
                    moveOrnament6(im6);
                }
                if (cycle < 25) {
                    handler6.postDelayed(this, 8700 - (50 * cycle)/* - (cycle * 240)*/);
                }
            }
        };
        handler6.postDelayed(runnableCode6, 5000);
        //7
        final Handler handler7 = new Handler();
        Runnable runnableCode7 = new Runnable() {
            @Override
            public void run() {
                /*ObjectAnimator animation7 = ObjectAnimator.ofFloat(im7, "translationY", startY, 1850);
                animation7.setDuration(3000 - (cycle * 40));
                animation7.start();*/
                moveOrnament7(im7);
                if (cycle < 25) {
                    handler7.postDelayed(this, 8700 - (50 * cycle)/* - (cycle * 240)*/);
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
                im1.setVisibility(View.VISIBLE);
                im2.setY(startY);
                im2.setVisibility(View.VISIBLE);
                im3.setY(startY);
                im3.setVisibility(View.VISIBLE);
                im4.setY(startY);
                im4.setVisibility(View.VISIBLE);
                im5.setY(startY);
                im5.setVisibility(View.VISIBLE);
                im6.setY(startY);
                im6.setVisibility(View.VISIBLE);
                im7.setY(startY);
                im7.setVisibility(View.VISIBLE);
                cycle++;
                if (cycle >= 25)  {
                    level.setVisibility(View.INVISIBLE);
                    win.setVisibility(View.VISIBLE);
                    return;
                }
                if (cycle < 25) {
                    handlerReset.postDelayed(this, 8700 - (50 * cycle)/* - (cycle * 240)*/);
                }
            }
        };
        handlerReset.postDelayed(runnableCodeReset, 8700);
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
        imgMove = findViewById(R.id.stocking);
        //Declare the timer
        Timer t = new Timer();
        //Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {
                                  @Override
                                  public void run() {
                                      runOnUiThread(new Runnable() {

                                          @Override
                                          public void run() {
                                              if (imgMove.getX() <= -200) {
                                                  imgMove.setX(-198);
                                              }
                                              if (imgMove.getX() > 725) {
                                                  imgMove.setX(723);
                                              }
                                              if (direction == 1) {
                                                  imgMove.setX(imgMove.getX() + 2);
                                              }
                                              if (direction == -1) {
                                                  imgMove.setX(imgMove.getX() - 2);
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

    public void moveOrnament(final ImageView im1) {
        final Handler handler = new Handler();
        final Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                im1.setY(im1.getY() + 10 + cycle);

                //collision
                if (Math.abs(im1.getY() - stocking.getY()) < 100 && Math.abs((im1.getX() + 30) - (stocking.getX() + 200)) < 100 && im1.getVisibility() == View.VISIBLE) {
                    scoreCount++/*((cycle + 1) * 2)*/;
                    String x = "Score: " + scoreCount;
                    score.setText(x);
                    im1.setVisibility(View.INVISIBLE);
                }
                if (im1.getY() > 2025) {
                    return;
                }
                if (cycle < 25) {
                    handler.postDelayed(this, 1/*9000 - (cycle * 240)*/);
                }
            }
        };
        handler.postDelayed(runnableCode, 0);
    }
    public void moveOrnament2(final ImageView im2) {
        final Handler handler2 = new Handler();
        final Runnable runnableCode2 = new Runnable() {
            @Override
            public void run() {
                im2.setY(im2.getY() + 10 + cycle);

                //collision
                if (Math.abs(im2.getY() - stocking.getY()) < 100 && Math.abs((im2.getX() + 30) - (stocking.getX() + 200)) < 100 && im2.getVisibility() == View.VISIBLE) {
                    scoreCount++/*((cycle + 1) * 2)*/;
                    String x = "Score: " + scoreCount;
                    score.setText(x);
                    im2.setVisibility(View.INVISIBLE);
                }
                if (im2.getY() > 2025) {
                    return;
                }
                if (cycle < 25) {
                    handler2.postDelayed(this, 1/*9000 - (cycle * 240)*/);
                }
            }
        };
        handler2.postDelayed(runnableCode2, 0);
    }
    public void moveOrnament3(final ImageView im3) {
        //final ImageView im3 = findViewById(R.id.ornament3);
        final Handler handler3 = new Handler();
        final Runnable runnableCode3 = new Runnable() {
            @Override
            public void run() {
                im3.setY(im3.getY() + 10 + cycle);

                //collision
                if (Math.abs(im3.getY() - stocking.getY()) < 100 && Math.abs((im3.getX() + 30) - (stocking.getX() + 200)) < 100 && im3.getVisibility() == View.VISIBLE) {
                    scoreCount++/*((cycle + 1) * 2)*/;
                    String x = "Score: " + scoreCount;
                    score.setText(x);
                    im3.setVisibility(View.INVISIBLE);
                }
                if (im3.getY() > 2025) {
                    return;
                }
                if (cycle < 25) {
                    handler3.postDelayed(this, 1/*9000 - (cycle * 240)*/);
                }
            }
        };
        handler3.postDelayed(runnableCode3, 0);
    }
    public void moveOrnament4(final ImageView im4) {
        //final ImageView im4 = findViewById(R.id.ornament4);
        final Handler handler4 = new Handler();
        final Runnable runnableCode4 = new Runnable() {
            @Override
            public void run() {
                im4.setY(im4.getY() + 10 + cycle);

                //collision
                if (Math.abs(im4.getY() - stocking.getY()) < 100 && Math.abs((im4.getX() + 30) - (stocking.getX() + 200)) < 100 && im4.getVisibility() == View.VISIBLE) {
                    scoreCount++/*((cycle + 1) * 2)*/;
                    String x = "Score: " + scoreCount;
                    score.setText(x);
                    im4.setVisibility(View.INVISIBLE);
                }
                if (im4.getY() > 2025) {
                    return;
                }
                if (cycle < 25) {
                    handler4.postDelayed(this, 1/*9000 - (cycle * 240)*/);
                }
            }
        };
        handler4.postDelayed(runnableCode4, 0);
    }
    public void moveOrnament5(final ImageView im5) {
        //final ImageView im5 = findViewById(R.id.ornament5);
        final Handler handler5 = new Handler();
        final Runnable runnableCode5 = new Runnable() {
            @Override
            public void run() {
                im5.setY(im5.getY() + 10 + cycle);

                //collision
                if (Math.abs(im5.getY() - stocking.getY()) < 100 && Math.abs((im5.getX() + 30) - (stocking.getX() + 200)) < 100 && im5.getVisibility() == View.VISIBLE) {
                    scoreCount++/*((cycle + 1) * 2)*/;
                    String x = "Score: " + scoreCount;
                    score.setText(x);
                    im5.setVisibility(View.INVISIBLE);
                }
                if (im5.getY() > 2025) {
                    return;
                }
                if (cycle < 25) {
                    handler5.postDelayed(this, 1/*9000 - (cycle * 240)*/);
                }
            }
        };
        handler5.postDelayed(runnableCode5, 0);
    }
    public void moveOrnament6(final ImageView im6) {
        //final ImageView im6 = findViewById(R.id.ornament6);
        final Handler handler6 = new Handler();
        final Runnable runnableCode6 = new Runnable() {
            @Override
            public void run() {
                im6.setY(im6.getY() + 10 + cycle);

                //collision
                if (Math.abs(im6.getY() - stocking.getY()) < 100 && Math.abs((im6.getX() + 30) - (stocking.getX() + 200)) < 100 && im6.getVisibility() == View.VISIBLE) {
                    scoreCount++/*((cycle + 1) * 2)*/;
                    String x = "Score: " + scoreCount;
                    score.setText(x);
                    im6.setVisibility(View.INVISIBLE);
                }
                if (im6.getY() > 2025) {
                    return;
                }
                if (cycle < 25) {
                    handler6.postDelayed(this, 1/*9000 - (cycle * 240)*/);
                }
            }
        };
        handler6.postDelayed(runnableCode6, 0);
    }
    public void moveOrnament7(final ImageView im7) {
        //final ImageView im7 = findViewById(R.id.ornament7);
        final Handler handler7 = new Handler();
        final Runnable runnableCode7 = new Runnable() {
            @Override
            public void run() {
                im7.setY(im7.getY() + 10 + cycle);

                //collision
                if (Math.abs(im7.getY() - stocking.getY()) < 100 && Math.abs((im7.getX() + 30) - (stocking.getX() + 200)) < 100 && im7.getVisibility() == View.VISIBLE) {
                    scoreCount++/*((cycle + 1) * 2)*/;
                    String x = "Score: " + scoreCount;
                    score.setText(x);
                    im7.setVisibility(View.INVISIBLE);
                }
                if (im7.getY() > 2025) {
                    return;
                }
                if (cycle < 25) {
                    handler7.postDelayed(this, 1/*9000 - (cycle * 240)*/);
                }
            }
        };
        handler7.postDelayed(runnableCode7, 0);
    }
}