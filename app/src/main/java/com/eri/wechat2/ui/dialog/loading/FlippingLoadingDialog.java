package com.eri.wechat2.ui.dialog.loading;

import android.app.Dialog;
import android.content.Context;

import com.eri.wechat2.R;


public class FlippingLoadingDialog extends Dialog {

	private FlippingImageView mFivIcon;
	private HandyTextView mHtvText;
	private String mText;

	public FlippingLoadingDialog(Context context, String text) {
		super(context);
		mText = text;
		init();
	}

	//由于父类的onbackpressed子类不需要实现，所以这里重写父类方法
	@Override
	public void onBackPressed() {
		if(this.isShowing())
		dismiss();
	}
	private void init() {
		setContentView(R.layout.loading_diloag);
		mFivIcon = (FlippingImageView) findViewById(R.id.loadingdialog_fiv_icon);
		mHtvText = (HandyTextView) findViewById(R.id.loadingdialog_htv_text);
		mFivIcon.startAnimation();
		mHtvText.setText(mText);
	}

	public void setText(String text) {
		mText = text;
		mHtvText.setText(mText);
	}

	@Override
	public void dismiss() {
		if (isShowing()) {
			super.dismiss();
		}
	}
}
