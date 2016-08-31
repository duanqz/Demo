package org.xo.demo.sensor.location;

import java.util.Timer;
import java.util.TimerTask;

import org.xo.demo.R;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TestLocationActivity extends Activity {

    private TextView mTextView;

    private Timer mTimer;
    private int mCount = 0;

    private LocationManager mLocationMananger;
    private LocationListener mLocationListner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_location_activity);

        mTextView = (TextView) findViewById(R.id.text);

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                updateWithCount();
            }

        }, 0, 1000);

        mLocationListner = new MyLocationListner();
        mLocationMananger = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        try {
            mLocationMananger.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    0, 0, mLocationListner);
        } catch (SecurityException e) {
            // Ignore
        }
    }

    private class MyLocationListner implements LocationListener {
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onLocationChanged(Location location) {
            Log.d("time", location + "");
            updateWithNewLocation(location);
        }
    };

    private void updateWithCount() {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                mTextView.setText(String.valueOf(++mCount));
            }
        });
    }

    private void updateWithNewLocation(Location location) {
        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.location_time, mCount))
            .append("\n")
            .append(getString(R.string.location_succ, lat, lng));
            mTextView.setText(sb.toString());
        } else {
            mTextView.setText(this.getString(R.string.location_fail));
        }

        if(mLocationMananger != null) {
            try {
                mLocationMananger.removeUpdates(mLocationListner);
            } catch (SecurityException e) {
                // Ignore
            }
        }
        mTimer.cancel();
    }
}
