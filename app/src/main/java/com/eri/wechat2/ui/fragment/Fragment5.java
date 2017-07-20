package com.eri.wechat2.ui.fragment;


import android.view.LayoutInflater;
import android.view.View;

import com.eri.wechat2.R;
import com.eri.wechat2.ui.dialog.LoadingDialog;
import com.eri.wechat2.ui.fragment.base.BaseFragment;
import com.eri.wechat2.utils.DebugLog;
import com.eri.wechat2.utils.HttpURLConnectionUtils;
import com.eri.wechat2.utils.T;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Fragment5 extends BaseFragment{
    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frag_fragment4,null);
    }

    @Override
    protected void initFindViewById(View view) {
    }

    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
//        testHttpRequestGet();
        testHttpRequestPost();
    }

    private void testHttpRequestGet(){
//        T.show(getActivity(),"56666....");

        final String url = "http://qpi.zhenghongwy.com:5095/qpi/rest/ownerDynamicsInfo/getDynamicsList?pageNum=1&perSize=5&userId=95327&projectId=7";
        HttpURLConnectionUtils.doGet(url, new HttpURLConnectionUtils.HttpResponseCallBack() {
            LoadingDialog loadingDialog;
            @Override
            public void onSuccess(JSONObject response) {
                DebugLog.json(response.toString());
            }

            @Override
            public void onFailure() {
                DebugLog.json("----------onFailure-----");
            }

            @Override
            public void onStart() {
                DebugLog.e("-----------onStart----------------");
               loadingDialog = LoadingDialog.getDefaultLoading(mActivity);
               loadingDialog.show();
            }

            @Override
            public void onFinished() {
                DebugLog.e("-----------onFinished----------------");
                loadingDialog.dismiss();
            }
        });
    }
    private void testHttpRequestPost(){
        String url = "http://qpi.zhenghongwy.com:5095/qpi/rest/accessControlInfo/getDevice";
        Map<String,String> paraMap = new HashMap<>();
        //{deviceType=2, userId=95327}
        paraMap.put("deviceType","2");
        paraMap.put("userId","95327");
        HttpURLConnectionUtils.doPost(url, paraMap, new HttpURLConnectionUtils.HttpResponseCallBack() {
            LoadingDialog loadingDialog;
            @Override
            public void onSuccess(JSONObject response) {
                T.showShort("请求success...");
                DebugLog.e("--------------onSuccess------------------");
                DebugLog.json(response.toString());
            }

            @Override
            public void onFailure() {
                DebugLog.e("-----------onFailure------------");
            }

            @Override
            public void onStart() {
                DebugLog.e("-----------onStart------------");
                loadingDialog = LoadingDialog.getDefaultLoading(mActivity);
                loadingDialog.show();
            }

            @Override
            public void onFinished() {
                DebugLog.e("-----------onFinished------------");
                loadingDialog.dismiss();
            }
        });
    }
}
