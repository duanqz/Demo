package org.xo.demo.helloworld.helloparcel;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class AActivity extends Activity {

    private static final String TAG = "AActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();

        long[] param1 = intent.getLongArrayExtra("PARAM1");
        for(int i = 0; i < param1.length; i++) {
            Log.i(TAG, "PARAM1: " + String.valueOf(param1[i]));
        }

        ArrayList<Integer> param2 = intent.getIntegerArrayListExtra("PARAM2");
        for(int i = 0; i < param2.size(); i++) {
            Log.i(TAG, "PARAM2: " + param2.get(i));
        }
    }
}
