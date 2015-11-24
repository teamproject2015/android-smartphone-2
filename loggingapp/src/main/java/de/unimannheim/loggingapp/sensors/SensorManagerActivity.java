package de.unimannheim.loggingapp.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import de.unimannheim.loggingapp.BaseActivity;
import de.unimannheim.loggingapp.R;
import de.unimannheim.loggingapp.session.SessionManager;
import de.unimannheim.loggingapp.utils.Vector3;

/**
 * Created by suryadevara on 18-06-2015.
 * Adding all the sensor data from this file
 */
public class SensorManagerActivity extends BaseActivity implements SensorEventListener {
    //    private static final String TAG = MainActivity.class.getName();
    private static final float NS2S = 1.0f / 1000000000.0f;
    private static final float EPSILON = 0.000001f;
    private static final String CLASS_NAME = "KeyboardActivity";

    private final float[] deltaRotationVector = new float[4];
    protected SensorManager mSensorManager;
    protected Sensor mAccelerometer;
    protected Sensor mTemperature;
    protected Sensor mPressure;
    protected Sensor mLight;
    protected Sensor mMagneticField;
    protected Sensor mGravity;
    protected Sensor mGyroscope;
    Vector3 gravity;
    protected float[] magnitude = new float[3];
    //protected float[] acceleration = new float[3];
    protected float lux;
    protected float hPa;
    protected float[] orientation = new float[3];
    protected float timestamp;
    //protected float[] gyroscope = new float[3];
    protected StringBuffer logValues = new StringBuffer();
    protected String generatedKey;
    private float RTmp[] = new float[9];
    private float Rot[] = new float[9];
    private float I[] = new float[9];
    protected String data;
    static Random rnd = new Random();
    private char[] randomKeys;
    private int charCount;

