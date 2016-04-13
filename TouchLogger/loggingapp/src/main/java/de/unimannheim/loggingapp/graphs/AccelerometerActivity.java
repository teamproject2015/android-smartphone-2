package de.unimannheim.loggingapp.graphs;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.androidplot.Plot;
import com.androidplot.util.Redrawer;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYStepMode;

import java.text.DecimalFormat;
import java.util.Arrays;

import de.unimannheim.loggingapp.R;
import de.unimannheim.loggingapp.sensors.SensorManagerActivity;
import de.unimannheim.loggingapp.sensors.SensorResultReceiver;

/**
 * Created by Saimadhav S on 20.11.2015.
 *
 */
public class AccelerometerActivity extends SensorManagerActivity {

    // number of points to plot in history
    private static final int HISTORY_SIZE = 30;

    private Redrawer redrawer;
    private SimpleXYSeries azimuthHistorySeries = null;
    private SimpleXYSeries pitchHistorySeries = null;
    private SimpleXYSeries rollHistorySeries = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xy_chart);

        Handler handler = new Handler();

        //getting the acelerometer receiver from service
        accelerometerResultReceiver = new SensorResultReceiver(handler);
        accelerometerResultReceiver.setReceiver(new AcceleroReceiver());

        // setting the screen in portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        // setup the APR History plot:
        XYPlot aprPlot = (XYPlot) findViewById(R.id.aprHistoryPlot);

        // setting the 3-axis for the given plot
        azimuthHistorySeries = new SimpleXYSeries("X");
        azimuthHistorySeries.useImplicitXVals();
        pitchHistorySeries = new SimpleXYSeries("Y");
        pitchHistorySeries.useImplicitXVals();
        rollHistorySeries = new SimpleXYSeries("Z");
        rollHistorySeries.useImplicitXVals();

        //drawing the plot
        aprPlot.setRangeBoundaries(-10, 10, BoundaryMode.AUTO);
        aprPlot.setDomainBoundaries(0, HISTORY_SIZE, BoundaryMode.AUTO);
        aprPlot.addSeries(azimuthHistorySeries,
                new LineAndPointFormatter(
                        Color.rgb(100, 100, 200), null, null, null));
        aprPlot.addSeries(pitchHistorySeries,
                new LineAndPointFormatter(
                        Color.rgb(100, 200, 100), null, null, null));
        aprPlot.addSeries(rollHistorySeries,
                new LineAndPointFormatter(
                        Color.rgb(200, 100, 100), null, null, null));
        aprPlot.setDomainStepMode(XYStepMode.INCREMENT_BY_VAL);
        aprPlot.setDomainStepValue(HISTORY_SIZE / 10);
        aprPlot.setTicksPerRangeLabel(3);
        aprPlot.setDomainLabel("Sample Index");
        aprPlot.getDomainLabelWidget().pack();
        aprPlot.setRangeLabel("Angle (Degs)");
        aprPlot.getRangeLabelWidget().pack();

        aprPlot.setRangeValueFormat(new DecimalFormat("#"));
        aprPlot.setDomainValueFormat(new DecimalFormat("#"));


        redrawer = new Redrawer(
                Arrays.asList(new Plot[]{aprPlot}),
                100, false);
    }

    /**
     * Class that gets the axis values from acccelerometer service class by using
     * SensorResultReceiver.receiver interface
     */
    private class AcceleroReceiver implements
            SensorResultReceiver.Receiver {

        private float x, y, z;

        public AcceleroReceiver() {
        }

        private void sendToUI() {
            runOnUiThread(new UpdateUI("x: " + x, "y: " + y, "z: " + z));
        }

        @Override
        public void newEvent(int sensorType, long timeStamp, float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
//            Log.i("AcceReceiver", "sensorType=" + sensorType + ",x=" + x + ",y=" + y + ",z=" + z);
            sendToUI();

            // get rid the oldest sample in history:
            if (rollHistorySeries.size() > HISTORY_SIZE) {
                rollHistorySeries.removeFirst();
                pitchHistorySeries.removeFirst();
                azimuthHistorySeries.removeFirst();
            }
            // add the latest history sample:
            azimuthHistorySeries.addLast(null, x);
            pitchHistorySeries.addLast(null, y);
            rollHistorySeries.addLast(null, z);
        }

        @Override
        public void error(String error) {
        }

    }

    /**
     * Sets the x,y,z axis to the UI screen which runs parallel with the graph
     */
    private class UpdateUI implements Runnable {

        private String x;
        private String y;
        private String z;

        private TextView xTextView;
        private TextView yTextView;
        private TextView zTextView;

        // update the UI with new x,y,z values
        public UpdateUI(String x, String y, String z) {
            this.xTextView = (TextView) findViewById(R.id.xAxis_textView);
            this.x = x;
            this.yTextView = (TextView) findViewById(R.id.yAxis_textView);
            this.y = y;
            this.zTextView = (TextView) findViewById(R.id.zAxis_textView);
            this.z = z;
        }

        @Override
        public void run() {
            xTextView.setText(x);
            yTextView.setText(y);
            zTextView.setText(z);
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        redrawer.start();
    }

    @Override
    public void onPause() {
        redrawer.pause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        redrawer.finish();
        super.onDestroy();
    }

}
