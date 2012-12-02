package org.xo.demo.helloworld.helloparcel;

import java.util.ArrayList;

import org.xo.demo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class HelloParcelableActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_parcelable_activity);
        
        Intent intent = new Intent(this, AActivity.class);
        
        long[] param1 = new long[2];
        param1[0] = 1L;
        param1[1] = 2L;
        intent.putExtra("PARAM1", param1);

        ArrayList<Integer> param2 = new ArrayList<Integer>();
        param2.add(3);
        param2.add(4);
        intent.putIntegerArrayListExtra("PARAM2", param2);

        startActivity(intent);
    }
}
