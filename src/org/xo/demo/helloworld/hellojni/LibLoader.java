package org.xo.demo.helloworld.hellojni;

import android.util.Log;

public class LibLoader {

    static int mCount = 0;

    static {
        Log.d("XXX", "Hello " + mCount++);
    }
}
