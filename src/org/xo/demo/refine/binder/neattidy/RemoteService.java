package org.xo.demo.refine.binder.neattidy;


import android.os.HandlerThread;

public class RemoteService extends HandlerThread {

	Remote mRemote;

	public RemoteService(String name) {
		super(name);

		mRemote = new Remote();
	}

	public IRemote getRemote() {
		return mRemote;
	}

}
