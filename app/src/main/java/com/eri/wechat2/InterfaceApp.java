package com.eri.wechat2;


public interface InterfaceApp {
    interface ResponseListener<T> {
        void onResponse(T response);
    }
    void regist(RequestBase request, ResponseListener<ResponseBase> responseListener);
}
