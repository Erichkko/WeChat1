package com.eri.wechat2.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.eri.wechat2.R;
import com.eri.wechat2.ui.activity.base.BaseActivity;
import com.eri.wechat2.ui.constant.Constants;
import com.eri.wechat2.ui.dialog.LoadingDialog;
import com.eri.wechat2.ui.view.webview.X5WebView;
import com.eri.wechat2.utils.DebugLog;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class WebViewActivity extends BaseActivity {
	private TextView mTvTitle;
	private String mUrl;
	private X5WebView mWebView;
	private LinearLayout rightLayout;
	private ImageView imageRight;
	private LoadingDialog loadView;

	@Override
	protected void initView() {
		setContentView(R.layout.web_view);
	}

	@Override
	protected void initData() {
		mUrl = getIntent().getStringExtra(Constants.EXTRA_PREFIX);
		mWebView.loadUrl(mUrl);
	}

	@Override
	protected void initFindViewById() {
//		mTvTitle = (TextView) findViewById(R.id.tvTitle);
//		//mTvTitle.setText(getString(R.string.web));
//		findViewById(R.id.leftLayout).setVisibility(View.VISIBLE);
//		rightLayout = (LinearLayout) findViewById(R.id.rightLayout);
//		imageRight = (ImageView) findViewById(R.id.imageRight);
//		rightLayout.setVisibility(View.VISIBLE);
//		imageRight.setVisibility(View.VISIBLE);
//		imageRight.setImageResource(R.drawable.nav_refresh);
		mWebView = (X5WebView) findViewById(R.id.we_bView);
		loadView = LoadingDialog.getDefaultLoading(this);
		initX5Webview();
	}

	@Override
	protected void initEvent() {

	}


	private void initX5Webview() {

		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (url.startsWith("tel:")) {
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri
							.parse(url));
					startActivity(intent);
					return  true;
				}

				return false;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				DebugLog.e("url == "+url);
				loadView.dismiss();
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				loadView.show();

			}

		});

		mWebView.setWebChromeClient(new com.tencent.smtt.sdk.WebChromeClient(){
			@Override
			public boolean onJsAlert(WebView arg0, String arg1, String arg2,
                                     com.tencent.smtt.export.external.interfaces.JsResult arg3) {
				return super.onJsAlert(arg0, arg1, arg2, arg3);
			}
			@Override
			public void onReceivedTitle(WebView webView, String title) {
				super.onReceivedTitle(webView, title);
				DebugLog.e("title == "+ title);
		}
		});
		mWebView.addJavascriptInterface(new JSInterface(),"Android");
	}

	public void onLeftClicked(View view) {
		if (mWebView.canGoBack()){
			mWebView.goBack();
		}else {
			finish();
		}
	}

	public void onRightClicked(View view) {
		mWebView.reload();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
    private class JSInterface {
        //JS需要调用的方法
        @JavascriptInterface
        public void test() {

        }
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

	}


}
