package org.xo.demo.helloworld.hellostatemachine;

import org.xo.demo.R;

import android.app.Activity;
import android.os.Bundle;

public class HelloStateMachineActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hello_statemachine_activity);

        HelloStateMachine sm = new HelloStateMachine("Hello-StateMachine");
        sm.start();
    }
}
