package com.eri.wechat2.ui.view.bannerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.eri.wechat2.R;

@SuppressLint("AppCompatCustomView")
public class NumberIndicator extends TextView {

    public NumberIndicator(Context context) {
        super(context);
        setTextColor(Color.WHITE);
        setTextSize(14);
        setBackgroundResource(R.drawable.text_indicator_bg);
        int padding = DensityUtils.dp2px(context, 5);
        setPadding(padding,padding,padding,padding);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //保证TextIndicator的宽高一致(正方形)
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
