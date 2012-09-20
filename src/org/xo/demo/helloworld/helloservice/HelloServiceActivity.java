package org.xo.demo.helloworld.helloservice;


import org.xo.demo.R;
import org.xo.demo.helloworld.helloservice.local.ILocalService;
import org.xo.demo.helloworld.helloservice.remote.IRemoteService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.TextView;

public class HelloServiceActivity extends Activity {

    private ILocalService mILocalService;
    private IRemoteService mIRemoteService;

    private TextView mLocalServiceText, mRemoteServiceText;

    private ServiceConnection mLocalServiceConn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mILocalService = (ILocalService) service;
            // Set caller information
            mILocalService.setCaller(getCallerDescription());

            StringBuilder text = new StringBuilder();
            text.append("Caller is\n" + mILocalService.getCaller())
            .append("\n\n")
            .append(getString(R.string.service_local_info, 
                    mILocalService.getPid(), 
                    mILocalService.getUid(),
                    mILocalService.getTid()));
            mLocalServiceText.setText(text.toString());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mLocalServiceConn = null;
        }
        
    };

    private ServiceConnection mRemoteServiceConn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIRemoteService = IRemoteService.Stub.asInterface(service);
            try {
                // Set caller information
                mIRemoteService.setCaller(getCallerDescription());

                StringBuilder text = new StringBuilder();
                text.append("Caller is\n" + mIRemoteService.getCaller())
                .append("\n\n")
                .append(getString(R.string.service_remote_info, 
                        mIRemoteService.getPid(), 
                        mIRemoteService.getUid(),
                        mIRemoteService.getTid()));
                mRemoteServiceText.setText(text.toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIRemoteService = null;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_aidl_activity);

        mLocalServiceText = (TextView) findViewById(R.id.local_service_text);
        mRemoteServiceText = (TextView) findViewById(R.id.remote_service_text);

        // Bind local service
        Intent localServiceIntent = new Intent("org.xo.demo.helloworld.helloservice.local.LocalService");
        bindService(localServiceIntent, mLocalServiceConn, Context.BIND_AUTO_CREATE);

        // Bind remote service
        Intent remoteServiceIntent = new Intent("org.xo.demo.helloworld.helloservice.remote.RemoteService");
        bindService(remoteServiceIntent, mRemoteServiceConn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mLocalServiceConn);
        unbindService(mRemoteServiceConn);
    }

    private String getCallerDescription() {
        StringBuilder des = new StringBuilder();
        des.append(android.os.Process.myPid())
        .append(", ").append(android.os.Process.myUid());

        return des.toString();
    }
}
