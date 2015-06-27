package de.unimannheim.loggingapp.touchlogger;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

import de.unimannheim.loggingapp.BaseActivity;
import de.unimannheim.loggingapp.MainActivity;
import de.unimannheim.loggingapp.R;

/**
 * Created by suryadevara on 18-06-2015.
 */
public class SensorManagerActivity extends BaseActivity implements SensorEventListener {
    private static final String TAG = MainActivity.class.getName();
    private static final float NS2S = 1.0f / 1000000000.0f;
    private static final float EPSILON = 0.000001f;
    private static final String CLASS_NAME = "KeyboardActivity";

    // In this example, alpha is calculated as t / (t + dT),
    // where t is the low-pass filter's time-constant and
    // dT is the event delivery rate.
    final float alpha = 0.8f;
    private final double[] deltaRotationVector = new double[4];
    protected SensorManager mSensorManager;
    protected Sensor mAccelerometer;
    protected Sensor mTemperature;
    protected Sensor mPressure;
    protected Sensor mLight;
    protected Sensor mMagneticField;
    protected Sensor mGravity;
    protected Sensor mGyroscope;
    protected float[] gravity = new float[3];
    protected float[] magnitude = new float[3];
    protected float[] linear_acceleration = new float[3];
    protected float celsius;
    protected float lux;
    protected float hPa;
    protected float[] orientation = new float[3];
    protected float results[] = new float[3];
    protected float timestamp;
    protected float[] downTime = new float[3];
    protected float[] upTime = new float[3];


    private float RTmp[] = new float[9];
    private float Rot[] = new float[9];
    private float I[] = new float[9];

