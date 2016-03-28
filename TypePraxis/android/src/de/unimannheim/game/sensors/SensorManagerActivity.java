package de.unimannheim.game.sensors;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import java.util.List;
import java.util.Random;

import de.unimannheim.game.BaseActivity;
import de.unimannheim.game.utils.Constants;

/**
 * Created by suryadevara on 18-06-2015.
 * Adding all the sensor data from this file
 */
public class SensorManagerActivity extends BaseActivity {

    //private static final String CLASS_NAME = "SensorManagerActivity";

    protected float lux;
    protected float hPa;

    protected StringBuffer logValues = new StringBuffer();
    protected String generatedKey;

    static Random rnd = new Random();
    private char[] randomKeys;
    private int charCount;

    protected SensorResultReceiver accelerometerResultReceiver;
    protected SensorResultReceiver orientationResultReceiver;
    protected SensorResultReceiver gyroscopeResultReceiver;
    protected SensorResultReceiver gravityResultReceiver;
    protected PressureResultReceiver pressureResultReceiver;
    protected LightResultReceiver lightResultReceiver;
    protected SensorResultReceiver magnitudeResultReceiver;

    protected String pressedKey;
    //long downTime, upTime;
    protected float coordinateX, coordinateY;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        startServices();
        registerSensors();
        pressedKey = "";
    }

    public void startServices() {

        // start accelerator service
        Intent accIntent = new Intent(this, AccelerometerService.class);
        accIntent.putExtra(Constants.EXTRA_RECEIVER, accelerometerResultReceiver);
        startService(accIntent);


        // start orientation service
        /*Intent orientationIntent = new Intent(this, OrientationService.class);
        orientationIntent.putExtra(Constants.EXTRA_RECEIVER, orientationResultReceiver);
        startService(orientationIntent);*/

        // start gyroscope service
        Intent gyroscopeIntent = new Intent(this, GyroscopeService.class);
        gyroscopeIntent.putExtra(Constants.EXTRA_RECEIVER, gyroscopeResultReceiver);
        startService(gyroscopeIntent);

        // start gravity service
        Intent gravityIntent = new Intent(this, GravityService.class);
        gravityIntent.putExtra(Constants.EXTRA_RECEIVER, gravityResultReceiver);
        startService(gravityIntent);


        // start pressure service
        Intent pressureIntent = new Intent(this, PressureService.class);
        pressureIntent.putExtra(Constants.EXTRA_RECEIVER, pressureResultReceiver);
        startService(pressureIntent);


        // start light service
        Intent lightIntent = new Intent(this, LightService.class);
        lightIntent.putExtra(Constants.EXTRA_RECEIVER, lightResultReceiver);
        startService(lightIntent);


        // start light service
        Intent magnitudeIntent = new Intent(this, MagnitudeService.class);
        magnitudeIntent.putExtra(Constants.EXTRA_RECEIVER, magnitudeResultReceiver);
        startService(magnitudeIntent);

    }

    /**
     * Registering all the required Sensors to the App
     */
    protected void registerSensors() {

        Handler handler = new Handler();

        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sm.getSensorList(Sensor.TYPE_ALL);
        for (int i = 0; i < sensorList.size(); i++) {
            int type = sensorList.get(i).getType();
            if (type == Sensor.TYPE_ACCELEROMETER) {
                accelerometerResultReceiver = new SensorResultReceiver(handler);
                accelerometerResultReceiver.setReceiver(new SensorDataReceiver());

            } else if (type == Sensor.TYPE_GRAVITY) {
                gravityResultReceiver = new SensorResultReceiver(handler);
                gravityResultReceiver.setReceiver(new SensorDataReceiver());

            } else if (type == Sensor.TYPE_GYROSCOPE) {
                gyroscopeResultReceiver = new SensorResultReceiver(handler);
                gyroscopeResultReceiver.setReceiver(new SensorDataReceiver());

            } else if (type == Sensor.TYPE_PRESSURE) {
                pressureResultReceiver = new PressureResultReceiver(handler);
                pressureResultReceiver.setReceiver(new PressureReceiver());

            } else if (type == Sensor.TYPE_LIGHT) {
                lightResultReceiver = new LightResultReceiver(handler);
                lightResultReceiver.setReceiver(new LightReceiver());

            } else if (type == Sensor.TYPE_MAGNETIC_FIELD) {
                magnitudeResultReceiver = new SensorResultReceiver(handler);
                magnitudeResultReceiver.setReceiver(new SensorDataReceiver());
            }
        }
        /*orientationResultReceiver = new SensorResultReceiver(handler);
        orientationResultReceiver.setReceiver(new SensorDataReceiver());*/
    }

    /**
     * This method generates the Key to press on the screen to monitor
     * the key stocks
     */
    protected void generateRandomKey(String randomLetters) {

        if (randomKeys == null || randomKeys.length == 0) {
            randomLetters = new String(new char[40]).replace("\0", randomLetters);
            randomKeys = shuffleArray(randomLetters.toCharArray());
            charCount = 0;
        }
        //Log.i(CLASS_NAME, "Random key generated"+ new String(randomKeys));

        generatedKey = String.valueOf(randomKeys[charCount++]);
        if (randomKeys.length - 1 == charCount) {
            generatedKey = "All keys are finished";
        }

        /*TextView generateKeyForTextView = (TextView) findViewById(R.id.textView_key);
        generateKeyForTextView.setText(generatedKey);*/
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
     */
     /* private String recursiveRandomStr(String randomLetter) {

        if(!randomKeys.contains(randomLetter)) {
            randomKeys.add(randomLetter);
        } else {
            recursiveRandomStr(randomLetter);
        }
        return randomLetter;
    } */
    protected void recordTouchEvent(float x, float y, String key,
                                    long downTime, long upTime) {

        this.pressedKey = key;
        this.coordinateX = x;
        this.coordinateY = y;
       // Log.i("Record", "Key=" + this.pressedKey + ",coordinateX=" + this.coordinateX +",coordinateY=" + this.coordinateY);
    }

    /**
     * When the User press/ touch the screen the event from OnTouchevent will be
     * triggering the onTouch method, the method will save the x,y coordinates
     * and accelerometer and orientation coordinates
     */
   /* protected boolean setBuffer() {

        StringBuilder keyStroke = new StringBuilder();

        //Appending the key
        keyStroke.append(key).append(";");

        //Keystroke for Co-ordinates
        keyStroke.append(coordinateX).append(";").append(coordinateY).append(";");
        //Testing the Value of pointers by Log
        //Log.d(CLASS_NAME; "X:" + event.getX(pointerIndex) + "Y:" + event.getY(pointerIndex));


        //Keystroke for taken Time/ms
        //long downTime = event.getDownTime();
        //Log.d(CLASS_NAME, "downTime-->" + downTime + " upTime-->" + upTime);
        //keyStroke.append(System.nanoTime()).append(";");
        //keyStroke.append(upTime).append(";");
        *//*StringBuilder tempBuffer = new StringBuilder();
        tempBuffer.append(keyStroke);*//*
        //keyStroke.append(sensorValues).append("\r\n");
        //sensorValues.setLength(0);

        //Only the first sensor value needs to be collected; so commented the below Code
        //Keystroke for gravity
        keyStroke.append(System.nanoTime()).append(";").append("Gravity").append(";").append(gravityData[0]).append(";")
                .append(gravityData[1]).append(";").append(gravityData[2]).append("\r\n");
        //populateSensorData("Gravity", gravityData, keyStroke, new StringBuilder(), downTime, upTime);
        //Keystroke for magnitude
        keyStroke.append(tempBuffer).append(System.nanoTime()).append(";").append("Magnitude").append(";").append(magnitudeData[0]).append(";")
                .append(magnitudeData[1]).append(";").append(magnitudeData[2]).append("\r\n");
        //populateSensorData("Magnitude", magnitudeData, keyStroke, tempBuffer, downTime, upTime);

        //Keystroke for Accelerometer
        //Log.i(CLASS_NAME, "accelerometerData-->" + accelerometerData.toString());
        keyStroke.append(tempBuffer).append(System.nanoTime()).append(";").append("Acceleration").append(";").append(accelerometerData[0]).append(";")
                .append(accelerometerData[1]).append(";").append(accelerometerData[2]).append("\r\n");
        //populateSensorData("Acceleration", accelerometerData, keyStroke, tempBuffer, downTime, upTime);

        //Keystroke for orientation
        *//*keyStroke.append("Orientation").append(";").append(orientationData[0]).append(";")
                .append(orientationData[1]).append(";").append(orientationData[2]).append("\r\n");*//*
        //populateSensorData("Orientation", orientationData, keyStroke, tempBuffer, downTime, upTime);

        keyStroke.append(tempBuffer).append(System.nanoTime()).append(";").append("GyroScope").append(";").append(gyroscopeData[0]).append(";")
                .append(gyroscopeData[1]).append(";").append(gyroscopeData[2]).append("\r\n");
        //populateSensorData("GyroScope", gyroscopeData, keyStroke, tempBuffer, downTime, upTime);


      *//*  keyStroke.append(tempBuffer).append("RotationVector").append(",").append(rotationVector[0]).append(",")
                .append(rotationVector[1]).append(";").append(rotationVector[2]).append("\r\n");*//*

        //Keystroke for Temperature
        //keyStroke.append(celsius).append(";");

        //Keystroke for Light
        keyStroke.append(tempBuffer).append(System.nanoTime()).append(";").append("Light").append(";").append(lux).append(";").append(0).append(";").append(0).append("\r\n");
        //Keystroke for pressure
        keyStroke.append(tempBuffer).append(System.nanoTime()).append(";").append("Pressure").append(";").append(hPa).append(";").append(0).append(";").append(0).append("\r\n");
        Log.i("keyStroke", keyStroke.toString());
        logValues.append(keyStroke);
        //Log.i(CLASS_NAME, "logValues------------->" + logValues);
        return true;
    }*/

    private void populateSensorData(String name, float x, float y,float z) {
        //Log.i(CLASS_NAME,sensorData.toString());
        //Appending the key and Co-ordinates
        logValues.append(name).append(";").append(System.nanoTime()).append(";").append(pressedKey).append(";")
                .append(coordinateX).append(";").append(coordinateY).append(";").append(x).append(";")
                .append(y).append(";").append(z).append("\r\n");

    }



    protected void onResume() {
        super.onResume();
        startServices();
    }

    protected void onPause() {
        super.onPause();
        if (accelerometerResultReceiver != null)
            stopService(new Intent(this, AccelerometerService.class));
        if (orientationResultReceiver != null)
            stopService(new Intent(this, OrientationService.class));
        if (gyroscopeResultReceiver != null)
            stopService(new Intent(this, GyroscopeService.class));
        if (gravityResultReceiver != null)
            stopService(new Intent(this, GravityService.class));
        if (pressureResultReceiver != null)
            stopService(new Intent(this, PressureService.class));
        if (lightResultReceiver != null)
            stopService(new Intent(this, LightService.class));
        if (magnitudeResultReceiver != null)
            stopService(new Intent(this, MagnitudeService.class));
    }

    /*protected String getBundleData() {
        Bundle bundle = getIntent().getExtras();
        StringBuilder temp = new StringBuilder();
        SessionManager session = new SessionManager(getApplicationContext());

        temp.append("User: ").append(session.getUserName()).append("\r\n");
        temp.append("Layout: ").append(getBundle(bundle, "layout")).append("\r\n");
        temp.append("Orientation: ").append(getBundle(bundle, "orientation")).append("\r\n");
        temp.append("Variation: ").append(getBundle(bundle, "variation")).append("\r\n");
        temp.append("Hardwareaddons: ").append(getBundle(bundle, "hardwareaddons")).append("\r\n");
        temp.append("Input: ").append(getBundle(bundle, "input")).append("\r\n");
        temp.append("Posture: ").append(getBundle(bundle, "posture")).append("\r\n");
        temp.append("Externalfactors: ").append(getBundle(bundle, "externalfactors")).append("\r\n").append("\r\n");
        //data = temp.toString();
        return temp.toString();
    }*/

    private class SaveToBuffer extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            //populateSensorData();
            return null;
        }
    }

    protected class SensorDataReceiver implements
            SensorResultReceiver.Receiver {

        //private float x, y, z;
        /*SensorData data = null;
        long timestamp = 0;
*/
        public SensorDataReceiver() {
        }

        @Override
        public void newEvent(int sensorType, float x, float y, float z) {
            //Log.i(CLASS_NAME,"x="+x+",y="+y+",z="+z);
            //Log.i("AcceReceiver", "sensorType=" + sensorType + ",x=" + x + ",y=" + y + ",z=" + z);

            if (sensorType == Sensor.TYPE_ACCELEROMETER) {
                populateSensorData("Acceleration", x,y,z);
            } else if (sensorType == Sensor.TYPE_GYROSCOPE) {
                populateSensorData("Gyroscope", x,y,z);
            } else if (sensorType == Sensor.TYPE_GRAVITY) {
                populateSensorData("Gravity", x,y,z);
            } else if (sensorType == Sensor.TYPE_MAGNETIC_FIELD) {
                populateSensorData("Magnitude", x,y,z);
            } /*else if (sensorType == Sensor.TYPE_ORIENTATION) {
                orientationData[0] = x;
                orientationData[1] = y;
                orientationData[2] = z;
            }*/

        }

        @Override
        public void error(String error) {
        }
    }



    private class PressureReceiver implements PressureResultReceiver.Receiver {

        public PressureReceiver() {
        }

        @Override
        public void newEvent(float x, float y, float z) {
            hPa = x;
        }

        @Override
        public void error(String error) {
        }
    }

    private class LightReceiver implements LightResultReceiver.Receiver {
        public LightReceiver() {
        }

        @Override
        public void newEvent(float x, float y, float z) {
            lux = x;
        }

        @Override
        public void error(String error) {
        }
    }



}
