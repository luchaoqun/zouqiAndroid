package com.zouqi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class W_personal extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_w_personal);
		ListView lv=(ListView) findViewById(R.id.listView1);
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		for(int i=0;i<10;i++)
		{
		HashMap<String, Object> map = new HashMap<String, Object>();
		 map.put("ItemImage", R.drawable.ic_launcher);
		 map.put("name","wyh");
		 map.put("zan", "123");
		 map.put("iamgezan", R.drawable.ic_launcher);
		 map.put("cai", "456");
		 map.put("imagecai", R.drawable.ic_launcher);
		 map.put("geqian","woshinibaba");
		 listItem.add(map); 
		}
		 SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//数据源   
		            R.layout.activity_w_listview_padding,//ListItem的XML实现  
		            //动态数组与ImageItem对应的子项          
		            new String[] {"ItemImage","name", "zan","iamgezan","cai","imagecai","geqian"},   
		            //ImageItem的XML文件里面的一个ImageView,两个TextView ID  
		            new int[] {R.id.W_per_iamgeuser,R.id.W_per_username,R.id.W_per_textzanshu,R.id.W_per_imagezan,R.id.W_per_textcaishu,R.id.W_per_imagecai,R.id.W_per_geqian}  
		        );  
		
		   lv.setAdapter(listItemAdapter);  
		 
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.w_personal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
