package org.xo.demo.helloworld.helloservice.local;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;

/**
 * LocalService runs on local without AIDL
 * 
 * @author duanqizhi
 *
 */
public class LocalService extends Service implements ILocalService {

    private String mCaller;

    private IBinder mBinder = new Proxy();

    public class Proxy extends Binder implements ILocalService {

        @Override
        public int getPid() {
            // Actually call implementation of service
            return LocalService.this.getPid();
        }

        @Override
        public int getUid() {
            // Actually call implementation of service
            return LocalService.this.getUid();
        }

        @Override
        public int getTid() {
            // Actually call implementation of service
            return LocalService.this.getTid();
        }

        @Override
        public String getCaller() {
            return LocalService.this.getCaller();
        }

        @Override
        public void setCaller(String caller) {
            LocalService.this.setCaller(caller);
        }
        
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int getPid() {
        return Process.myPid();
    }

    @Override
    public int getUid() {
        return Process.myUid();
    }

    @Override
    public int getTid() {
        return Process.myTid();
    }

    @Override
    public String getCaller() {
        return mCaller;
    }

    @Override
    public void setCaller(String caller) {
        mCaller = caller;
    }
}
