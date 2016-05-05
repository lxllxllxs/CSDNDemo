package com.lxl.csdndemo.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bean.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 */
public class NewsItemDAO {
	private DBHelper dbHelper;

	public NewsItemDAO(Context context) {
		dbHelper = new DBHelper(context);
	}


	public void add(NewsItem newsItem) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String sql = "insert into tb_newsItem(title,content,link,_date,imgLink,newstype) values(?,?,?,?,?,?);";
		db.execSQL(sql, new Object[]{
				newsItem.getTitle(),
				newsItem.getContent(),
				newsItem.getLink(),
				newsItem.getDate(),
				newsItem.getImgLink(),
				newsItem.getNewsType()
		});

		db.close();

	}

	public void deleteAll(int newType) {
		String sql = "delete from tb_newsItem where newstype=?";
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL(sql, new Object[]{newType});
		db.close();


	}


	public  void add(List<NewsItem> list){
		for(NewsItem ni:list){
			add(ni);
		}
	}

	public  List<NewsItem> query(int newsType,int currentPage){
		NewsItem newsItem=null;
		int offset=10*(currentPage-1);//缓存多少条记录
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		String sql ="select title ,content ,link ,_date ,imgLink ,newstype from tb_newsItem where newstype=? limit ?,?";
		Cursor cs=db.rawQuery(sql,new String[]{""+newsType,""+offset,""+offset+10});


		List<NewsItem> newsItemList=new ArrayList<NewsItem>();

		while (cs.moveToNext()){
			newsItem=new NewsItem();
			String title=cs.getString(0);
			String content=cs.getString(1);
			String link=cs.getString(2);
			String date=cs.getString(3);
			String imgLink=cs.getString(4);
			int  newstype=cs.getInt(5);

			newsItem.setTitle(title);
			newsItem.setContent(content);
			newsItem.setLink(link);
			newsItem.setDate(date);
			newsItem.setImgLink(imgLink);
			newsItem.setNewsType(newstype);

			newsItemList.add(newsItem);
		}
		cs.close();
		db.close();

		return  newsItemList;

	}


}
