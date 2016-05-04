package com.lxl.csdndemo;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.lxl.csdndemo.DB.NewsItemDAO;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
	public ApplicationTest() {
		super(Application.class);
	}
/*

public  void test1(){
	NewsItem newsItem=new NewsItem("title","content","Link","date","imglink",1);
	NewsItemDAO dDAo=new NewsItemDAO(getContext());

	dDAo.add(newsItem);

	}
*/

	public  void test2(){
		NewsItemDAO dao=new NewsItemDAO(getContext());

		dao.deleteAll(1);


	}

}