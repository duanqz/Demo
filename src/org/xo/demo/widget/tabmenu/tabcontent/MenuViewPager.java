package org.xo.demo.widget.tabmenu.tabcontent;

import java.util.ArrayList;
import java.util.List;

import org.xo.demo.R;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Layout manager that allows the user to flip left and right through pages of
 * attachment menu.
 * 
 * @author duanqizhi
 * 
 */
public class MenuViewPager extends ViewPager {

    public MenuViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        List<View> mViews = new ArrayList<View>();
        mViews.add(findViewById(R.id.attachment_page_one));
        mViews.add(findViewById(R.id.attachment_page_two));
        MenuViewPagerAdapter adapter = new MenuViewPagerAdapter(mViews);
        this.setAdapter(adapter);
    }

}
