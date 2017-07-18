package com.eri.wechat2.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eri.wechat2.R;


public class TestAdapter extends BaseAdapter {
    private Context mContext;
    public TestAdapter(Context mContext){
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder mViewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_test, null);
            mViewHolder = new ViewHolder();
            view.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }
        return view;
    }
    private class ViewHolder {
        private TextView nameText;
    }

}
