package com.lxl.csdndemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bean.NewsItem;
import com.lxl.csdndemo.DB.NewsItemDAO;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 */
public class testAty extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		(findViewById(R.id.btnC)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						NewsItemDAO dao=new NewsItemDAO(getApplicationContext());
						List<NewsItem> newsItemList=dao.query(0,1);
						System.out.print(newsItemList.size()+"");
						Log.d("TESTATY",newsItemList.size()+"");
					}
				}).start();
			}
		});
	}
}
