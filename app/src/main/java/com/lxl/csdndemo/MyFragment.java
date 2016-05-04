package com.lxl.csdndemo;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.CommonException.CommonException;
import com.bean.NewsItem;
import com.biz.Constaint;
import com.biz.NewsItemBiz;
import com.canyinghao.canrefresh.CanRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//@SuppressLint("ValidFragment")
public class MyFragment extends Fragment implements CanRefreshLayout.OnRefreshListener,CanRefreshLayout.OnLoadMoreListener
{
	private final  static String TAG="MF_Aty_Created";
	private NewItemAdapter newItemAdapter;


	private int newsType = Constaint.NEW_TYPE_YEJIE;
	private List<NewsItem> mDatas= new ArrayList<NewsItem>(); ;
	private NewsItemBiz newsItemBiz;
	private ListView listView ;
	private CanRefreshLayout canRefreshLayout;
	public MyFragment(){};
	public MyFragment(int newsType){
		this.newsType = newsType;
		newsItemBiz=new NewsItemBiz();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view= inflater.inflate(R.layout.myfragemt,container,false);
		canRefreshLayout=(CanRefreshLayout)view.findViewById(R.id.mf_reflayout);
		canRefreshLayout.setOnLoadMoreListener(this);
		canRefreshLayout.setOnRefreshListener(this);
		listView=(ListView)view.findViewById(R.id.can_content_view);
		newItemAdapter=new NewItemAdapter(getContext(),mDatas);

		listView.setAdapter(newItemAdapter);



		return view;

	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		new asyncTask().execute();
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

	@Override
	public void onLoadMore() {
		Log.d(TAG,"onLoadMore");
	}

	@Override
	public void onRefresh() {
		new asyncTask().execute();
		canRefreshLayout.refreshComplete();
	}

	@TargetApi(Build.VERSION_CODES.CUPCAKE)
	class asyncTask extends AsyncTask<Void,Void,Void>{

		 @Override
		 protected void onPostExecute(Void aVoid) {
			 newItemAdapter.clear();
			 newItemAdapter.addAll(mDatas);
			newItemAdapter.notifyDataSetChanged();

		 }

		 @Override
		 protected Void doInBackground(Void... params) {

			 try {
//				 if (!(mDatas.isEmpty())) {
//					 mDatas.clear();
//				 }
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
