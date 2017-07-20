package com.eri.wechat2.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eri.wechat2.MsgApplication;
import com.eri.wechat2.R;

/**
 * Toast统一管理类
 *
 */
public class T {
    private T() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static Context getContext() {
        return MsgApplication.getInstance();
    }
    public static void showShort(CharSequence message) {
        showToast(message.toString());
    }

    public static void showShort(int msgId) {
        showToast(msgId);
    }

    /** 显示自定义Toast提示(来自res) **/
    private static void showToast(int resId) {
        View toastRoot = LayoutInflater.from(getContext()).inflate(
                R.layout.app_toast, null);
        ((TextView) toastRoot.findViewById(R.id.toast_text)).setText(getContext().getString(resId));
        Toast toast = new Toast(getContext());
        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastRoot);
        toast.show();
    }

    /** 显示自定义Toast提示(来自String) **/
    private static void showToast(String text) {
        View toastRoot = LayoutInflater.from(getContext()).inflate(
                R.layout.app_toast, null);
        ((TextView) toastRoot.findViewById(R.id.toast_text)).setText(text);
        Toast toast = new Toast(getContext());
        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastRoot);
        toast.show();
    }


}