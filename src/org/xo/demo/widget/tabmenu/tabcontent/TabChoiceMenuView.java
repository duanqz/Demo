package org.xo.demo.widget.tabmenu.tabcontent;

import java.util.ArrayList;
import java.util.List;

import org.xo.demo.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Tab content of choice menus.
 * 
 * @author duanqizhi
 * 
 */
public class TabChoiceMenuView extends GridView {

    public TabChoiceMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);

        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem(R.drawable.ic_mess_fav,
                R.string.menu_settings, 9));
        menuItems.add(new MenuItem(R.drawable.ic_attach_template,
                R.string.menu_settings, 10));
        menuItems.add(new MenuItem(R.drawable.ic_mess_wish,
                R.string.menu_settings, 11));

        MenuItemAdapter mMenuItemAdapter = new MenuItemAdapter(context);
        mMenuItemAdapter.setMenuItems(menuItems);
        setAdapter(mMenuItemAdapter);

    }

}
