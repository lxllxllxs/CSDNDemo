package com.lxl.csdndemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lxl.csdndemo.ui.MyFragment;

/**
 * Created by Administrator on 2016/4/29.
 */
public class MyAdapter  extends FragmentPagerAdapter {
	int count =0;
	private  static final String TAG="MyAdapter_getItem";
	public static final String[] TITLES = new String[]{"业界", "移动", "研发", "云计算"};


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