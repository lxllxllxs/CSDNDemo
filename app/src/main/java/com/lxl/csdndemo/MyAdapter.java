package com.lxl.csdndemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by Administrator on 2016/4/29.
 */
public class MyAdapter  extends FragmentPagerAdapter {
	int count =0;
	private  static final String TAG="MyAdapter_getItem";
	public static final String[] TITLES = new String[]{"业 界", "移 动", "研 发", "云计算"};


	public MyAdapter(FragmentManager fm) {
		super(fm);
	}


	@Override
	public Fragment getItem(int arg0) {
		MyFragment fragment = new MyFragment(arg0);
		Log.d(TAG,count+"");
		count++;
		return fragment;
	}


	@Override
	public CharSequence getPageTitle(int position) {
		return TITLES[position % TITLES.length];
	}


	@Override
	public int getCount() {
		return TITLES.length;
	}
}