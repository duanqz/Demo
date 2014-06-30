package org.xo.demo.refine.binder.neatpolluted;

import android.os.Process;
import android.os.RemoteException;

class Remote extends IRemote.Stub {

	@Override
	public int getPid() throws RemoteException {
		return Process.myPid();
	}

	@Override
	public String extGetThreadName() throws RemoteException {
		return Thread.currentThread().getName();
	}

}
