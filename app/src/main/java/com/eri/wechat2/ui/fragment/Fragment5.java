package com.eri.wechat2.ui.fragment;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.eri.wechat2.R;
import com.eri.wechat2.task.AsyncLoadingTask;
import com.eri.wechat2.ui.dialog.LoadingDialog;
import com.eri.wechat2.ui.fragment.base.BaseFragment;
import com.eri.wechat2.utils.DebugLog;
import com.eri.wechat2.utils.HttpURLConnectionUtils;
import com.eri.wechat2.utils.T;

import org.json.JSONObject;


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
        testHttpRequest();
    }

    private void testHttpRequest(){
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
                DebugLog.json("---------------");
            }

            @Override
            public void onStart() {
                LoadingDialog.Builder loadBuilder = new LoadingDialog.Builder(mActivity)
                        .setMessage("loading...")
                        .setCancelable(false)
                        .setCancelOutside(false);
                loadingDialog = loadBuilder.create();
                loadingDialog.show();
            }

            @Override
            public void onFinished() {
                loadingDialog.dismiss();
            }
        });
    }
}
