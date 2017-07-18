package com.eri.wechat2.ui.activity.base;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Toast;

import com.eri.wechat2.R;
import com.eri.wechat2.ui.dialog.loading.FlippingLoadingDialog;
import com.eri.wechat2.ui.dialog.loading.HandyTextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {
        /**
         * 记录处于前台的Activity
         */
        private static BaseActivity mForegroundActivity = null;
        /**
         * 记录所有活动的Activity
         */
        private static final List<BaseActivity> mActivities = new LinkedList<BaseActivity>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            initView();
            initFindViewById();
            initData();
            initEvent();

            // initActionBar();
        }



        @Override
        protected void onResume() {
            mForegroundActivity = this;
            super.onResume();
        }

        @Override
        protected void onPause() {
            mForegroundActivity = null;
            super.onPause();
        }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.anmi_in_right_left,R.anim.anmi_out_right_left);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_in_left_right,R.anim.anit_out_left_right);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        overridePendingTransition(R.anim.anmi_in_right_left, R.anim.anmi_out_right_left);
    }
        abstract protected void initView();

        abstract protected void initData();

        abstract protected void initFindViewById();

        abstract protected void initEvent();

        protected void initActionBar() {

        }

        /**
         * 关闭所有Activity
         */
        public static void finishAll() {
            List<BaseActivity> copy;
            synchronized (mActivities) {
                copy = new ArrayList<BaseActivity>(mActivities);
            }
            for (BaseActivity activity : copy) {
                activity.finish();
            }
        }

        /**
         * 关闭所有Activity，除了参数传递的Activity
         */
        public static void finishAll(BaseActivity except) {
            List<BaseActivity> copy;
            synchronized (mActivities) {
                copy = new ArrayList<BaseActivity>(mActivities);
            }
            for (BaseActivity activity : copy) {
                if (activity != except)
                    activity.finish();
            }
        }

        /**
         * 是否有启动的Activity
         */
        public static boolean hasActivity() {
            return mActivities.size() > 0;
        }

        /**
         * 获取当前处于前台的activity
         */
        public static BaseActivity getForegroundActivity() {
            return mForegroundActivity;
        }

        /**
         * 获取当前处于栈顶的activity，无论其是否处于前台
         */
        public static BaseActivity getCurrentActivity() {
            List<BaseActivity> copy;
            synchronized (mActivities) {
                copy = new ArrayList<BaseActivity>(mActivities);
            }
            if (copy.size() > 0) {
                return copy.get(copy.size() - 1);
            }
            return null;
        }


        /**
         * 退出应用
         */
        public void exitApp() {
            finishAll();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }


