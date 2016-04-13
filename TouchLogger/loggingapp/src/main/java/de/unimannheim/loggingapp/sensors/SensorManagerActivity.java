package de.unimannheim.loggingapp.sensors;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import de.unimannheim.loggingapp.BaseActivity;
import de.unimannheim.loggingapp.R;
import de.unimannheim.loggingapp.session.SessionManager;
import de.unimannheim.loggingapp.touchlogger.KeyboardActivity;
import de.unimannheim.loggingapp.utils.Constants;
import de.unimannheim.loggingapp.utils.GenerateFeaturesFile;

/**
 * Created by suryadevara on 18-06-2015.
 * <p/>
 * Adding all the sensor data from this file
 */
public class SensorManagerActivity extends BaseActivity {

    private static final String CLASS_NAME = "SensorManagerActivity";
    static Random rnd = new Random();
    protected float lux;
    protected float hPa;
    protected StringBuffer logValues = new StringBuffer();
    protected String generatedKey;
    protected String data;
    protected SensorResultReceiver accelerometerResultReceiver;
    protected SensorResultReceiver orientationResultReceiver;
    protected SensorResultReceiver gyroscopeResultReceiver;
    protected SensorResultReceiver gravityResultReceiver;
    protected PressureResultReceiver pressureResultReceiver;
    protected LightResultReceiver lightResultReceiver;
    protected SensorResultReceiver magnitudeResultReceiver;
    protected SensorResultReceiver rotationVectorResultReceiver;
    protected String pressedKey;
    protected float coordinateX, coordinateY;
    private char[] randomKeys;
    private int charCount;

    protected String csvFileName;

    /**
     * Implementing Fisher–Yates shuffle
     */
    private static char[] shuffleArray(char[] ar) {
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            char a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pressedKey = "";

        //registering the sensors
        registerSensors();

        //Starting the services by using startservice method
        startServices();
    }

