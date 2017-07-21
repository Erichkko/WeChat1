
package com.eri.wechat2;

import android.app.Application;


public class MsgApplication extends Application {

    private static MsgApplication instance;

    public static MsgApplication getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initCrashHandler();
        BackgroundManager.init(this);
    }
    private void initCrashHandler(){
        //1,获取异常捕获日志
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }



}
