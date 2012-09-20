package org.xo.demo.helloworld.helloservice.remote;

import org.xo.demo.helloworld.helloservice.remote.IRemoteService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;

/**
 * RemoteService runs on process named :remote
 * See IRemoteService.aidl, interfaces are pre-defined there.
 *
 * @author duanqizhi
 *
 */
public class RemoteService extends Service {

    private String mCaller;

    private IBinder mBinder = new IRemoteService.Stub() {

        @Override
        public int getPid() throws RemoteException {
            return Process.myPid();
        }

        @Override
        public int getUid() throws RemoteException {
            return Process.myUid();
        }

        @Override
        public int getTid() throws RemoteException {
            return Process.myTid();
        }

        @Override
        public String getCaller() throws RemoteException {
            return mCaller;
        }

        @Override
        public void setCaller(String caller) throws RemoteException {
            mCaller = caller;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

}