    public void startServices() {

        // start accelerator service
        Intent accIntent = new Intent(this, AccelerometerService.class);
        accIntent.putExtra(Constants.EXTRA_RECEIVER, accelerometerResultReceiver);
        startService(accIntent);

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

        // start Magnitude service
        Intent magnitudeIntent = new Intent(this, MagnitudeService.class);
        magnitudeIntent.putExtra(Constants.EXTRA_RECEIVER, magnitudeResultReceiver);
        startService(magnitudeIntent);

        // start Rotation Vector service
        Intent rotationVectorIntent = new Intent(this, RotationVectorService.class);
        rotationVectorIntent.putExtra(Constants.EXTRA_RECEIVER, rotationVectorResultReceiver);
        startService(rotationVectorIntent);

        // start orientation service
        /*Intent orientationIntent = new Intent(this, OrientationService.class);
        orientationIntent.putExtra(Constants.EXTRA_RECEIVER, orientationResultReceiver);
        startService(orientationIntent);*/
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

            } else if (type == Sensor.TYPE_GYROSCOPE) {
                gyroscopeResultReceiver = new SensorResultReceiver(handler);
                gyroscopeResultReceiver.setReceiver(new SensorDataReceiver());

            } else if (type == Sensor.TYPE_MAGNETIC_FIELD) {
                magnitudeResultReceiver = new SensorResultReceiver(handler);
                magnitudeResultReceiver.setReceiver(new SensorDataReceiver());

            } else if (type == Sensor.TYPE_ROTATION_VECTOR) {
                rotationVectorResultReceiver = new SensorResultReceiver(handler);
                rotationVectorResultReceiver.setReceiver(new SensorDataReceiver());

            } else if (type == Sensor.TYPE_LIGHT) {
                lightResultReceiver = new LightResultReceiver(handler);
                lightResultReceiver.setReceiver(new LightReceiver());

            } else if (type == Sensor.TYPE_GRAVITY) {
                gravityResultReceiver = new SensorResultReceiver(handler);
                gravityResultReceiver.setReceiver(new SensorDataReceiver());

            } else if (type == Sensor.TYPE_PRESSURE) {
                pressureResultReceiver = new PressureResultReceiver(handler);
                pressureResultReceiver.setReceiver(new PressureReceiver());

            }
        }
        /*if(magnitudeResultReceiver!= null && gyroscopeResultReceiver!=null) {
            orientationResultReceiver = new SensorResultReceiver(handler);
            orientationResultReceiver.setReceiver(new SensorDataReceiver());
        }*/
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
            //Log.d(CLASS_NAME, "Random key generated"+ new String(randomKeys));
        }

        generatedKey = String.valueOf(randomKeys[charCount++]);
        if (randomKeys.length - 1 == charCount) {
            generatedKey = "All keys are finished";
        }
        //setting the generatedkey to textview
        TextView generateKeyForTextView = (TextView) findViewById(R.id.textView_key);
        generateKeyForTextView.setText(generatedKey);
    }

    /**
     * get the touch events from the other event and set the values to sensormanageractivity
     *
     * @param x        x Coordinate
     * @param y        y Coordinate
     * @param key      pressed key value
     * @param downTime downtime of key press
     * @param upTime   uptime of key press
     */
    protected void recordTouchEvent(float x, float y, String key,
                                    long downTime, long upTime) {

        this.pressedKey = key;
        this.coordinateX = x;
        this.coordinateY = y;
        // Log.d("Record", "Key=" + this.pressedKey + ",coordinateX=" + this.coordinateX +",coordinateY=" + this.coordinateY);
    }


    /**
     * Populate the sensor data to stringbuffer to save to csv file
     *
     * @param name      name of the sensor
     * @param timeStamp timestamp when the sensor got the value
     * @param x         x axis of sensor
     * @param y         y axis of sensor
     * @param z         z axis of sensor
     */
    private void populateSensorData(String name, long timeStamp, float x, float y, float z) {
        //Log.d(CLASS_NAME,sensorData.toString());

        //Appending the key and Co-ordinates
        logValues.append(name).append(";").append(timeStamp).append(";").append(pressedKey).append(";")
                .append(coordinateX).append(";").append(coordinateY).append(";").append(x).append(";")
                .append(y).append(";").append(z).append("\r\n");

    }


    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /**
     * onResume the services are again activated
     */
    protected void onResume() {
        super.onResume();
        startServices();
    }

    /**
     * all the sensors are unregistered in onPause method.
     */
    protected void onPause() {
        super.onPause();

        //calling the async method
        SaveFeaturesFile saveFeaturesFile = new SaveFeaturesFile();
        saveFeaturesFile.execute(csvFileName);

        //disabling all the sensor services
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
        if (rotationVectorResultReceiver != null)
            stopService(new Intent(this, RotationVectorService.class));
    }

    /**
     * SaveFeaturesFile class runs in the background for saving the Features file
     */
    private class SaveFeaturesFile extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            GenerateFeaturesFile generate = new GenerateFeaturesFile();
            generate.writeCSVFeaturesFile(csvFileName);
            return null;
        }
    }


    /**
     * gets the user related information such as username, layout, orientation, variation and so on
     *
     * @return string value
     */
    protected String getBundleData() {
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
    }

    /**
     * SensorDataReceiver gets the data from service class to current class
     */
    protected class SensorDataReceiver implements
            SensorResultReceiver.Receiver {

        public SensorDataReceiver() {
        }

        @Override
        public void newEvent(int sensorType, long timeStamp, float x, float y, float z) {

            if (sensorType == Sensor.TYPE_ACCELEROMETER) {
               /* accelerometerData[0] = x;
                accelerometerData[1] = y;
                accelerometerData[2] = z;*/
                populateSensorData("Acceleration", timeStamp, x, y, z);
            } else if (sensorType == Sensor.TYPE_GYROSCOPE) {
              /*gyroscopeData[0] = x;
                gyroscopeData[1] = y;
                gyroscopeData[2] = z;*/
                populateSensorData("Gyroscope", timeStamp, x, y, z);
            } else if (sensorType == Sensor.TYPE_GRAVITY) {
              /*gravityData[0] = x;
                gravityData[1] = y;
                gravityData[2] = z;*/
                populateSensorData("Gravity", timeStamp, x, y, z);
            } else if (sensorType == Sensor.TYPE_MAGNETIC_FIELD) {
              /*magnitudeData[0] = x;
                magnitudeData[1] = y;
                magnitudeData[2] = z;*/
                populateSensorData("Magnitude", timeStamp, x, y, z);
            } else if (sensorType == Sensor.TYPE_ROTATION_VECTOR) {
                populateSensorData("RotationVector", timeStamp, x, y, z);
            }
        }

        @Override
        public void error(String error) {
        }
    }


    /**
     * PressureReceiver gets the data from Pressure service class to current class
     */
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

    /**
     * LightReceiver gets the data from Light service class to current class
     */
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
