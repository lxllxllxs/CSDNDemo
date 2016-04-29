import com.CommonException.CommonException;
import com.bean.NewsItem;
import com.biz.Constaint;
import com.biz.NewsItemBiz;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/4/29.
 */
public class Mian {

	public static void main(String[] args) {
		new Mian().test();

	}

	public  void test(){

		NewsItemBiz biz = new NewsItemBiz();
		int currentPage = 1;
		try
		{
			List<NewsItem> newsItems = biz.getNewsItem(Constaint.NEW_TYPE_YEJIE, currentPage);
			for (NewsItem item : newsItems)
			{
				System.out.println(item);
			}

			System.out.println("----------------------");
		} catch (CommonException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}