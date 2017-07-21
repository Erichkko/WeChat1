package com.eri.wechat2;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

// based on https://gist.github.com/steveliles/11116937
public class BackgroundManager implements Application.ActivityLifecycleCallbacks {

    public static final long BACKGROUND_DELAY = 400;

    private static BackgroundManager sInstance;

    public interface AppLifecycleListener {
        void onBecameForeground();
        void onBecameBackground();
    }

    private boolean mInBackground = true;
    private boolean needShowLocalLogin = false;
    private final List<AppLifecycleListener> AppLifecycleListeners = new ArrayList<>();
    private final Handler mBackgroundDelayHandler = new Handler();
    private Runnable mBackgroundTransition;
    public static BackgroundManager getInstance() {
    	return sInstance;
    }
    
    public static BackgroundManager init(Application application) {
        if (sInstance == null) {
            sInstance = new BackgroundManager(application);
        }
        return sInstance;
    }

    private BackgroundManager(Application application) {
        application.registerActivityLifecycleCallbacks(this);
    }

	public void registerListener(AppLifecycleListener AppLifecycleListener) {
		if(!AppLifecycleListeners.contains(AppLifecycleListener))
			AppLifecycleListeners.add(AppLifecycleListener);
    }

    public void unregisterListener(AppLifecycleListener AppLifecycleListener) {
        AppLifecycleListeners.remove(AppLifecycleListener);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (mBackgroundTransition != null) {
            mBackgroundDelayHandler.removeCallbacks(mBackgroundTransition);
            mBackgroundTransition = null;
        }

        if (mInBackground) {
            mInBackground = false;
            notifyOnBecameForeground();
        }
    }

    private void notifyOnBecameForeground() {
        for (AppLifecycleListener AppLifecycleListener : AppLifecycleListeners) {
            try {
                AppLifecycleListener.onBecameForeground();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        if (!mInBackground && mBackgroundTransition == null) {
            mBackgroundTransition = new Runnable() {
                @Override
                public void run() {
                    mInBackground = true;
                    mBackgroundTransition = null;
                    notifyOnBecameBackground();
                }
            };
            mBackgroundDelayHandler.postDelayed(mBackgroundTransition, BACKGROUND_DELAY);
        }
    }

    private void notifyOnBecameBackground() {
        for (AppLifecycleListener AppLifecycleListener : AppLifecycleListeners) {
            try {
                AppLifecycleListener.onBecameBackground();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {}

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}

    @Override
    public void onActivityStarted(Activity activity) {}

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

    @Override
    public void onActivityDestroyed(Activity activity) {}

    public boolean isNeedShowLocalLogin() {
        return needShowLocalLogin;
    }

    public void setNeedShowLocalLogin(boolean needShowLocalLogin) {
        this.needShowLocalLogin = needShowLocalLogin;
    }
}
