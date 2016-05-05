package com.lxl.csdndemo.ui;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.lxl.csdndemo.R;
import com.lxl.csdndemo.adapter.MyAdapter;
import com.viewpagerindicator.TabPageIndicator;


public class MainActivity extends FragmentActivity
{
	private TabPageIndicator mIndicator ;
	private ViewPager mViewPager ;
	private FragmentPagerAdapter mAdapter ;
	private   int count=0;

	private  static final String TAG="MainActivity_Oncreate";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mIndicator = (TabPageIndicator) findViewById(R.id.id_indicator);
		mViewPager = (ViewPager) findViewById(R.id.id_pager);
		mAdapter = new MyAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
		mIndicator.setViewPager(mViewPager, 0);

	}




}