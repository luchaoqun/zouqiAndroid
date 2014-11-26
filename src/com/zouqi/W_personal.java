package com.zouqi;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class W_personal extends Activity {
	      
	   ArrayList listString;
	   myAdapter listAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_w_personal);
		ListView lv = (ListView) this.findViewById(R.id.listView1);
        listAdapter = new myAdapter(this);
        lv.setAdapter(listAdapter);
	}
	   // listString.add(Integer.toString());
	/*	HashMap<String, Object> map = new HashMap<String, Object>();
		 map.put("ItemImage", R.drawable.ic_launcher);
		 map.put("name","wyh");
		 map.put("zan", "123");
		 map.put("iamgezan", R.drawable.ic_launcher);
		 map.put("cai", "456");
		 map.put("imagecai", R.drawable.ic_launcher);
		 map.put("geqian","woshinibaba");
		 listItem.add(map); 
		 
		 
		 SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//数据源   
		            R.layout.activity_w_listview_padding,//ListItem的XML实现  
		            //动态数组与ImageItem对应的子项          
		            new String[] {"userimage","message", "clock","clocktext","locate","locatetext"},   
		            //ImageItem的XML文件里面的一个ImageView,两个TextView ID  
		            new int[] {R.id.imageView1,R.id.textView1,R.id.imageView2,R.id.textView2,R.id.imageView3,R.id.textView3}  
		        );  
		   lv.setAdapter(listItemAdapter);  
		   
		   HashMap<String, Object> map1 = new HashMap<String, Object>();
			map1.put("userimage", R.drawable.snowpng);
			map1.put("message", "老司机俱乐部");
			map1.put("clock", R.drawable.time);
			map1.put("clocktext", "2012/12/5");
			map1.put("locate", R.drawable.locate);
			map1.put("locatetext", "鹏远3#5005");
			 listItem.add(map1); 
			 
		 SimpleAdapter listItemAdapter1 = new SimpleAdapter(this,listItem,//数据源   
		            R.layout.activity_w_listview_padding2,//ListItem的XML实现  
		            //动态数组与ImageItem对应的子项          
		            new String[] {"userimage","message", "zan","iamgezan","cai","imagecai","geqian"},   
		            //ImageItem的XML文件里面的一个ImageView,两个TextView ID  
		            new int[] {R.id.W_per_iamgeuser,R.id.W_per_username,R.id.W_per_textzanshu,R.id.W_per_imagezan,R.id.W_per_textcaishu,R.id.W_per_imagecai,R.id.W_per_geqian}  
		        );  
		
		   lv.setAdapter(listItemAdapter1);*/
		   
		class myAdapter extends BaseAdapter{
		Context mContext;
		LinearLayout linearLayout = null;
		LayoutInflater inflater;
	    TextView tex;
		final int VIEW_TYPE = 2;
		final int TYPE_1 = 0;
		final int TYPE_2 = 1;
		
		public myAdapter(Context context){
			                  mContext = context;
                              inflater = LayoutInflater.from(mContext);
		}
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return listString.size();
			}
			@Override
			public int getItemViewType(int position) {
			// TODO Auto-generated method stub
				int p = position%2;
			if(p == 0)
			return TYPE_1;
			else
			return TYPE_2; 
			}

			@Override

			public int getViewTypeCount() {
			// TODO Auto-generated method stub
             return 1;
			}
			public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return listString.get(arg0);
		   }
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				viewHolder1 holder1 = null;
			    viewHolder2 holder2 = null;
			    int type=getItemViewType(position);
			    if(convertView==null){
			    	Log.e("converview","NULL");
			    	switch(type)
			    	{
			    	case TYPE_1:
			    		convertView=inflater.inflate(R.layout.activity_w_listview_padding, parent, false);
			    		holder1=new viewHolder1();
			    		holder1.image1=(ImageView)convertView.findViewById(R.id.W_per_iamgeuser);
			    		holder1.image2=(ImageView)convertView.findViewById(R.id.W_per_imagezan);
			    		holder1.image3=(ImageView)convertView.findViewById(R.id.W_per_imagecai);
			    		holder1.text1=(TextView)convertView.findViewById(R.id.W_per_username);
			    		holder1.text2=(TextView)convertView.findViewById(R.id.W_per_textzanshu);
			    		holder1.text3=(TextView)convertView.findViewById(R.id.W_per_textcaishu);
			    		holder1.text4=(TextView)convertView.findViewById(R.id.W_per_geqian);
			    		convertView.setTag(holder1);
			    		Log.e("holder1", "NULL "); 
			    		break;
			    	case TYPE_2:
			    	   convertView=inflater.inflate(R.layout.activity_w_listview_padding2, parent, false);
			    	   holder2=new viewHolder2();
			    	   holder2.text1=(TextView)convertView.findViewById(R.id.W_listv_activityname);
			    	   holder2.text2=(TextView)convertView.findViewById(R.id.W_listv_timetext);
			    	   holder2.text3=(TextView)convertView.findViewById(R.id.W_listv_loactiontext);
			    	   holder2.image1=(ImageView)convertView.findViewById(R.id.W_listv_userimage);
			    	   holder2.image2=(ImageView)convertView.findViewById(R.id.W_listv_timeimage);
			    	   holder2.image3=(ImageView)convertView.findViewById(R.id.W_listv_loactionimage);
			    	   convertView.setTag(holder2);
			    	   Log.e("holder2", "NULL "); 
			    	   break;
			    	}
			    }
			    else{
			    	switch(type)
			    	{
			    	case TYPE_1:
			    		holder1=(viewHolder1)convertView.getTag();
			    		break;
			    	case TYPE_2:
			    		holder2=(viewHolder2)convertView.getTag();
			    		break;
			    	}
			    }
			    switch (type){
			    case TYPE_1:
			    	holder1.text1.setText("wyh");
			    	holder1.text2.setText("123");
			    	holder1.text3.setText(Integer.toString(position));
			    	holder1.text4.setText("我是瑞琦的爸爸");
			    	holder1.image1.setBackgroundResource(R.drawable.ic_launcher);
			    	holder1.image2.setBackgroundResource(R.drawable.zan);
			    	holder1.image3.setBackgroundResource(R.drawable.ic_launcher);
			    	break;
			    case TYPE_2:
			    	holder2.text1.setText("老司机俱乐部");
			    	holder2.text2.setText("2012/12/5");
			    	holder2.text3.setText(Integer.toString(position));
			    	holder2.image1.setBackgroundResource(R.drawable.snowpng);
			    	holder2.image2.setBackgroundResource(R.drawable.time);
			    	holder2.image3.setBackgroundResource(R.drawable.locate);
			    }
				return convertView;
			}
		
			 class viewHolder1{
				 ImageView  image1;
				 ImageView  image2;
				 ImageView  image3;
				 TextView text1;
				 TextView text2;
				 TextView text3;
				 TextView text4;
			 }
			 class viewHolder2{
				 ImageView image1;
				 ImageView image2;
				 ImageView image3;
				 TextView text1;
				 TextView text2;
				 TextView text3;
			 }
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
