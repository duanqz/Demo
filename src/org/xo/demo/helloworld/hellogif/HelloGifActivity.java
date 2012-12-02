package org.xo.demo.helloworld.hellogif;

import org.xo.demo.R;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class HelloGifActivity extends Activity {

    private ImageView mGifView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hello_gif_activity);
        mGifView = (ImageView) findViewById(R.id.gif_view);

        Drawable d = getResources().
                getDrawable(R.drawable.ic_launcher);
        mGifView.setBackgroundDrawable(d);
        RotateAnimation rotate = new RotateAnimation(0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setRepeatCount(-1);
        rotate.setDuration(1000);

        mGifView.setAnimation(rotate);
    }
}
