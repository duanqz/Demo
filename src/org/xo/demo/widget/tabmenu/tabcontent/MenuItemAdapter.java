package org.xo.demo.widget.tabmenu.tabcontent;

import java.util.List;

import org.xo.demo.R;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MenuItemAdapter extends BaseAdapter {

    private List<MenuItem> mMenuItems;
    private LayoutInflater mInflater;

    private Context mContext;

    public MenuItemAdapter(Context context) {
        mContext = context;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.mMenuItems = menuItems;

        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<MenuItem> getMenuItems() {
        return mMenuItems;
    }

    @Override
    public int getCount() {
        if (mMenuItems != null) {
            return mMenuItems.size();
        }

        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mMenuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            MenuItemView menuItemView = (MenuItemView) mInflater.inflate(
                    R.layout.tab_menu_item_view, null);
            MenuItem menuItem = mMenuItems.get(position);
            menuItemView.bind(menuItem);

            convertView = menuItemView;
        }
        return convertView;
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        if (observer != null) {
            super.unregisterDataSetObserver(observer);
        }
    }
}
