package org.xo.demo.refine.binder.neattidy;

import android.os.IBinder;
import android.os.RemoteException;

public class ExtIRemoteProxy implements ExtIRemote {

	private static final java.lang.String DESCRIPTOR = "org.xo.demo.refine.binder.neattidy.ExtIRemote";

	@Override
	public String extGetThreadName(IBinder binder) throws RemoteException {
		android.os.Parcel _data = android.os.Parcel.obtain();
		android.os.Parcel _reply = android.os.Parcel.obtain();
		String _result;
		try {
			_data.writeInterfaceToken(DESCRIPTOR);
			binder.transact(ExtIRemote.GET_THREAD_NAME, _data, _reply, 0);
			_reply.readException();
			_result = _reply.readString();
		} finally {
			_reply.recycle();
			_data.recycle();
		}
		return _result;
	}
}
