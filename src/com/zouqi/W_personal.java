package com.zouqi;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONObject;

import com.zouqi.NetWorkX.HTTPMethod;
import com.zouqi.NetWorkX.JsonType;



import android.R.integer;
import android.app.Activity;
import android.app.LauncherActivity.ListItem;
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
	  /* JSONObject TestJson;
	   public Runnable DataChanged=new Runnable(){
		   public void run(){
			   Log.d("W_person_By_R7","Run the Datachanged");
			   Log.d("W_person_By_R7","Result is "+TestJson.toString());
			   listAdapter.notifyDataSetChanged();
		   }
	   };*/
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_w_personal);
		ListView lv=(ListView)findViewById(R.id.listView1);
		listString = new ArrayList();
		listString.add(Integer.toString(1));
		listString.add(Integer.toString(2));
		for(int i=0;i<10;i++)
		{
			listString.add(Integer.toString(i));
		}
	   listAdapter = new myAdapter(this);
	   lv.setAdapter(listAdapter);
	}	   
		class myAdapter extends BaseAdapter{
		Context mContext;
		LayoutInflater inflater;
		final int VIEW_TYPE = 2;
		final int TYPE_1 = 0;
		final int TYPE_2 = 1;
		final int TYPE_3 = 2;
		private ArrayList<Integer> TypeList = new ArrayList<Integer>();
		public myAdapter(Context context){
			                  mContext = context;
                              inflater = LayoutInflater.from(mContext);
		}
		
			
			public int getCount() {
				// TODO Auto-generated method stub
				return listString.size();
			}
			@Override
			public int getItemViewType(int position) {
			// TODO Auto-generated method stub
				//int p = position%2;
				int p = position;
			    if(p == 0)
			     return TYPE_1;
			   else if(listString.size()-position>=4)//position从零开始
                 return TYPE_2;
			   else 
				  return TYPE_3;
		
			}

			//@Override

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
			    Log.e("position", Integer.toString(position));
		 /*   if(convertView==null){
			    	Log.e("converview","NULL");
			        */
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
			    	case TYPE_3:
			    		convertView=inflater.inflate(R.layout.activity_w_listview_padding3, parent,false);
			    	}
			//   }
		/*   else{
			    	switch(type)
			    	{
			    	case TYPE_1:
			    		holder1=(viewHolder1)convertView.getTag();
			    		break;
			    	case TYPE_2:
			    		holder2=(viewHolder2)convertView.getTag();
			    		break;
			    	}
			    }*/
			    
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
			    	break;
			    }
				return convertView;
			}
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
