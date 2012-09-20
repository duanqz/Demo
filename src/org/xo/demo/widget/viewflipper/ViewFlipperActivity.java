package org.xo.demo.widget.viewflipper;

import org.xo.demo.R;
import org.xo.demo.widget.viewflipper.getsture.ViewFlipperGestureListener;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

/**
 * Demo for ViewFlipper.
 * 
 * @author duanqizhi
 * 
 */
public class ViewFlipperActivity extends Activity {

    private GestureDetector mGestureDetector;
    private ViewFlipper mViewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewFlipper = new MyViewFlipper(this);
        mGestureDetector = new GestureDetector(this,
                new ViewFlipperGestureListener(this, mViewFlipper));

        setContentView(mViewFlipper);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private class MyViewFlipper extends ViewFlipper {

        public MyViewFlipper(Context context) {
            super(context);

            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.drawable.ic_launcher);
            this.addView(imageView);

            ImageView searchView = new ImageView(context);
            searchView.setImageResource(R.drawable.ic_action_search);
            this.addView(searchView);
        }

    }
}
