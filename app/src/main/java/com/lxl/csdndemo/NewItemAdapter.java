package com.lxl.csdndemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bean.NewsItem;

import java.util.List;

/**
 * Created by Administrator on 2016/5/1.
 */
public class NewItemAdapter extends BaseAdapter {

	private Context context;
	private List<NewsItem> NewItemList;

	public NewItemAdapter(Context context,List<NewsItem> NewItemList){
		this.context=context;
		this.NewItemList=NewItemList;
		//add();
	}

	@Override
	public int getCount() {
		return NewItemList.size();
	}

	@Override
	public NewsItem getItem(int position) {
		return  NewItemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void addAll(List<NewsItem> mdata){

		NewItemList.addAll(mdata);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=null;
		if(convertView==null){
			convertView=View.inflate(context,R.layout.list_item_layout,null);
			viewHolder=new ViewHolder();
			viewHolder.tv=(TextView)convertView.findViewById(R.id.list_item_tv);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
			NewsItem ni=NewItemList.get(position);
			viewHolder.tv.setText(ni.getTitle());

		return convertView ;
	}

	public  void add(){
		NewsItem Ni=new NewsItem();
		NewsItem new2=new NewsItem();
		NewsItem new3=new NewsItem();
		Ni.setTitle("i am the first");
		new2.setTitle("I am the second");
		new3.setTitle("I am the third");
		NewItemList.add(Ni);
		NewItemList.add(new2);
		NewItemList.add(new3);


	}



	private final class ViewHolder {
		private TextView tv;

	}
}
