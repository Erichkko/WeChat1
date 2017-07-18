package com.eri.wechat2.ui.fragment;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.eri.wechat2.R;
import com.eri.wechat2.ui.adapter.TestAdapter;
import com.eri.wechat2.ui.fragment.base.BaseFragment;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


public class Fragment2 extends BaseFragment{
    private PullToRefreshListView listview;

    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frag_fragment2,null);
    }

    @Override
    protected void initFindViewById(View view) {
        listview = (PullToRefreshListView)view.findViewById(R.id.ptr_listview);

    }

    @Override
    public void initData() {
        TestAdapter adapter = new TestAdapter(getActivity());
        listview.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }
}
