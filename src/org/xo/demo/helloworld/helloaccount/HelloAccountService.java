package org.xo.demo.helloworld.helloaccount;

import android.accounts.AccountManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class HelloAccountService extends Service {

    private HelloAccountAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        super.onCreate();
        mAuthenticator = new HelloAccountAuthenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        if(AccountManager.ACTION_AUTHENTICATOR_INTENT.equals(intent.getAction())) {
            return mAuthenticator.getIBinder();
        }
        return null;
    }

}
