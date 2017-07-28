package com.eri.wechat2.ui.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.eri.wechat2.R;
import com.eri.wechat2.ui.activity.base.BaseActivity;
import com.eri.wechat2.ui.fragment.SegmentFragment1;
import com.eri.wechat2.ui.fragment.SegmentFragment2;
import com.eri.wechat2.utils.T;

import info.hoang8f.android.segmented.SegmentedGroup;


public class SegmentActivity extends BaseActivity {
    private SegmentedGroup segmented2;
    private FrameLayout flContent;
    private SegmentFragment1 fragment1;
    private SegmentFragment2 fragment2;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_segment);
    }

    @Override
    protected void initData() {
        fragment1 = new SegmentFragment1();
        fragment2 = new SegmentFragment2();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, fragment2).commit();

    }

    @Override
    protected void initFindViewById() {
        segmented2 = (SegmentedGroup) findViewById(R.id.segmented2);
        flContent = (FrameLayout) findViewById(R.id.fl_content);

    }

    @Override
    protected void initEvent() {
        segmented2.setOnCheckedChangeListener(new CheckListen());
    }

    private  class CheckListen implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            switch (i){
                case R.id.button21:
                    FragmentTransaction ft1 = getSupportFragmentManager()
                            .beginTransaction();
                    ft1.replace(R.id.fl_content, fragment1).commit();
                    break;
                case R.id.button22:
                    FragmentTransaction ft2 = getSupportFragmentManager()
                            .beginTransaction();
                    ft2.replace(R.id.fl_content, fragment2).commit();
                    break;
            }
        }
    }
}
