package com.eri.wechat2.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.eri.wechat2.R;
import com.eri.wechat2.ui.fragment.base.BaseFragment;
import com.eri.wechat2.ui.view.PullToRefreshRecyclerView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;


public class Fragment1 extends BaseFragment{
    private PullToRefreshRecyclerView rlv;
    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frag_fragment1,null);
    }

    @Override
    protected void initFindViewById(View view) {
        rlv = (PullToRefreshRecyclerView)view.findViewById(R.id.rc_view);

    }

    @Override
    public void initData() {

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        rlv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
//                mPresenter.loadData();
            }

            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
//                mPresenter.loadMore();
            }
        });
    }
}
