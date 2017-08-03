package com.eri.wechat2.ui.fragment;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.eri.wechat2.R;
import com.eri.wechat2.ui.activity.BannerActivity;
import com.eri.wechat2.ui.activity.SegmentActivity;
import com.eri.wechat2.ui.activity.Test1Activity;
import com.eri.wechat2.ui.activity.WebViewActivity;
import com.eri.wechat2.ui.constant.Constants;
import com.eri.wechat2.ui.fragment.base.BaseFragment;
import com.eri.wechat2.utils.T;


public class Fragment3 extends BaseFragment{
    private Button bt_swip_back;
    private Button bt_bt2;
    private Button bt_bt3;
    private Button bt_bt4;
    private Button bt_bt5;
    private Button bt_bt6;
    private Button bt_bt7;
    private Button bt_bt8;
    private Button bt_bt9;


    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frag_fragment3,null);
    }

    @Override
    protected void initFindViewById(View view) {
        bt_swip_back = (Button)view.findViewById(R.id.bt_swip_back);
        bt_bt2 = (Button)view.findViewById(R.id.bt_bt2);
        bt_bt3 = (Button)view.findViewById(R.id.bt_bt3);
        bt_bt4 = (Button)view.findViewById(R.id.bt_bt4);
        bt_bt5 = (Button)view.findViewById(R.id.bt_bt5);
        bt_bt6 = (Button)view.findViewById(R.id.bt_bt6);
        bt_bt7 = (Button)view.findViewById(R.id.bt_bt7);

//        bt_bt8 = (Button)view.findViewById(R.id.bt_bt8);
//        bt_bt9 = (Button)view.findViewById(R.id.bt_bt9);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        bt_swip_back.setOnClickListener(new BtnClickEvent());
        bt_bt2.setOnClickListener(new BtnClickEvent());
        bt_bt3.setOnClickListener(new BtnClickEvent());
        bt_bt4.setOnClickListener(new BtnClickEvent());
        bt_bt5.setOnClickListener(new BtnClickEvent());
        bt_bt6.setOnClickListener(new BtnClickEvent());
        bt_bt7.setOnClickListener(new BtnClickEvent());
    }

    private class BtnClickEvent implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            int id = view.getId();
            Intent intent = new Intent();
            switch (id){
                case R.id.bt_swip_back:
                    intent.setClass(mActivity, Test1Activity.class);
                    break;
                case R.id.bt_bt2:
                    intent.setClass(mActivity, SegmentActivity.class);
                    T.showShort("2");
                    break;
                case R.id.bt_bt3:
                    intent.setClass(mActivity, WebViewActivity.class);
                    intent.putExtra(Constants.EXTRA_PREFIX,"http://www.baidu.com");
                    T.showShort("3");
                    break;
                case R.id.bt_bt4:
                    intent.setClass(mActivity, BannerActivity.class);
                    T.showShort("4");
                    break;
                case R.id.bt_bt5:
                    T.showShort("5");
                    break;
                case R.id.bt_bt6:
                    T.showShort("6");
                    break;
                case R.id.bt_bt7:
                    T.showShort("7");
                    break;
                default:
                    break;

            }
            startActivity(intent);
        }
    }
}
