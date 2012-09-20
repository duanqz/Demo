package org.xo.demo.sensor.flashlight;

import java.lang.ref.WeakReference;

import org.xo.demo.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;

/**
 * Flash light
 *
 * @author duanqizhi
 *
 */
public class FlashLightActivity extends Activity implements OnClickListener {

    private PowerManager.WakeLock mWakeLock;

    private static final int MODE_NONE = 0;
    private static final int MODE_FLASH_WINDOW = 1;
    private static final int MODE_FLASH_CAMERA = 2;
    private static final int MODE_SOS_WINDOW   = 3;

//    // flag to detect flash is on or off
//    private boolean isLighOn = false;

    private Camera camera;

    private ToggleButton mBtnFlashWindow, mBtnSosWindow, mBtnFlashCamera;
    private View mWindow;

    private Handler mFlashHandler; 

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_light_activity);

        mFlashHandler = new FlashHandler(this);

        initResource();
        createWakeLock();
    }

    private void initResource() {
        mBtnFlashWindow = (ToggleButton) findViewById(R.id.btn_flash_window);
        mBtnSosWindow = (ToggleButton) findViewById(R.id.btn_sos_window);
        mBtnFlashCamera = (ToggleButton) findViewById(R.id.btn_flash_camera);
        mWindow = findViewById(R.id.flash_window);

        mBtnFlashWindow.setOnClickListener(this);
        mBtnSosWindow.setOnClickListener(this);
        mBtnFlashCamera.setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        releaseWakeLock();

        if (camera != null) {
            camera.release();
        }
    }

    @Override
    public void onClick(View v) {
        if(v instanceof ToggleButton) {
            ToggleButton button = (ToggleButton) v;
            int mode = toFlashMode(button);
            if(button.isChecked()) {
                // Begin flashing
                acquireWakeLock();
                Message msg = mFlashHandler.obtainMessage(mode);
                mFlashHandler.sendMessage(msg);
            } else {
                // Stop flashing
                releaseWakeLock();
                mFlashHandler.removeMessages(mode);
            }
        }
    }

    private int toFlashMode(Button button) {
        if(button == mBtnFlashWindow) {
            return MODE_FLASH_WINDOW;
        } else if(button == mBtnSosWindow){
            return MODE_SOS_WINDOW;
        } else if(button == mBtnFlashCamera) {
            return MODE_FLASH_CAMERA;
        } else {
            return MODE_NONE;
        }
    }

    private synchronized void createWakeLock() {
        if(mWakeLock == null) {
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Flash light");
            mWakeLock.setReferenceCounted(false);
        }
    }

    private void acquireWakeLock() {
        mWakeLock.acquire();
    }

    private void releaseWakeLock() {
        if(mWakeLock != null && mWakeLock.isHeld()) {
            mWakeLock.release();
        }
    }

    /**
     * Avoid leak of outer class, use static Handler and weak reference 
     * to outer class.
     */
    private static class FlashHandler extends Handler {
        private WeakReference<FlashLightActivity> mFlashLightActivity;
        // Flag control background color
        private int mColorIdx = 0;
        private static final int[] mColor = new int[] {
            Color.WHITE, Color.BLACK
        };

        private int mSOSIdx;
        private static final int[] mSOSCode = new int[] {
                300, 300, 300, 300, 300,        // ...  S
                //
                900,
                //
                900, 300, 900, 300, 900,        // ---  O
                //
                900,
                //
                300, 300, 300, 300, 300,        // ...  S
                //
                2100
        };

        public FlashHandler(FlashLightActivity activity) {
            mFlashLightActivity = new WeakReference<FlashLightActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            FlashLightActivity activity = mFlashLightActivity.get();
            if(activity == null) {
                return;
            }

            

            switch(msg.what) {
            case MODE_FLASH_WINDOW:
                changeBackground(activity);

                // Continue the same message at delayMillis
                removeMessages(MODE_FLASH_WINDOW);
                sendMessageDelayed(obtainMessage(MODE_FLASH_WINDOW), 500);
                break;

            case MODE_SOS_WINDOW:
                changeBackground(activity);
                removeMessages(MODE_SOS_WINDOW);
                sendMessageDelayed(obtainMessage(MODE_SOS_WINDOW), mSOSCode[mSOSIdx]);
                mSOSIdx++;
                if(mSOSIdx >= mSOSCode.length) {
                    mSOSIdx = 0;
                }
                break;
            }
        }

        private void changeBackground(FlashLightActivity activity) {
            // Set background color
            activity.mWindow.setBackgroundColor(mColor[mColorIdx]);
            mColorIdx = mColorIdx == 0 ? 1 : 0;
        }
    }
}
