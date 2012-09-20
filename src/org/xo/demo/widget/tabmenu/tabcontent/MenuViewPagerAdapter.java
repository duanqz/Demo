package org.xo.demo.widget.tabmenu.tabcontent;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * Providing the adapter to populate pages inside of a {@link MenuViewPager}.
 * 
 * @author duanqizhi
 * 
 */
public class MenuViewPagerAdapter extends PagerAdapter {

    private List<View> mViews;

    public MenuViewPagerAdapter(List<View> views) {
        mViews = views;
    }

    @Override
    public int getCount() {
        if (mViews != null) {
            return mViews.size();
        }

        return 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(mViews.get(position), 0);
        return mViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(mViews.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
