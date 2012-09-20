package org.xo.demo.widget.tabmenu.tabcontent;

import org.xo.demo.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuItemView extends LinearLayout {

    private MenuItem mMenuItem;
    private ImageView mIcon;
    private TextView mText;

    public MenuItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mIcon = (ImageView) findViewById(R.id.item_icon);
        mText = (TextView) findViewById(R.id.item_text);

    }

    public void bind(MenuItem menuItem) {
        mMenuItem = menuItem;
        mIcon.setImageResource(menuItem.mIconResId);
        mText.setText(menuItem.mTextResId);

    }

    public MenuItem getMenuItem() {
        return mMenuItem;
    }

}
