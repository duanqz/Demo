package org.xo.demo.widget.tabmenu.tabhost;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TabHost;

/**
 * Attachment menu tab host. Hosts all kinds of tab menus.
 * 
 * @author duanqizhi
 */
public class MenuTabHost extends TabHost {

    private List<TabMenu> mTabMenus;

    public MenuTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Initialize tab menus
        mTabMenus = new ArrayList<TabMenu>(3);
        mTabMenus.add(TabMenu.EMOTION);
        mTabMenus.add(TabMenu.ATTACHMENT);
        mTabMenus.add(TabMenu.CHOICE);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        inflateTabMenus();
    }

    private void inflateTabMenus() {
        setup();

        TabMenu tabMenu;
        for (int i = 0; i < mTabMenus.size(); i++) {
            tabMenu = mTabMenus.get(i);
            addTab(newTabSpec(tabMenu.mTag).setContent(tabMenu.mContentId)
                    .setIndicator(getString(tabMenu.mResId)));
        }
    }

    private String getString(int resId) {
        return getContext().getResources().getString(resId);
    }
}
