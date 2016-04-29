package com;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by Administrator on 2016/4/29.
 */
public class Mytest {
	public  static void main(String[] args){
		new Mytest().go();
	}
	public  void go(){
		try {
			Document document= Jsoup.connect("http://127.0.1:80/android/jsoup.html").get();
			Element h1_ele=document.getElementsByTag("h1").get(0);
			String href=h1_ele.child(0).attr("href");
			Element h4_ele=document.getElementsByClass("view_time").get(0);
			String reader=h4_ele.text();
			System.out.print("reader is "+reader);
			System.out.print(href);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
