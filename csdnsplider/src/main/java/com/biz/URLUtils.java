package com.biz;

/**
 * Created by Administrator on 2016/4/29.
 */
public class URLUtils {
	public static final String NEWS_LIST_URL = "http://www.csdn.net/headlines.html";
	public static final String NEWS_LIST_URL_YIDONG = "http://mobile.csdn.net/mobile";
	public static final String NEWS_LIST_URL_YANFA = "http://sd.csdn.net/sd";
	public static final String NEWS_LIST_URL_YUNJISUAN = "http://cloud.csdn.net/cloud";
	public static final String NEWS_LIST_URL_TOUTIAO = "http://geek.csdn.net/";
	public static final String NEWS_LIST_URL_YEJIE = "http://news.csdn.net/news";

	public  static  String  getUrl(int newType,int currentTag){
		currentTag = currentTag > 0 ? currentTag : 1;
		String url="";
		switch (newType){
			case Constaint.NEW_TYPE_YANFA:
				url=NEWS_LIST_URL_YANFA;
				break;
			case Constaint.NEW_TYPE_YEJIE:
				url=NEWS_LIST_URL_YEJIE;
				break;
			case Constaint.NEW_TYPE_YIDONG:
				url=NEWS_LIST_URL_YIDONG;
				break;
			case Constaint.NEW_TYPE_YUNSUAN:
				url=NEWS_LIST_URL_YUNJISUAN;
				break;
		/*	case Constaint.NEW_TYPE_TOUTIAO:
				url=NEWS_LIST_URL_TOUTIAO;
				break;*/
		}
		url+="/"+currentTag;
//		url=url+"/";
		return url ;


	}

}
