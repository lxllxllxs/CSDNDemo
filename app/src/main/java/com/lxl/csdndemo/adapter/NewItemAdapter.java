package com.lxl.csdndemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bean.NewsItem;
import com.lxl.csdndemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * Created by Administrator on 2016/5/1.
 */
public class NewItemAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private Context context;
	private List<NewsItem> NewItemList;
	private ImageLoader imageLoader=ImageLoader.getInstance();
	private DisplayImageOptions options;



	public NewItemAdapter(Context context,List<NewsItem> NewItemList){
		this.context=context;
		this.NewItemList=NewItemList;
		mInflater=LayoutInflater.from(context);
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.images)
				.showImageForEmptyUri(R.drawable.images).showImageOnFail(R.drawable.images).cacheInMemory()
				.cacheOnDisc().displayer(new RoundedBitmapDisplayer(20)).displayer(new FadeInBitmapDisplayer(300))
				.build();

	//	add();
	}

	public   void clear(){
		if (!NewItemList.isEmpty()){
			NewItemList.clear();
		}

	}
	private void add() {
		NewsItem n1=new NewsItem();
		n1.setTitle("1");
		NewsItem n2=new NewsItem();
		n2.setTitle("2");
		NewItemList.add(n1);
		NewItemList.add(n2);

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

	public void setData(List<NewsItem> mdata){
		NewItemList.clear();
		NewItemList.addAll(mdata);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=null;
		StringBuffer sb=new StringBuffer();
		if(convertView==null){
			convertView=mInflater.inflate(R.layout.list_item_layout,null);
			viewHolder=new ViewHolder();
			viewHolder.tv_title=(TextView)convertView.findViewById(R.id.list_item_tv);
			viewHolder.imageView=(ImageView)convertView.findViewById(R.id.list_item_iv);
			viewHolder.tv_content=(TextView)convertView.findViewById(R.id.list_item_content);
			viewHolder.tv_date=(TextView)convertView.findViewById(R.id.list_item_date);

			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
			NewsItem ni=NewItemList.get(position);
			viewHolder.tv_title.setText(ni.getTitle());
			viewHolder.tv_content.setText(ni.getContent());
			viewHolder.tv_date.setText(ni.getDate());
			if (ni.getImgLink()!=null){
				viewHolder.imageView.setVisibility(View.VISIBLE);
				imageLoader.displayImage(ni.getImgLink(), viewHolder.imageView, options);
			}else
			{
				viewHolder.imageView.setVisibility(View.INVISIBLE);
			}


		return convertView ;
	}




	private final class ViewHolder {
		private TextView tv_title;
		private ImageView imageView;

		private TextView tv_content;
		private  TextView tv_date;
	}
}
