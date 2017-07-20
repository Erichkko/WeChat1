package com.eri.wechat2.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eri.wechat2.R;


public class DescHolder extends RecyclerView.ViewHolder {
    public TextView descView;

    public DescHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        descView = (TextView) itemView.findViewById(R.id.tv_desc);
    }
}
