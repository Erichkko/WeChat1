package com.eri.wechat2.ui.activity.base;


import android.app.Activity;
import android.os.Bundle;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Toast;

import com.eri.wechat2.R;
import com.eri.wechat2.ui.dialog.loading.FlippingLoadingDialog;
import com.eri.wechat2.ui.dialog.loading.HandyTextView;


public class BaseActivity extends Activity {

    protected FlippingLoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingDialog = new FlippingLoadingDialog(this, "");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onLeftClicked(View v){
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @SuppressWarnings("unused")
    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onStop() {
        super.onStop();
        }


    protected void showLoadingDialog(int resId) {
        mLoadingDialog.setText(getString(resId));
        mLoadingDialog.show();
    }

    protected void showLoadingDialog(String text) {
        if (text != null) {
            mLoadingDialog.setText(text);
        }
        mLoadingDialog.show();
    }

    protected void setLoadingText(String text) {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.setText(text);
        }
    }

    protected void dismissLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    protected boolean isLoadingDialogShow() {
        return mLoadingDialog != null && mLoadingDialog.isShowing();
    }

    /** 显示自定义Toast提示(来自res) **/
    public void showToast(int resId) {
        View toastRoot = LayoutInflater.from(this).inflate(
                R.layout.app_toast, null);
        ((HandyTextView) toastRoot.findViewById(R.id.toast_text))
                .setText(getString(resId));
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastRoot);
        toast.show();
    }

    /** 显示自定义Toast提示(来自String) **/
    public void showToast(String text) {
        View toastRoot = LayoutInflater.from(this).inflate(
                R.layout.app_toast, null);
        ((HandyTextView) toastRoot.findViewById(R.id.toast_text)).setText(text);
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastRoot);
        toast.show();
    }

}
