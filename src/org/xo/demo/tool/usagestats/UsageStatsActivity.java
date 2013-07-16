package org.xo.demo.tool.usagestats;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.android.internal.app.IUsageStats;
import com.android.internal.os.PkgUsageStats;

/**
 * 
 * Need system permission: 
 * android.permission.PACKAGE_USAGE_STATS
 */
public class UsageStatsActivity extends ListActivity {

    private static final String TAG = "UsageStatsActivity";
    private IUsageStats mUsageStatsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mUsageStatsService = IUsageStats.Stub.asInterface(ServiceManager.getService("usagestats"));
        if(mUsageStatsService == null) {
            Log.e(TAG, "Failed to retrieve usagestats service");
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String[] allUsageStats = getPkgUsageStats();
        if(allUsageStats == null) {
            Log.e(TAG, "Failed to retrieve pkgUsageStats");
            return;
        }

        setListAdapter(new ArrayAdapter<String>(
                this, 
                android.R.layout.simple_expandable_list_item_1,
                allUsageStats));
    }
    
    private String[] getPkgUsageStats() {
        PkgUsageStats[] allPkgUsageStats = null;
        try {
            allPkgUsageStats = mUsageStatsService.getAllPkgUsageStats();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(allPkgUsageStats == null) {
            return null;
        }

        String[] usageStats = new String[allPkgUsageStats.length];
        PkgUsageStats pkgUsageStats;
        for(int i = 0; i < allPkgUsageStats.length; i++) {
            pkgUsageStats = allPkgUsageStats[i];
            usageStats[i] = pkgUsageStats.packageName + "\nLauch Count: " + 
                            pkgUsageStats.launchCount + "\nUsage Time: " +
                            pkgUsageStats.usageTime;
        }
        return usageStats;
    }
}
