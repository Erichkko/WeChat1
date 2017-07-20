package com.eri.wechat2.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.eri.wechat2.R;
import com.eri.wechat2.ui.activity.base.BaseActivity;
import com.eri.wechat2.ui.adapter.HotelEntityAdapter;
import com.eri.wechat2.ui.adapter.SectionedSpanSizeLookup;
import com.eri.wechat2.ui.entity.HotelEntity;
import com.eri.wechat2.utils.JsonUtils;

public class Test1Activity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private HotelEntityAdapter mAdapter;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_main_1);
    }

    @Override
    protected void initData() {
        mAdapter = new HotelEntityAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this,1);
        //设置header
        manager.setSpanSizeLookup(new SectionedSpanSizeLookup(mAdapter,manager));
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        HotelEntity entity = JsonUtils.analysisJsonFile(this,"json");
        mAdapter.setData(entity.allTagsList);
    }

    @Override
    protected void initFindViewById() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }


    @Override
    protected void initEvent() {

    }


}
