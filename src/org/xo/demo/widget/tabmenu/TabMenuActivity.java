package org.xo.demo.widget.tabmenu;

import org.xo.demo.R;

import android.app.Activity;
import android.os.Bundle;

/**
 * A tab menu that has three tabs: Emotion, Attachment, Choice
 * 
 * @author duanqizhi
 * 
 */
public class TabMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tab_menu_activity);
    }
}
