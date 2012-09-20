package org.xo.demo.widget.tabmenu.tabhost;

import org.xo.demo.R;

/**
 * Definition of tab menus.
 * 
 * @author duanqizhi
 * 
 */
public enum TabMenu {

    /** Tag: EMOTION */
    EMOTION("EMOTION", R.string.tab_menu_expression, R.id.tab_expression),

    /** Tag: ATTACHMENT */
    ATTACHMENT("ATTACHMENT", R.string.tab_menu_attachment, R.id.tab_attachment),

    /** Tag: CHOICE */
    CHOICE("CHOICE", R.string.tab_menu_choice, R.id.tab_choice);

    String mTag;
    int mResId;
    int mContentId;

    private TabMenu(String tag, int resId, int contentId) {
        mTag = tag;
        mResId = resId;
        mContentId = contentId;
    }

}
