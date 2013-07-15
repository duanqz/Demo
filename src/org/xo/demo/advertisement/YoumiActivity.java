package org.xo.demo.advertisement;

import net.youmi.android.AdManager;
import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;

import org.xo.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;

public class YoumiActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ads_youmi_activity);

        
        addYoumiAds();
    }

    private void addYoumiAds() {
        AdManager.getInstance(this).init(
                "2e829901f8217296", /* Release ID */
                "315ddeb8a13a886b ", /* Application Key */
                false);

        AdView adView = new AdView(this, AdSize.SIZE_320x50);
        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
 
        ViewGroup decorWindow = (ViewGroup) getWindow().getDecorView();
        ViewGroup adParent = (ViewGroup) decorWindow.getChildAt(0);
        adParent.addView(adView, params);
    }
}
