package org.xo.demo.refine.binder.neattidy;

import android.os.Parcel;
import android.os.RemoteException;

public class ExtRemote extends Remote {

	private static final java.lang.String DESCRIPTOR = "org.xo.demo.refine.binder.neattidy.ExtIRemote";

	@Override
	public boolean onTransact(int code, Parcel data, Parcel reply, int flags)
			throws RemoteException {

		if (code == ExtIRemote.GET_THREAD_NAME) {
			data.enforceInterface(DESCRIPTOR);
			String _result = this.transact_getThreadName();
			reply.writeNoException();
			reply.writeString(_result);
			return true;
		}

		return super.onTransact(code, data, reply, flags);
	}

	public String transact_getThreadName() {
		return Thread.currentThread().getName();
	}	
}
