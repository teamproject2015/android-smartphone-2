package de.unimannheim.loggingapp.graphs;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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
import de.unimannheim.loggingapp.sensors.AccelerometerResultReceiver;
import de.unimannheim.loggingapp.sensors.AccelerometerService;
import de.unimannheim.loggingapp.utils.Constants;

/**
 * Created by Saimadhav S on 20.11.2015.
 */
public class AccelerometerActivity extends AppCompatActivity {

    private static final int HISTORY_SIZE = 30;            // number of points to plot in history

    private XYPlot aprHistoryPlot = null;


    private Redrawer redrawer;
    private SimpleXYSeries azimuthHistorySeries = null;
    private SimpleXYSeries pitchHistorySeries = null;
    private SimpleXYSeries rollHistorySeries = null;
    private AccelerometerResultReceiver accelerometerResultReceiver;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xy_chart);

        Handler handler = new Handler();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerResultReceiver = new AccelerometerResultReceiver(handler);
        accelerometerResultReceiver.setReceiver(new AcceleroReceiver());

        Intent accIntent = new Intent(this, AccelerometerService.class);
        accIntent.putExtra(Constants.EXTRA_RECEIVER, accelerometerResultReceiver);
        startService(accIntent);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        // setup the APR History plot:
        aprHistoryPlot = (XYPlot) findViewById(R.id.aprHistoryPlot);

        azimuthHistorySeries = new SimpleXYSeries("X");
        azimuthHistorySeries.useImplicitXVals();
        pitchHistorySeries = new SimpleXYSeries("Y");
        pitchHistorySeries.useImplicitXVals();
        rollHistorySeries = new SimpleXYSeries("Z");
        rollHistorySeries.useImplicitXVals();

        aprHistoryPlot.setRangeBoundaries(-10, 10, BoundaryMode.AUTO);
        aprHistoryPlot.setDomainBoundaries(0, HISTORY_SIZE, BoundaryMode.AUTO);
        aprHistoryPlot.addSeries(azimuthHistorySeries,
                new LineAndPointFormatter(
                        Color.rgb(100, 100, 200), null, null, null));
        aprHistoryPlot.addSeries(pitchHistorySeries,
                new LineAndPointFormatter(
                        Color.rgb(100, 200, 100), null, null, null));
        aprHistoryPlot.addSeries(rollHistorySeries,
                new LineAndPointFormatter(
                        Color.rgb(200, 100, 100), null, null, null));
        aprHistoryPlot.setDomainStepMode(XYStepMode.INCREMENT_BY_VAL);
        aprHistoryPlot.setDomainStepValue(HISTORY_SIZE / 10);
        aprHistoryPlot.setTicksPerRangeLabel(3);
        aprHistoryPlot.setDomainLabel("Sample Index");
        aprHistoryPlot.getDomainLabelWidget().pack();
        aprHistoryPlot.setRangeLabel("Angle (Degs)");
        aprHistoryPlot.getRangeLabelWidget().pack();

        aprHistoryPlot.setRangeValueFormat(new DecimalFormat("#"));
        aprHistoryPlot.setDomainValueFormat(new DecimalFormat("#"));


        redrawer = new Redrawer(
                Arrays.asList(new Plot[]{aprHistoryPlot}),
                100, false);
    }


    private class AcceleroReceiver implements
            AccelerometerResultReceiver.Receiver {


        private float x, y, z;

        public AcceleroReceiver() {
        }

        private void sendToUI() {
            runOnUiThread(new UpdateUI("x: " + x, "y: " + y, "z: " + z));
        }

        @Override
        public void newEvent(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
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

    private class UpdateUI implements Runnable {

        private String x;
        private String y;
        private String z;

        private TextView xTextView;
        private TextView yTextView;
        private TextView zTextView;

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
