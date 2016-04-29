package com.lxl.csdndemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Administrator on 2016/4/29.
 */
public class MyAdapter  extends FragmentPagerAdapter {


	public static final String[] TITLES = new String[]{"业界", "移动", "研发", "程序员杂志", "云计算"};


	public MyAdapter(FragmentManager fm) {
		super(fm);
	}


	@Override
	public Fragment getItem(int arg0) {
		MyFragment fragment = new MyFragment(arg0);
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