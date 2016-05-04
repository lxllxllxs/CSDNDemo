package com.biz;

import com.CommonException.CommonException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/4/29.
 * 不再使用
 *
 *
 *
 */
public class DataUtils {

	public  static  String getString(String urls){
		StringBuffer sb=new StringBuffer();

		try {
			URL url=new URL(urls);
			HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setConnectTimeout(5000);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			InputStream is;
			if (httpURLConnection.getResponseCode()==200){
				is=httpURLConnection.getInputStream();

				byte[]  buff=new byte[1024];
				int count;

				while ((count=is.read(buff))!=-1){
					sb.append(new String(buff,0,count,"UTF-8"));
				}

			}else {
				throw new CommonException("访问网络失败");
			}
			is.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CommonException e) {
			e.printStackTrace();
		}

		return  sb.toString();
	}


}
