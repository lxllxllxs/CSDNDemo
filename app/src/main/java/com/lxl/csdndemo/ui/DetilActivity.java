package com.lxl.csdndemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.lxl.csdndemo.R;

/**
 * Created by Administrator on 2016/5/5.
 */
public class DetilActivity extends Activity {
	private String url;
	private android.webkit.WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		 url=getIntent().getStringExtra("url");
		init();

	}

	public  void init(){
		webView=(android.webkit.WebView)findViewById(R.id.wb_webviwe);
		WebSettings webSettings=webView.getSettings();

		webSettings.setJavaScriptEnabled(true);
		webSettings.setAllowContentAccess(true);
		webSettings.setBuiltInZoomControls(true);

		webView.loadUrl(url);
		webView.setWebViewClient(new webClient());

	}

	class  webClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
			view.loadUrl(url);

			return true;
		}
	}

}
