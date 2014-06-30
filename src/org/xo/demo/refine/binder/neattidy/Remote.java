package org.xo.demo.refine.binder.neattidy;


import android.os.Process;
import android.os.RemoteException;

class Remote extends IRemote.Stub {

	@Override
	public int getPid() throws RemoteException {
		return Process.myPid();
	}

}
