package com.eri.wechat2.ui.dialog.loading;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class HandyTextView extends TextView {

	public HandyTextView(Context context) {
		super(context);
	}

	public HandyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HandyTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void setText(CharSequence text, BufferType type) {
		if (text == null) {
			text = "";
		}
		super.setText(text, type);
	}
}
