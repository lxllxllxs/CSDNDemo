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
import android.widget.Toast;

import com.CommonException.CommonException;
import com.bean.NewsItem;
import com.biz.Constaint;
import com.biz.NewsItemBiz;
import com.canyinghao.canrefresh.CanRefreshLayout;
import com.lxl.csdndemo.DB.NewsItemDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//@SuppressLint("ValidFragment")
public class MyFragment extends Fragment implements CanRefreshLayout.OnRefreshListener,CanRefreshLayout.OnLoadMoreListener
{
	private final  static String TAG="MF_Aty_Created";
	private NewItemAdapter newItemAdapter;

	private final static  int LOAD_REFRESH=1;
	private final  static  int LOAD_MORE=2;
	private int newsType = Constaint.NEW_TYPE_YEJIE;
	private  int currentPage=1;
	private  final  static  int NET_SERVICE_ERROR=0x123;
	private  final static  int NO_NET_ERROR=0x121;
	private  final static  int LOAD_REFRESH_SUCCESS=0x120;

	private  boolean isLoadFromNetWork;


	private List<NewsItem> mDatas= new ArrayList<NewsItem>(); ;
	private NewsItemBiz newsItemBiz;
	private ListView listView ;
	private CanRefreshLayout canRefreshLayout;
	private NewsItemDAO newsItemDAO;
	private  View view;


	public MyFragment(){};
	public MyFragment(int newsType1){
		Log.d("NEWTYPE!",""+newsType1);
		this.newsType = newsType1;
		newsItemBiz=new NewsItemBiz();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		view= inflater.inflate(R.layout.myfragemt,container,false);
		return view;

	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{

		super.onActivityCreated(savedInstanceState);
		canRefreshLayout=(CanRefreshLayout)view.findViewById(R.id.mf_reflayout);
		canRefreshLayout.setOnLoadMoreListener(this);
		canRefreshLayout.setOnRefreshListener(this);
		listView=(ListView)view.findViewById(R.id.can_content_view);
		listView.setAdapter(newItemAdapter);
		newItemAdapter=new NewItemAdapter(getContext(),mDatas);
		onRefresh();

	}

	@Override
	public void onLoadMore() {
		new asyncTask().execute(LOAD_MORE);
		canRefreshLayout.loadMoreComplete();

	}

	@Override
	public void onRefresh() {
		new asyncTask().execute(LOAD_REFRESH);
		listView.setAdapter(newItemAdapter);
		canRefreshLayout.refreshComplete();
	}


	@TargetApi(Build.VERSION_CODES.CUPCAKE)
	class asyncTask extends AsyncTask<Integer,Void,Integer>{

		 @Override
		 protected void onPostExecute(Integer result) {
			/* switch (result){
				 case NET_SERVICE_ERROR:
					 //异步 uI可能报错
					 Toast.makeText(getContext(),"网络服务错误",Toast.LENGTH_SHORT).show();
					 break;
				 case NO_NET_ERROR:
					 newItemAdapter.notifyDataSetChanged();
					 break;
				 case LOAD_REFRESH_SUCCESS:
					 newItemAdapter.notifyDataSetChanged();
					 break;
			 }*/
			 newItemAdapter.notifyDataSetChanged();
		 }

		 @Override
		 protected Integer doInBackground(Integer... params) {
			switch (params[0]){
				case LOAD_MORE:
					loadMore();
					break;
				case LOAD_REFRESH:
					refreshData();
					break;
			}

			 return -1 ;
		 }
	 }


	public void  loadMore(){
		currentPage+=1;
		if (isLoadFromNetWork){
			try {
				List<NewsItem> newslist=newsItemBiz.getNewsItem(newsType,currentPage);
				newsItemDAO.deleteAll(newsType);
				newsItemDAO.add(newslist);
				newItemAdapter.setData(newslist);

			} catch (CommonException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		//无网 从数据库加载
		}else {
			List<NewsItem> newslist=newsItemDAO.query(newsType,currentPage);
			mDatas=newslist;
			newItemAdapter.setData(mDatas);

		}
	}





	public  int refreshData() {
		newsItemDAO=new NewsItemDAO(getContext());
		//有无网络，有则从网络加载，无则从数据库加载
		//Log.d("ISCONNECTED",NetUtil.isConnected(getContext())+"");
		if (NetUtil.isConnected(getContext())) {
			isLoadFromNetWork=true;
			try {
				List<NewsItem> newsItemList = newsItemBiz.getNewsItem(newsType, currentPage);
				newItemAdapter.setData(newsItemList);
				//清除数据库
				newsItemDAO.deleteAll(newsType);
				//向数据库装入数据
				newsItemDAO.add(newsItemList);

			} catch (CommonException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//从数据库加载
		}else {
			isLoadFromNetWork=false;
			//Log.d("QUERY",newsType+"  "+currentPage+"");
			List<NewsItem> newsItemList=newsItemDAO.query(newsType,currentPage);
			mDatas=newsItemList;
			newItemAdapter.setData(mDatas);
			return NO_NET_ERROR;

		}return LOAD_REFRESH_SUCCESS ;
	}
}
