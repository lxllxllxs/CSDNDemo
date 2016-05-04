package com.lxl.csdndemo.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/5/4.
 */
public class DBHelper extends SQLiteOpenHelper {
	private static final String  DBNAME="csdnDB";

	public DBHelper(Context context){
		super(context,DBNAME,null,1);

	}



	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql="create table tb_newsItem(_id integer primary key autoincrement,title text,content text,link text,_date text,imgLink text,newstype integer);";
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
