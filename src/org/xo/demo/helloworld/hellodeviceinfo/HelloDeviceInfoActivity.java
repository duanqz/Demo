package org.xo.demo.helloworld.hellodeviceinfo;

import java.util.List;

import org.xo.demo.R;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityThread;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

public class HelloDeviceInfoActivity extends Activity {

    private static final String TAG = "HelloDeviceInfoActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hello_deviceinfo_activity);

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        StringBuilder deviceInfo = new StringBuilder();
        deviceInfo.append("\nDeviceId(IMEI)\t").append(tm.getDeviceId())
        .append("LineNumber\t").append(tm.getLine1Number())
        .append("NetworkType\t").append(tm.getNetworkType())
        .append("TopActivity\t").append(getTopActivity())
        .append("Window type\t").append(getWindow().getAttributes().type);

        TextView textView = (TextView) findViewById(R.id.device_info);
        textView.setText(deviceInfo);

        dumpRunningTaskInfo();
        dumpRunningProcessInfo();
    }

    private String getTopActivity() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

        if(runningTaskInfos != null) {
          return (runningTaskInfos.get(0).topActivity).toString();
        } else {
          return null;
        }
    }

    private void dumpRunningTaskInfo() {
        final ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        final List<RunningTaskInfo> runningTaskInfos = am.getRunningTasks(1);
        if(runningTaskInfos == null) {
            Log.i(TAG, "No running task");
            return;
        }

        final IPackageManager pm = ActivityThread.getPackageManager();

        for(RunningTaskInfo taskInfo : runningTaskInfos) {
            ActivityInfo ai;
            try {
                ai = pm.getActivityInfo(taskInfo.topActivity, PackageManager.GET_INTENT_FILTERS);
                Log.i(TAG, "Task Info : [Top activity package name is " + taskInfo.topActivity.getPackageName() + 
                        ", Top activity class name is " + taskInfo.topActivity.getShortClassName() +
                        ", TaskAffinity is " + ai.taskAffinity);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void dumpRunningProcessInfo() {
        final ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        final List<RunningAppProcessInfo> procInfos = am.getRunningAppProcesses();
        if(procInfos == null) {
            Log.i(TAG, "No running process");
            return;
        }

        for (RunningAppProcessInfo procInfo : procInfos) {
            if (procInfo.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                Log.i(TAG, "Porcess Name : " + procInfo.processName);
                for (String pkg : procInfo.pkgList) {
                    Log.i(TAG, "*** " + pkg);
                }
            }
        }
    }

}
