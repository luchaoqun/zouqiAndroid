package com.zouqi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class L_tab_hot extends Activity {
	private String[] names = new String[]{"Activity1","Activity2","Activity3","Activity4","活动5"};
	private String[] descs = new String[]{"Zhouriqi is a big SB","Zhouriqi is a big SB","Zhouriqi is a big SB","Zhouriqi is a big SB","周瑞琦是个大逗比"};
	private int[] imageIds = new int[]
			{R.drawable.snowpng,R.drawable.snowpng,R.drawable.snowpng,R.drawable.snowpng,R.drawable.snowpng};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_l_tab_hot);
		
		List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
		for(int i = 0;i < names.length;i++)
		{
			Map<String,Object> listItem = new HashMap<String,Object>();
			listItem.put("header", imageIds[i]);
			listItem.put("acname",names[i]);
			listItem.put("desc",descs[i]);
			listItems.add(listItem);
		}
		
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,
				R.layout.l_tab_listview_item,
				new String[]{"acname","header","desc"},
				new int[]{R.id.name,R.id.header,R.id.desc});
		ListView list = (ListView) findViewById(R.id.l_hot_list);
		list.setAdapter(simpleAdapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
  
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent adintent = new Intent();
				adintent.setClass(L_tab_hot.this, L_activity_detail.class);
				startActivity(adintent);
				
			}  
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.l_tab_hot, menu);
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
