package com.lxl.csdndemo;

import android.os.Bundle;

		import android.annotation.SuppressLint;
		import android.os.Bundle;
		import android.support.v4.app.Fragment;
		import android.view.LayoutInflater;
		import android.view.View;
		import android.view.ViewGroup;
		import android.widget.TextView;


//@SuppressLint("ValidFragment")
public class MyFragment extends Fragment
{


	private int newsType = 0;
	public  MyFragment(){};

	public MyFragment(int newsType)
	{
		this.newsType = newsType;
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.tab, null);
		TextView tip = (TextView) view.findViewById(R.id.tab_tv);
		tip.setText(MyAdapter.TITLES[newsType]);
		return view;
	}


}
