package org.xo.demo.advertisement;

import org.xo.demo.R;

import android.app.Activity;
import android.os.Bundle;

import com.mobclix.android.sdk.MobclixMMABannerXLAdView;

public class MobclixActivity extends Activity {

    private MobclixMMABannerXLAdView mAdviewBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ads_mobclix_activity);
        inflateViews();
    }
    
    private void inflateViews() {
        mAdviewBanner = (MobclixMMABannerXLAdView) findViewById(R.id.advertising_banner_view);  
        mAdviewBanner.setRefreshTime(8000);  
        mAdviewBanner.getAd();
    }
}
