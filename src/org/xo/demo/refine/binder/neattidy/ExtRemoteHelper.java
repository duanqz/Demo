package org.xo.demo.refine.binder.neattidy;

import android.os.Binder;
import android.os.RemoteException;

public class ExtRemoteHelper {

	private static final Binder mToken = new Binder();

	private static ExtIRemoteProxy mExtRemoteProxy = null;

	public static String getThreadName() {
		if ( mExtRemoteProxy == null) {
			mExtRemoteProxy = new ExtIRemoteProxy();
		}

		try {
			return mExtRemoteProxy.extGetThreadName(mToken);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return null;
	}
}