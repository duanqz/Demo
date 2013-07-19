package org.xo.demo.tool.usagestats;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;

import com.android.internal.os.PkgUsageStats;

public class ReflectUsageStatsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try {
            getPkgUsageStats();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private PkgUsageStats[] getPkgUsageStats()
            throws ClassNotFoundException,
                   NoSuchMethodException,
                   IllegalArgumentException,
                   IllegalAccessException,
                   InvocationTargetException {

        // Get the Service Binder by reflect ServiceManager
        Class<?> classServiceManager = Class.forName("android.os.ServiceManager");
        Method methodGetService = classServiceManager.getMethod("getService", new Class[]{String.class});
        Object binderUsageStats = methodGetService.invoke(null, new Object[]{"usagestats"});

        // Get the UsageStats Service by reflect Binder
        Class<?> classIUsageStatsStub = Class.forName("com.android.internal.app.IUsageStats$Stub");
        Method methodAsInterface = classIUsageStatsStub.getMethod("asInterface", new Class[]{IBinder.class});
        Object objectUsageStatsService =  methodAsInterface.invoke(null, new Object[]{binderUsageStats});
        Class<?> classUsageStatsService = objectUsageStatsService.getClass();

        Method methodGetAllPkgUsageStats = classUsageStatsService.getMethod("getAllPkgUsageStats", null);
        Object result = methodGetAllPkgUsageStats.invoke(objectUsageStatsService, null);
        return null;
    }
}
