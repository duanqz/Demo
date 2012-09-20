package org.xo.demo.widget.rotation3d;

import org.xo.demo.R;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;

public class Rotation3dActivity extends Activity {

    private Button mButton;

    private Drawable mTopDrawable, mLeftDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rotation3d_activity);

        initResouce();
    }

    private void initResouce() {
        mTopDrawable = getResources().getDrawable(R.drawable.ic_action_search);
        mTopDrawable.setBounds(0, 0, mTopDrawable.getIntrinsicWidth(),
                mTopDrawable.getIntrinsicHeight());
        mLeftDrawable = getResources().getDrawable(R.drawable.ic_add_attach);
        mLeftDrawable.setBounds(0, 0, mLeftDrawable.getIntrinsicWidth(),
                mLeftDrawable.getIntrinsicHeight());
        mButton = (Button) findViewById(R.id.btn);
        mButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                applyRotation(0, 180);
            }
        });
    }

    /**
     * Setup a new 3D rotation on the container view.
     * 
     * @param position
     *            the item that was clicked to show a picture, or -1 to show the
     *            list
     * @param start
     *            the start angle at which the rotation must begin
     * @param end
     *            the end angle of the rotation
     */
    private void applyRotation(float start, float end) {
        // Find the center of the container
        final float centerX = mButton.getWidth() / 2.0f;
        final float centerY = mButton.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end,
                centerX, centerY, 0.0f, false);
        rotation.setDuration(500);
        rotation.setFillAfter(false);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new MyAnimationListener());

        mButton.startAnimation(rotation);
    }

    private class MyAnimationListener implements AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            mButton.setCompoundDrawables(mLeftDrawable, mTopDrawable, null,
                    null);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            mButton.setCompoundDrawables(mTopDrawable, mLeftDrawable, null,
                    null);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

    }
}
