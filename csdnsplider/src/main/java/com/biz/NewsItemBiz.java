package com.biz;

import com.CommonException.CommonException;
import com.bean.NewsItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/29.
 */
public class NewsItemBiz  {
	public List<NewsItem> getNewsItem(int newsType , int currentPage) throws CommonException, IOException {
		List<NewsItem> newsItemList=new ArrayList<NewsItem>();
		NewsItem newsItem=null;
		//取得连接
		String url=URLUtils.getUrl(newsType,currentPage);
		//取得连接里的内容
	//	String urls=DataUtils.getString(url);
		Document document=Jsoup.connect(url).get();
		Elements units = document.getElementsByClass("unit");//从哪里开始
		System.out.print(units.size()+"");
		for (int i = 0; i < units.size(); i++)
		{
			newsItem = new NewsItem();
			newsItem.setNewsType(newsType);

			Element unit_ele = units.get(i);

			Element h1_ele = unit_ele.getElementsByTag("h1").get(0);
			Element h1_a_ele = h1_ele.child(0);
			String title = h1_a_ele.text();
			String href = h1_a_ele.attr("href");

			newsItem.setLink(href);
			newsItem.setTitle(title);

			Element h4_ele = unit_ele.getElementsByTag("h4").get(0);
			Element ago_ele = h4_ele.getElementsByClass("ago").get(0);
			String date = ago_ele.text();

			newsItem.setDate(date);

			Element dl_ele = unit_ele.getElementsByTag("dl").get(0);// dl
			Element dt_ele = dl_ele.child(0);// dt
			try
			{// 可能没有图片
				Element img_ele = dt_ele.child(0);
				String imgLink = img_ele.child(0).attr("src");
				newsItem.setImgLink(imgLink);
			} catch (IndexOutOfBoundsException e)
			{
			}
			Element content_ele = dl_ele.child(1);// dd
			String content = content_ele.text();
			newsItem.setContent(content);
			newsItemList.add(newsItem);
		}

		return  newsItemList;
	}


}
