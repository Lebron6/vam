package com.dy.vam.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.dy.vam.ui.adapter.AbstractPanelListAdapter;


/**
 * <pre>
 *     author : zyb
 *     e-mail : hbdxzyb@hotmail.com
 *     time   : 2017/05/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class PanelListLayout extends RelativeLayout {

    private AbstractPanelListAdapter adapter;

    public AbstractPanelListAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(AbstractPanelListAdapter adapter, AdapterView.OnItemClickListener onItemClickListener) {
        this.adapter = adapter;
        adapter.initAdapter(onItemClickListener);
    }

    public PanelListLayout(Context context) {
        super(context);
    }

    public PanelListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PanelListLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
