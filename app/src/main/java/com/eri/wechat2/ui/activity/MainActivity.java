package com.eri.wechat2.ui.activity;

import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.eri.wechat2.R;
import com.eri.wechat2.ui.activity.base.BaseActivity;
import com.eri.wechat2.ui.fragment.Fragment1;
import com.eri.wechat2.ui.fragment.Fragment2;
import com.eri.wechat2.ui.fragment.Fragment3;
import com.eri.wechat2.ui.fragment.Fragment4;
import com.eri.wechat2.ui.fragment.Fragment5;


public class MainActivity extends BaseActivity {


    private FragmentTabHost mTabHost;
    private LayoutInflater mLayoutInflater;
    private Class mFragmentArray[] = { Fragment1.class, Fragment2.class, Fragment3.class,
            Fragment4.class,Fragment5.class };
    private int mImageViewArray[] = { R.drawable.bg_tabbar_home_selector, R.drawable.bg_tabbar_message_selector,
            R.drawable.bg_tabbar_profile_selector,R.drawable.bg_tabbar_discover_selector,R.drawable.bg_tabbar_fav_selector  };
    private int mTextviewArray[] = { R.string.tab_1_title, R.string.tab_2_title, R.string.tab_3_title,
            R.string.tab_4_title , R.string.tab_4_title };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        initTabHost();
    }

    @Override
    protected void initFindViewById() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    private void initTabHost(){
        mLayoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        int count = mFragmentArray.length;

        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(mTextviewArray[i]));
            tabSpec.setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
           mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.homepage_selected_bg);
        }
    }
    private View getTabItemView(int index) {
        View view = mLayoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setBackgroundResource(mImageViewArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(mTextviewArray[index]);
        textView.setTextColor(getResources().getColorStateList(
                R.color.tab_selector_tv_color));
        return view;
    }
}
