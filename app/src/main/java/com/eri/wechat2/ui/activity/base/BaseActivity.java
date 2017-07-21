package com.eri.wechat2.ui.activity.base;


import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.eri.wechat2.BackgroundManager;
import com.eri.wechat2.R;

import com.eri.wechat2.ui.view.SwipeLayout;
import com.eri.wechat2.utils.DebugLog;

public abstract class BaseActivity extends AppCompatActivity {
    private SwipeLayout swipeLayout;
    public static boolean isForeground = false;
    private Handler handler = new Handler();
    private ConnectionRunnable mConnectionRunnable = new ConnectionRunnable();

    private BackgroundManager.AppLifecycleListener appLifecycleListener = new BackgroundManager.AppLifecycleListener() {

        @Override
        public void onBecameForeground() {
            isForeground = true;
            initForeground();
        }

        @Override
        public void onBecameBackground() {
            isForeground = false;
            handler.post(mConnectionRunnable);
        }
    };

    private void initForeground() {
        handler.removeCallbacks(mConnectionRunnable);
        DebugLog.d(BaseActivity.this.getClass().getSimpleName() + ":App in initForeground!");
    }

    private  class ConnectionRunnable implements Runnable {
        @Override
        public void run() {
            DebugLog.d(BaseActivity.this.getClass().getSimpleName() + ":App in background!");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initFindViewById();
        initData();
        initEvent();
        initSwipView();
        initAppLifeCycle();
    }
    private void initAppLifeCycle(){
        if (BackgroundManager.getInstance() != null) {
            BackgroundManager.getInstance().registerListener(appLifecycleListener);
        }
    }
    private void initSwipView() {
        swipeLayout = new SwipeLayout(this);
        swipeLayout.setSwipeAnyWhere(false);
        swipeLayout.setSwipeEnabled(isSwipeEnabled());
    }
    protected boolean isSwipeEnabled() {
        return true;
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        swipeLayout.replaceLayer(this);
    }
    @Override
    public void finish() {
        if (!swipeLayout.isSwipeFinished()) {
            swipeLayout.cancelPotentialAnimation();
        }
        super.finish();
        if (isTaskRoot()) {
            overridePendingTransition(0, R.anim.anit_out_left_right);
        } else {
            overridePendingTransition(R.anim.anim_in_left_right,R.anim.anit_out_left_right);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.anmi_in_right_left,R.anim.anmi_out_right_left);
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

    }


