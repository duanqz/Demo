package org.xo.demo.widget.tabmenu.tabcontent;

import java.util.ArrayList;
import java.util.List;

import org.xo.demo.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Tab content of attachment menus.
 * 
 * @author duanqizhi
 * 
 */
public class TabAttachmentMenuView extends GridView {

    public TabAttachmentMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);

        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem(R.drawable.ic_mess_pic,
                R.string.menu_settings, 0));
        menuItems.add(new MenuItem(R.drawable.ic_attach_capture_picture,
                R.string.menu_settings, 1));
        menuItems.add(new MenuItem(R.drawable.ic_mess_contact,
                R.string.menu_settings, 2));

        menuItems.add(new MenuItem(R.drawable.ic_attach_recorder,
                R.string.menu_settings, 3));
        menuItems.add(new MenuItem(R.drawable.ic_mess_attach_location,
                R.string.menu_settings, 4));
        menuItems.add(new MenuItem(R.drawable.ic_attach_video,
                R.string.menu_settings, 5));

        menuItems.add(new MenuItem(R.drawable.ic_attach_capture_video,
                R.string.menu_settings, 6));
        menuItems.add(new MenuItem(R.drawable.ic_attach_slideshow,
                R.string.menu_settings, 7));
        menuItems.add(new MenuItem(R.drawable.ic_attach_voice,
                R.string.menu_settings, 8));

        MenuItemAdapter adapter = new MenuItemAdapter(context);
        adapter.setMenuItems(menuItems);
        setAdapter(adapter);
    }

}