    protected String logValues;
    protected String generatedKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        registerSensors();
    }

    /**
     * Registering all the required Sensors to the App
     */
    protected void registerSensors() {
        //List<Sensor> sensorsList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        Log.i(CLASS_NAME, "Initiating all the sensors required");
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);

        mTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);

        mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mSensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);

        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);

        mMagneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager.registerListener(this, mMagneticField, SensorManager.SENSOR_DELAY_NORMAL);

        mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mSensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
        Log.i(CLASS_NAME, "Initiated all the sensors required");
    }

    /**
     * This method generates the Key to press on the screen to monitor
     * the key stocks
     */
    protected void generateRandomKey(String randomLetters) {
        Random rnd = new Random();
        String key = String.valueOf(randomLetters.charAt(rnd.nextInt(randomLetters.length())));
        TextView generateKeyForTextView = (TextView) findViewById(R.id.textView_key);
        generateKeyForTextView.setText(key);
        generatedKey = key;
    }

    /**
     * When the User press/ touch the screen the event from OnTouchevent will be
     * triggering the onTouch method, the method will save the x,y coordinates
     * and accelerometer and orientation coordinates
     *
     * @param event keyevent
     * @return boolean value
     */
    protected String recordTouchEvent(MotionEvent event) {

            StringBuilder keyStroke = new StringBuilder();

            if (logValues != null
                    && !"".equals(logValues)) {
                keyStroke.append(logValues);
            }
            keyStroke.append("Key:").append(generatedKey).append(",");
            //multi touch pointers
            // get pointer index from the event object
            int pointerIndex = event.getActionIndex();

            // get masked (not specific to a pointer) action
            int maskedAction = event.getActionMasked();

            switch (maskedAction) {

                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN: {
                    // We have a new pointer. Lets add it to the list of pointers
                    keyStroke.append(event.getX(pointerIndex) + "," + event.getY(pointerIndex));
                    keyStroke.append(",downTime:" + event.getDownTime());
                    //Testing the Value of pointers by toast
                    Toast.makeText(getApplicationContext(),
                            "X:" + event.getX(pointerIndex) + "Y:" + event.getY(pointerIndex), Toast.LENGTH_SHORT).show();
                    break;
                }

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL: {
                    keyStroke.append(",upTime:").append(event.getEventTime());
                    break;
                }
            }
            keyStroke.append("gravity:").append(gravity[0]).append(",").append(gravity[1]).append(",").append(gravity[2]).append(",");
            keyStroke.append("magnitude:").append(magnitude[0]).append(",").append(magnitude[1]).append(",").append(magnitude[2]).append(",");
            keyStroke.append("Accelerometer:").append(linear_acceleration[0]).append(",").append(linear_acceleration[1]).append(",").append(linear_acceleration[2]).append(",");
            keyStroke.append("orientation:").append(orientation[0]).append(",").append(orientation[1]).append(",").append(orientation[2]).append(",");
            keyStroke.append("Temperature:").append(celsius).append(",");
            keyStroke.append("Light:").append(lux).append(",");
            keyStroke.append("pressure:").append(hPa).append("\r\n");

            logValues = keyStroke.toString();
            Log.i(CLASS_NAME, "logValues------------->" + logValues);
        return logValues;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        /**
         * Value giving the total velocity of the gyroscope (will be high, when the device is moving fast and low when
         * the device is standing still). This is usually a value between 0 and 10 for normal motion. Heavy shaking can
         * increase it to about 25. Keep in mind, that these values are time-depended, so changing the sampling rate of
         * the sensor will affect this value!
         */
        double gyroscopeRotationVelocity;

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            // Isolate the force of gravity with the low-pass filter.
            gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
            gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
            gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

            // Remove the gravity contribution with the high-pass filter.
            linear_acceleration[0] = event.values[0] - gravity[0];
            linear_acceleration[1] = event.values[1] - gravity[1];
            linear_acceleration[2] = event.values[2] - gravity[2];

        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            // Isolate the force of gravity with the low-pass filter.
            magnitude[0] = alpha * magnitude[0] + (1 - alpha) * event.values[0];
            magnitude[1] = alpha * magnitude[1] + (1 - alpha) * event.values[1];
            magnitude[2] = alpha * magnitude[2] + (1 - alpha) * event.values[2];

        } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            // This timestep's delta rotation to be multiplied by the current rotation
            // after computing it from the gyro sample data.
            if (timestamp != 0) {
                final float dT = (event.timestamp - timestamp) * NS2S;
                // Axis of the rotation sample, not normalized yet.
                float axisX = event.values[0];
                float axisY = event.values[1];
                float axisZ = event.values[2];

                // Calculate the angular speed of the sample
                gyroscopeRotationVelocity = Math.sqrt(axisX * axisX + axisY * axisY + axisZ * axisZ);

                // Normalize the rotation vector if it's big enough to get the axis
                if (gyroscopeRotationVelocity > EPSILON) {
                    axisX /= gyroscopeRotationVelocity;
                    axisY /= gyroscopeRotationVelocity;
                    axisZ /= gyroscopeRotationVelocity;
                }

                // Integrate around this axis with the angular speed by the timestep
                // in order to get a delta rotation from this sample over the timestep
                // We will convert this axis-angle representation of the delta rotation
                // into a quaternion before turning it into the rotation matrix.
                double thetaOverTwo = gyroscopeRotationVelocity * dT / 2.0f;
                double sinThetaOverTwo = Math.sin(thetaOverTwo);
                double cosThetaOverTwo = Math.cos(thetaOverTwo);
                deltaRotationVector[0] = sinThetaOverTwo * axisX;
                deltaRotationVector[1] = sinThetaOverTwo * axisY;
                deltaRotationVector[2] = sinThetaOverTwo * axisZ;
                deltaRotationVector[3] = cosThetaOverTwo;
            }
            timestamp = event.timestamp;

        } else if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            celsius = event.values[0];
        } else if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            hPa = event.values[0];
        } else if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            lux = event.values[0];
        } /*else if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            //Gravity is already measured in the Accelerometer
        } */ else {
            return;
        }

        if (gravity != null && magnitude != null) {
            SensorManager.getRotationMatrix(RTmp, I, gravity, magnitude);
            Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            int rotation = display.getRotation();

            if (rotation == 1) {
                SensorManager.remapCoordinateSystem(RTmp, SensorManager.AXIS_X, SensorManager.AXIS_MINUS_Z, Rot);
            } else {
                SensorManager.remapCoordinateSystem(RTmp, SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_Z, Rot);
            }

            SensorManager.getOrientation(Rot, results);
            //Azimuth is angle between the positive Y-axis and magnetic north
            // and its range is between 0 and 360 degrees.
            orientation[0] = (float) (((results[0] * 180) / Math.PI) + 180); //azimuth
            //Positive Roll is defined when the phone starts by laying flat
            // on a table and the positive Z-axis begins to tilt towards the positive X-axis.
            orientation[1] = (float) (((results[1] * 180 / Math.PI)) + 90); //pitch
            //Positive Pitch is defined when the phone starts by laying flat
            // on a table and the positive Z-axis begins to tilt towards the positive Y-axis.
            orientation[2] = (float) (((results[2] * 180 / Math.PI))); //roll
        }
    }



    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

}
