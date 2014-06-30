package org.xo.demo.refine.binder.neattidy;

import android.os.IBinder;
import android.os.RemoteException;

public interface ExtIRemote {

	public static final int EXT_FIRST_CALL_TRANSACTION =  10001;
	
	public static final int GET_THREAD_NAME = EXT_FIRST_CALL_TRANSACTION + 1;

	String extGetThreadName(IBinder binder) throws RemoteException;
}
