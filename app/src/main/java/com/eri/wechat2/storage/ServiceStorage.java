package com.eri.wechat2.storage;

import android.content.Context;

import com.eri.wechat2.InterfaceApp;
import com.eri.wechat2.RequestBase;
import com.eri.wechat2.ResponseBase;

public class ServiceStorage implements InterfaceApp {

    private static ServiceStorage mInstance;
    private final Context mContext;

    public static ServiceStorage getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ServiceStorage(context);
        }
        return mInstance;
    }

    private ServiceStorage(Context context) {
        mContext = context;
//        mService = ServiceSmack.getInstance(context);
    }
    @Override
    public void regist(RequestBase request, ResponseListener<ResponseBase> responseListener) {
        ResponseBase response = new ResponseBase();
        response.setMessage("666");
        response.setStatus(ResponseBase.Status.ERROR);
        responseListener.onResponse(response);
    }
}
