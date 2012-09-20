package org.xo.demo.widget.viewflipper.getsture;

import org.xo.demo.R;

import android.content.Context;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class ViewFlipperGestureListener extends SimpleOnGestureListener {

    private ViewFlipper mViewFlipper;
    private Context mContext;

    public ViewFlipperGestureListener(Context context, ViewFlipper viewFilpper) {
        mContext = context;
        mViewFlipper = viewFilpper;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
            float velocityY) {
        if (e2.getX() - e1.getX() > 120) {
            // Gesture from left to right, left in, right out
            Animation rInAnim = AnimationUtils.loadAnimation(mContext,
                    R.anim.push_left_in);
            Animation rOutAnim = AnimationUtils.loadAnimation(mContext,
                    R.anim.push_right_out);

            mViewFlipper.setInAnimation(rInAnim);
            mViewFlipper.setOutAnimation(rOutAnim);
            mViewFlipper.showPrevious();
            return true;
        } else if (e2.getX() - e1.getX() < -120) {
            // Gesture from right to left, left out, right in
            Animation lInAnim = AnimationUtils.loadAnimation(mContext,
                    R.anim.push_right_in);
            Animation lOutAnim = AnimationUtils.loadAnimation(mContext,
                    R.anim.push_left_out);

            mViewFlipper.setInAnimation(lInAnim);
            mViewFlipper.setOutAnimation(lOutAnim);
            mViewFlipper.showNext();
            return true;
        }
        return true;
    }
}
