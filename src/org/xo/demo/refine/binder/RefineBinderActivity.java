package org.xo.demo.refine.binder;


import org.xo.demo.R;
import org.xo.demo.refine.binder.neatpolluted.RemoteService;

import android.app.Activity;
import android.os.Bundle;
import android.os.Process;
import android.widget.TextView;

public class RefineBinderActivity extends Activity {

	RemoteService mRemoteService = new RemoteService("Remote Service"); 

	TextView mLocalProcess, mRemoteProcess;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mRemoteService.start();

		setContentView(R.layout.hello_binder_activity);

		mLocalProcess = (TextView) findViewById(R.id.local_process);
		mRemoteProcess = (TextView) findViewById(R.id.remote_process);
	}

	@Override
	protected void onResume() {
		super.onResume();

		StringBuilder localText = new StringBuilder();
		localText.append("Local")
		.append("\n")
		.append("Thread Name : ").append(Thread.currentThread().getName())
		.append("\n")
		.append("Process ID : ").append(Process.myPid());
		mLocalProcess.setText(localText);

//		IRemote iremote = mRemoteService.getRemote();
////		IRemote iremote = IRemote.Stub.asInterface(ServiceManager.getService("demo.remote"));
//		if (iremote != null) {
//			try {
//				int pid = iremote.getPid();
//				StringBuilder remoteText = new StringBuilder();
//				remoteText.append("Remote")
//				.append("\n")
//				.append("Thread Name : ").append(iremote.getThreadName())
//				.append("\n")
//				.append("Process ID : ").append(pid);
//				mRemoteProcess.setText(remoteText);
//			} catch (RemoteException e) {
//				e.printStackTrace();
//			}
//		}

	}
}
