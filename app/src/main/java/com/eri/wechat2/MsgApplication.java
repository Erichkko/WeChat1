
package com.eri.wechat2;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;


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
        initX5Webview();
    }
    private void initCrashHandler(){
        //1,获取异常捕获日志
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }

    private void initX5Webview(){
        //x5内核初始化接口1
        QbSdk.initX5Environment(getApplicationContext(),  null);
    }


}
