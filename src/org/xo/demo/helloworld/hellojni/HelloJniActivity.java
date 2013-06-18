package org.xo.demo.helloworld.hellojni;

import org.xo.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HelloJniActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_jni_activity);

        mTextView = (TextView) findViewById(R.id.jni_text);
    }

    @Override
    protected void onResume() {
        super.onResume();



        for(int i = 0; i < 10; i++) {
            new LibLoader();
        }

        mTextView.setText("Count " + LibLoader.mCount);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
