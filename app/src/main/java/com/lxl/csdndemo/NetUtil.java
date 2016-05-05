package com.lxl.csdndemo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2016/5/4.
 * 判读网络状态
 *
 */
public class NetUtil {
		public static boolean isConnected(Context context){
			if (isWIFIConnected(context)||isMobileConnected(context)){
				return true;
			}
			return  false;
		}





	public static  boolean isWIFIConnected(Context context){
		ConnectivityManager CM=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo= CM.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (networkInfo!=null&&networkInfo.isConnected()){
			return true;
		}
		return false;
	}



	public static  boolean isMobileConnected(Context context){
		ConnectivityManager CM=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo= CM.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (networkInfo!=null&&networkInfo.isConnected()){
			return true;
		}
		return false;
	}



}
