package org.xo.demo.helloworld.hellostatemachine;

import com.android.internal.util.State;
import com.android.internal.util.StateMachine;

/**
 * 
 *             A
 *           /   \
 *          B     E
 *         / \
 *        C   D
 */
public class HelloStateMachine extends StateMachine {

    private StateA mSA = new StateA();
    private StateB mSB = new StateB();
    private StateC mSC = new StateC();
    private StateD mSD = new StateD();
    private StateE mSE = new StateE();

    protected HelloStateMachine(String name) {
        super(name);
        addState(mSA);
            addState(mSB, mSA);
                addState(mSC, mSB);
                addState(mSD, mSB);
            addState(mSE, mSA);
    }

    class StateA extends State {
        @Override
        public String getName() {
            return "State A";
        }
    }

    class StateB extends State {
        @Override
        public String getName() {
            return "State B";
        }
    }

    class StateC extends State {
        @Override
        public String getName() {
            return "State C";
        }
    }

    class StateD extends State {
        @Override
        public String getName() {
            return "State D";
        }
    }

    class StateE extends State {
        @Override
        public String getName() {
            return "State E";
        }
    }
}