    //Accelerometer data
    protected ArrayList<SensorData> accelerometerData = new ArrayList<SensorData>();
    protected ArrayList<SensorData> gravityData = new ArrayList<SensorData>();
    protected ArrayList<SensorData> magnitudeData = new ArrayList<SensorData>();
    protected ArrayList<SensorData> gyroscopeData = new ArrayList<SensorData>();
    protected ArrayList<SensorData> orientationData = new ArrayList<SensorData>();




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
        gravity = new Vector3();

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);

        mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mSensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_FASTEST);

        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_FASTEST);

        mTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_FASTEST);

        mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mSensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_FASTEST);

        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_FASTEST);

        mMagneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager.registerListener(this, mMagneticField, SensorManager.SENSOR_DELAY_FASTEST);


    }

    /**
     * This method generates the Key to press on the screen to monitor
     * the key stocks
     */
    protected void generateRandomKey(String randomLetters) {

        if (randomKeys == null || randomKeys.length == 0) {
            randomLetters = new String(new char[40]).replace("\0", randomLetters);
            //String value = String.valueOf(randomLetters.charAt(rnd.nextInt(randomLetters.length())));
            randomKeys = shuffleArray(randomLetters.toCharArray());
            //randomKeys = rnd.nextInt(26) + (byte)'a';
            //randomKeys = rnd.nextInt(10);
            charCount = 0;
        }
        //Log.i(CLASS_NAME, "Random key generated"+ new String(randomKeys));

        generatedKey = String.valueOf(randomKeys[charCount++]);
        if (randomKeys.length - 1 == charCount) {
            generatedKey = "All keys are finished";
        }


        /*if(keyCount==39 || keyCount==0) {


        }
        if(keyCount==39) {
            keyCount = 0;
        }
        keyCount++; */
        TextView generateKeyForTextView = (TextView) findViewById(R.id.textView_key);
        generateKeyForTextView.setText(generatedKey);
    }

    /*
     * Implementing Fisher–Yates shuffle
     */
    static char[] shuffleArray(char[] ar) {
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            char a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }

    /**
     * recursiveRandomStr method will remove the repeated characters
     * which are produced in Random character generation
     *
     * @param randomLetters
     * @return
     */
     /* private String recursiveRandomStr(String randomLetter) {

        if(!randomKeys.contains(randomLetter)) {
            randomKeys.add(randomLetter);
        } else {
            recursiveRandomStr(randomLetter);
        }
        return randomLetter;
    } */

    /**
     * When the User press/ touch the screen the event from OnTouchevent will be
     * triggering the onTouch method, the method will save the x,y coordinates
     * and accelerometer and orientation coordinates
     *
     * @param event keyevent
     * @return boolean value
     */
    protected boolean recordTouchEvent(MotionEvent event, String key,long downTime, long upTime) {

        StringBuilder keyStroke = new StringBuilder();

        /*if (logValues != null
                && !"".equals(logValues)) {
            keyStroke.append(logValues);
        } */

        /*if(keyCheck
                && generatedKey != null
                && !generatedKey.equals(key)) {
            return false;
        }*/

        //Appending the Data
        keyStroke.append(data);

        //Appending the key
        keyStroke.append(key).append(";");

        //multi touch pointers
        // get pointer index from the event object
        int pointerIndex = event.getActionIndex();

        // get masked (not specific to a pointer) action
        //int maskedAction = event.getActionMasked();


        //Keystroke for Co-ordinates
        keyStroke.append(event.getX(pointerIndex)).append(";").append(event.getY(pointerIndex)).append(";");
        //Testing the Value of pointers by Log
        //Log.d(CLASS_NAME; "X:" + event.getX(pointerIndex) + "Y:" + event.getY(pointerIndex));


        //Keystroke for taken Time/ms
        //long downTime = event.getDownTime();
        Log.d(CLASS_NAME, "downTime-->" + downTime + " upTime-->" + upTime);
        keyStroke.append(downTime).append(";");
        keyStroke.append(upTime).append(";");
        StringBuilder tempBuffer = new StringBuilder();
        tempBuffer.append(keyStroke);
        //keyStroke.append(sensorValues).append("\r\n");
        //sensorValues.setLength(0);

        //Only the first sensor value needs to be collected; so commented the below Code
        //Keystroke for gravity
       //keyStroke.append("Gravity").append(";").append(gravity.z).append(";").append(gravity.x).append(";").append(gravity.y).append("\r\n");
        populateSensorData("Gravity", gravityData, keyStroke, new StringBuilder(), downTime, upTime);
        //Keystroke for magnitude
        /*keyStroke.append(tempBuffer).append("Magnitude").append(";").append(magnitude[0]).append(";")
                .append(magnitude[1]).append(";").append(magnitude[2]).append("\r\n");*/
        populateSensorData("Magnitude", magnitudeData, keyStroke, tempBuffer, downTime, upTime);

        //Keystroke for Accelerometer
       //Log.i(CLASS_NAME, "accelerometerData-->" + accelerometerData.toString());
        populateSensorData("Acceleration",accelerometerData,keyStroke,tempBuffer,downTime,upTime);

        //Keystroke for orientation
        /*keyStroke.append(tempBuffer).append("Orientation").append(";").append(orientation[0]).append(";")
                .append(orientation[1]).append(";").append(orientation[2]).append("\r\n");*/
        populateSensorData("Orientation", orientationData, keyStroke, tempBuffer, downTime, upTime);

        /*keyStroke.append(tempBuffer).append("GyroScope").append(";").append(gyroscope[0]).append(";")
                .append(gyroscope[1]).append(";").append(gyroscope[2]).append("\r\n");*/
        populateSensorData("GyroScope", gyroscopeData, keyStroke, tempBuffer, downTime, upTime);


      /*  keyStroke.append(tempBuffer).append("RotationVector").append(",").append(rotationVector[0]).append(",")
                .append(rotationVector[1]).append(";").append(rotationVector[2]).append("\r\n");*/

        //Keystroke for Temperature
        //keyStroke.append(celsius).append(";");

        //Keystroke for Light
        keyStroke.append(tempBuffer).append("Light").append(";").append(lux).append(";").append(0).append(";").append(0).append("\r\n");
        //Keystroke for pressure
        keyStroke.append(tempBuffer).append("Pressure").append(";").append(hPa).append(";").append(0).append(";").append(0).append("\r\n");

        logValues.append(keyStroke);
        //Log.i(CLASS_NAME, "logValues------------->" + logValues);
        return true;
    }

    /**
     * Populate the SensorData to buffer
     * @param name
     * @param sensorData
     * @param keyStroke
     * @param tempBuffer
     * @param downTime
     * @param upTime
     */
    private void populateSensorData(String name,ArrayList<SensorData> sensorData,
                                    StringBuilder keyStroke,StringBuilder tempBuffer,long downTime,long upTime) {
        if (sensorData != null && sensorData.size() > 0) {
            //long t = accelerometerData.get(0).getTimestamp();
            StringBuilder xAxis = new StringBuilder();
            StringBuilder yAxis = new StringBuilder();
            StringBuilder zAxis = new StringBuilder();

            for (SensorData data : sensorData) {
                //Log.i(CLASS_NAME, "data-->" + data.toString());
                data.getTimestamp();
                if (data.getTimestamp() >= downTime) {
                    if (xAxis.length() > 0) {
                        xAxis.append(",");
                        yAxis.append(",");
                        zAxis.append(",");
                    }
                    xAxis.append(data.getX());
                    yAxis.append(data.getY());
                    zAxis.append(data.getZ());
                    if (data.getTimestamp() >= upTime) {
                        break;
                    }
                }
            }
            keyStroke.append(tempBuffer).append(name).append(";").append(xAxis.toString()).append(";")
                    .append(yAxis.toString()).append(";").append(zAxis.toString()).append("\r\n");
            sensorData.clear();
        }
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
        int type = event.sensor.getType();
        //sensorValues.setLength(0);
        if (type == Sensor.TYPE_GRAVITY) {
            // Isolate the force of gravity with the low-pass filter.
            // gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
            //gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
            //gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];
            gravity.z = event.values[0];
            gravity.x = event.values[1];
            gravity.y = event.values[2];
            long timestamp = SystemClock.elapsedRealtime();
            SensorData data = new SensorData(timestamp, event.values[0], event.values[1], event.values[2]);
            gravityData.add(data);

        } else if (type == Sensor.TYPE_ACCELEROMETER) {

            //acceleration[0] = event.values[0];
            //acceleration[1] = event.values[1];
            //acceleration[2] = event.values[2];
            long timestamp = SystemClock.elapsedRealtime();
            SensorData data = new SensorData(timestamp, event.values[0], event.values[1], event.values[2]);
            accelerometerData.add(data);
            //Log.i(CLASS_NAME, "accelerometerData-->" + accelerometerData.toString());
            /*gravity.z = averageList(acceleration[0]);
            gravity.x = averageList(acceleration[1]);
            gravity.y = averageList(acceleration[2]);*/

        } else if (type == Sensor.TYPE_MAGNETIC_FIELD) {

            magnitude[0] = event.values[0];
            magnitude[1] = event.values[1];
            magnitude[2] = event.values[2];
            long timestamp = SystemClock.elapsedRealtime();
            SensorData data = new SensorData(timestamp, event.values[0], event.values[1], event.values[2]);
            magnitudeData.add(data);

        } else if (type == Sensor.TYPE_ROTATION_VECTOR) {
            // This timestep's delta rotation to be multiplied by the current rotation
            // after computing it from the gyro sample data.
            if (timestamp != 0) {
                final float dT = (event.timestamp - timestamp) * NS2S;
                // Axis of the rotation sample, not normalized yet.
                float axisX = event.values[0];
                float axisY = event.values[1];
                float axisZ = event.values[2];

                // Calculate the angular speed of the sample
                float omegaMagnitude = (float) Math.sqrt(axisX * axisX + axisY * axisY + axisZ * axisZ);

                // Normalize the rotation vector if it's big enough to get the axis
                // (that is, EPSILON should represent your maximum allowable margin of error)
                if (omegaMagnitude > EPSILON) {
                    axisX /= omegaMagnitude;
                    axisY /= omegaMagnitude;
                    axisZ /= omegaMagnitude;
                }

                // Integrate around this axis with the angular speed by the timestep
                // in order to get a delta rotation from this sample over the timestep
                // We will convert this axis-angle representation of the delta rotation
                // into a quaternion before turning it into the rotation matrix.
                float thetaOverTwo = omegaMagnitude * dT / 2.0f;
                float sinThetaOverTwo = (float) Math.sin(thetaOverTwo);
                float cosThetaOverTwo = (float) Math.cos(thetaOverTwo);
                deltaRotationVector[0] = sinThetaOverTwo * axisX;
                deltaRotationVector[1] = sinThetaOverTwo * axisY;
                deltaRotationVector[2] = sinThetaOverTwo * axisZ;
                deltaRotationVector[3] = cosThetaOverTwo;
            }
            timestamp = event.timestamp;
            float[] deltaRotationMatrix = new float[9];
            SensorManager.getRotationMatrixFromVector(deltaRotationMatrix, deltaRotationVector);
            // User code should concatenate the delta rotation we computed with the current rotation
            // in order to get the updated rotation.
            // rotationCurrent = rotationCurrent * deltaRotationMatrix;


        } else if (type == Sensor.TYPE_GYROSCOPE) {

            //gyroscope = event.values.clone();
            long timestamp = SystemClock.elapsedRealtime();
            SensorData data = new SensorData(timestamp, event.values[0], event.values[1], event.values[2]);
            gyroscopeData.add(data);


        } /*else if (type == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            celsius = event.values[0];
            sensorValues.append("Temperature").append(",").append(celsius).append(",");

        } */ else if (type == Sensor.TYPE_PRESSURE) {
            hPa = event.values[0];
        } else if (type == Sensor.TYPE_LIGHT) {
            lux = event.values[0];

        } else {
            return;
        }

        if (gravity != null && magnitude != null) {
            SensorManager.getRotationMatrix(RTmp, I, gravity.get(), magnitude);

            Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            int rotation = display.getRotation();

            if (rotation == 1) {
                SensorManager.remapCoordinateSystem(RTmp, SensorManager.AXIS_X, SensorManager.AXIS_MINUS_Z, Rot);
            } else {
                SensorManager.remapCoordinateSystem(RTmp, SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_Z, Rot);
            }

            SensorManager.getOrientation(Rot, orientation);
            long timestamp = SystemClock.elapsedRealtime();
            SensorData data = new SensorData(timestamp, orientation[0], orientation[1], orientation[2]);
            orientationData.add(data);
            /*orientation[0] = (float) (((results[0] * 180) / Math.PI) + 180); //azimuth
            //Positive Roll is defined when the phone starts by laying flat
            // on a table and the positive Z-axis begins to tilt towards the positive X-axis.
            orientation[1] = (float) (((results[1] * 180 / Math.PI)) + 90); //pitch
            //Positive Pitch is defined when the phone starts by laying flat
            // on a table and the positive Z-axis begins to tilt towards the positive Y-axis.
            orientation[2] = (float) (((results[2] * 180 / Math.PI))); //roll*/
        }
    }

  /*  public List<Float> roll(List<Float> list, float newMember){
        if(list.size() == MAX_SAMPLE_SIZE){
            list.remove(0);
        }
        list.add(newMember);
        return list;
    }*//*

    public float averageList(List<Float> tallyUp) {

        float total = 0;
        for (float item : tallyUp) {
            total += item;
        }
        total = total / tallyUp.size();

        return total;
    }*/


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
        accelerometerData.clear();
        mSensorManager.unregisterListener(this);
    }

    protected void getBundleData() {
        Bundle bundle = getIntent().getExtras();
        StringBuilder temp = new StringBuilder();
        SessionManager session = new SessionManager(getApplicationContext());

        temp.append(session.getUserName()).append(";");
        temp.append(getBundle(bundle, "layout")).append(";");
        temp.append(getBundle(bundle, "orientation")).append(";");
        temp.append(getBundle(bundle, "variation")).append(";");
        temp.append(getBundle(bundle, "hardwareaddons")).append(";");
        temp.append(getBundle(bundle, "input")).append(";");
        temp.append(getBundle(bundle, "posture")).append(";");
        temp.append(getBundle(bundle, "externalfactors")).append(";");
        data = temp.toString();
    }

}
