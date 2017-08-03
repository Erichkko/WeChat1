package com.eri.wechat2.ui.activity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eri.wechat2.R;
import com.eri.wechat2.ui.activity.base.BaseActivity;
import com.eri.wechat2.ui.view.bannerview.CustomBanner;
import com.eri.wechat2.utils.T;

import java.util.ArrayList;


public class BannerActivity extends BaseActivity {
    private CustomBanner mBanner;
    private TextView mTitle;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_banner);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initFindViewById() {
        mBanner = (CustomBanner) findViewById(R.id.banner);
        mTitle = (TextView) findViewById(R.id.tv_title);

        ArrayList<String> images = new ArrayList<>();
        images.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3778456200,3076998411&fm=23&gp=0.jpg");
        images.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3535338527,4000198595&fm=23&gp=0.jpg");
        images.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1017904219,2460650030&fm=23&gp=0.jpg");
        images.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2863927798,667335035&fm=23&gp=0.jpg");
        images.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3885596348,1190704919&fm=23&gp=0.jpg");
        images.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1597254274,1405139366&fm=23&gp=0.jpg");
        images.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3513269361,2662598514&fm=23&gp=0.jpg");

        setBean(images);
    }

    //设置普通指示器
    private void setBean(final ArrayList beans) {
        mBanner.setPages(new CustomBanner.ViewCreator<String>() {
            @Override
            public View createView(Context context, int position) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }

            @Override
            public void updateUI(Context context, View view, int position, String entity) {
                Glide.with(context).load(entity).into((ImageView) view);
            }
        }, beans)
                //设置指示器为普通指示器
                .setIndicatorStyle(CustomBanner.IndicatorStyle.ORDINARY)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setIndicatorRes(R.drawable.shape_point_select, R.drawable.shape_point_unselect)
                //设置指示器的方向
                .setIndicatorGravity(CustomBanner.IndicatorGravity.RIGHT)
                //设置指示器的指示点间隔
                .setIndicatorInterval(5)
                //设置自动翻页
                .startTurning(5000);

        mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTitle.setText("测试标题:"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBanner.setOnPageClickListener(new CustomBanner.OnPageClickListener() {
            @Override
            public void onPageClick(int position, Object o) {
                T.showShort("当前选择的是第:"+position+" 个");
            }
        });
    }

//    //设置普通指示器
//    private void setBean(final ArrayList beans) {
//        mBanner.setPages(new CustomBanner.ViewCreator<String>() {
//            @Override
//            public View createView(Context context, int position) {
//                ImageView imageView = new ImageView(context);
//                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                return imageView;
//            }
//
//            @Override
//            public void updateUI(Context context, View view, int position, String entity) {
//                Glide.with(context).load(entity).into((ImageView) view);
//            }
//        }, beans)
//                //设置指示器为普通指示器
//                .setIndicatorStyle(CustomBanner.IndicatorStyle.NUMBER)
//                //设置指示器的方向
//                .setIndicatorGravity(CustomBanner.IndicatorGravity.RIGHT)
//                //设置自动翻页
//                .startTurning(5000);
//    }

//    //设置没有指示器
//    private void setBean(final ArrayList beans) {
//        mBanner.setPages(new CustomBanner.ViewCreator<String>() {
//            @Override
//            public View createView(Context context, int position) {
//                ImageView imageView = new ImageView(context);
//                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                return imageView;
//            }
//
//            @Override
//            public void updateUI(Context context, View view, int position, String entity) {
//                Glide.with(context).load(entity).into((ImageView) view);
//            }
//        }, beans)
//                //设置没有指示器
//                .setIndicatorStyle(CustomBanner.IndicatorStyle.NONE)
//                //设置自动翻页
//                .startTurning(5000);
//    }

    @Override
    protected void initEvent() {

    }
}
