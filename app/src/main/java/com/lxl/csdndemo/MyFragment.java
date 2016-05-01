package com.lxl.csdndemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.CommonException.CommonException;
import com.bean.NewsItem;
import com.biz.Constaint;
import com.biz.NewsItemBiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//@SuppressLint("ValidFragment")
public class MyFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener
{
	private final  static String MyFragmentTAG="MyFragmentCreated";
	private NewItemAdapter newItemAdapter;
	private int newsType = Constaint.NEW_TYPE_YEJIE;
	private List<NewsItem> mDatas = new ArrayList<NewsItem>();
	private ListView lv;
	private NewsItemBiz newsItemBiz;

	private SwipeRefreshLayout swipeRefreshLayout;

	public MyFragment(){};

	public MyFragment(int newsType)
	{	Log.d(MyFragmentTAG,newsType+"");
		this.newsType = newsType;
		newsItemBiz=new NewsItemBiz();

	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		swipeRefreshLayout=(SwipeRefreshLayout)getView().findViewById(R.id.myfragment_refreshlayout);
		swipeRefreshLayout.setOnRefreshListener(this);

		newItemAdapter=new NewItemAdapter(getContext(),mDatas);
		lv=(ListView)getView().findViewById(R.id.myfragment_lv);
		lv.setAdapter(newItemAdapter);

		//getView().findViewById(R.id.testButton).setOnClickListener(onclicklistener);
			new asyncTask().execute();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.myfragemt, null);
		return view;
	}



	@Override
	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// 停止刷新
				swipeRefreshLayout.setRefreshing(false);
			}
		}, 5000); // 5秒后发送消息，停止刷新

	}

	View.OnClickListener onclicklistener=new View.OnClickListener() {
		@Override
		public void onClick(View v) {

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						NewsItemBiz NI=new NewsItemBiz();
						List<NewsItem> list;
						list=NI.getNewsItem(Constaint.NEW_TYPE_YEJIE,1);
						Log.d("NewItemSize",list.size()+"");
						for (NewsItem newItem:list){
							Log.d("TITLE",newItem.getTitle());
						}
					} catch (CommonException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

		}
	};


	class asyncTask extends AsyncTask<Void,Void,Void>{


		 @Override
		 protected void onPostExecute(Void aVoid) {
			 newItemAdapter.addAll(mDatas);
			newItemAdapter.notifyDataSetChanged();

		 }

		 @Override
		 protected Void doInBackground(Void... params) {

			 try {
				 List<NewsItem> newsItemList=newsItemBiz.getNewsItem(newsType,1);
				 mDatas=newsItemList;

			 } catch (CommonException e) {
				 e.printStackTrace();
			 } catch (IOException e) {
				 e.printStackTrace();
			 }


			 return null;
		 }
	 }


}
