package com.eri.wechat2.utils;

import com.eri.wechat2.MsgApplication;

import android.content.Context;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import android.view.LayoutInflater;
import android.view.View;


public class UIUtils {

	/**
	 * 获取上下文
	 * 
	 * @return
	 */
	public static Context getContext() {
		return MsgApplication.getInstance();
	}

	public static View inflate(int resId) {
		return LayoutInflater.from(getContext()).inflate(resId, null);
	}

	/** 获取资源 */
	private static Resources getResources() {
		return getContext().getResources();
	}

	/** 获取文字 */
	public static String getString(int resId) {
		return getResources().getString(resId);
	}

	/** 获取文字数组 */
	public static String[] getStringArray(int resId) {
		return getResources().getStringArray(resId);
	}

	/** 获取dimen */
	public static int getDimens(int resId) {
		return getResources().getDimensionPixelSize(resId);
	}

	/** 获取drawable */
	public static Drawable getDrawable(int resId) {
		return getResources().getDrawable(resId);
	}

	/** 获取颜色 */
	public static int getColor(int resId) {
		return getResources().getColor(resId);
	}

	/** 获取颜色选择器 */
	public static ColorStateList getColorStateList(int resId) {
		return getResources().getColorStateList(resId);
	}


}
