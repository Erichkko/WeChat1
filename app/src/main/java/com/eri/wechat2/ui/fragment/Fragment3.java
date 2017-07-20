package com.eri.wechat2.ui.fragment;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.eri.wechat2.R;
import com.eri.wechat2.ui.activity.Test1Activity;
import com.eri.wechat2.ui.fragment.base.BaseFragment;


public class Fragment3 extends BaseFragment{
    private Button bt_swip_back;

    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frag_fragment3,null);
    }

    @Override
    protected void initFindViewById(View view) {
        bt_swip_back = (Button)view.findViewById(R.id.bt_swip_back);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        bt_swip_back.setOnClickListener(new BtnClickEvent());
    }

    private class BtnClickEvent implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id){
                case R.id.bt_swip_back:
                    Intent intent = new Intent();
                    intent.setClass(mActivity, Test1Activity.class);
                    startActivity(intent);
                    break;
                default:
                    break;

            }
        }
    }
}
