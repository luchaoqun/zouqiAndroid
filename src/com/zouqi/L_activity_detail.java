package com.zouqi;

import java.util.ArrayList;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class L_activity_detail extends Activity {

	 ArrayList listString;
	 myadapter listAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_l_detail);
		ListView lv_ad=(ListView)findViewById(R.id.L_activity_detail_listview);
		listString = new ArrayList();
		
		for(int i=0;i<8;i++){
			listString.add(Integer.toString(i));
		}
		listAdapter = new myadapter(this);
		   lv_ad.setAdapter(listAdapter);
		   listAdapter.notifyDataSetChanged();
		   lv_ad.setAdapter(listAdapter);
			
	}
	class myadapter extends BaseAdapter{
		Context mContext;
		LayoutInflater inflater_w;
	    TextView tex;
		final int VIEW_TYPE = 2;
		final int TYPE_1 = 0;
		final int TYPE_2 = 1;
		final int TYPE_3 = 2;
		final int TYPE_4 = 3;
		final int TYPE_5 = 4;
		final int TYPE_6 = 5;
		final int TYPE_7 = 6;
		final int TYPE_8 = 7;
		private ArrayList<Integer> TypeList = new ArrayList<Integer>();
		public myadapter(Context context){
			                  mContext = context;
                              inflater_w = LayoutInflater.from(mContext);
		}
		
			
			public int getCount() {
				return listString.size();
			}
			@Override
			public int getItemViewType(int position) {		
				return position;
			}

			@Override

			public int getViewTypeCount() {
             return listString.size();
			}
			public Object getItem(int arg0) {
			return listString.get(arg0);
		   }
			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				
			/*	viewHolder1 holder1 = null;
				viewHolder1 holder2 = null;
				viewHolder2 holder2=null;
			    viewHolder3 holder3 = null;
			    viewHolder4 holder4 = null;
			    viewHolder5 holder5=null;*/
				//viewHolder8 holder8=null;
			    int type=getItemViewType(position);
			    Log.e("position", Integer.toString(position));
			    	switch(type)
			    	{
			    	case TYPE_1:
			    		convertView=inflater_w.inflate(R.layout.l_activity_detail_title, parent, false);

			    		break;
			    		
			    	case TYPE_2:
			    	   convertView=inflater_w.inflate(R.layout.l_activity_detail_introduce, parent, false);
			    	
			    	   break;
			    	case TYPE_3:
			    		convertView=inflater_w.inflate(R.layout.l_activity_detail_pub_button, parent, false);

			    		break;
			    	case TYPE_4:
			    		convertView=inflater_w.inflate(R.layout.l_activity_detail_comment, parent,false);

			    		break;
			    	case TYPE_5:
			    		convertView=inflater_w.inflate(R.layout.l_activity_detail_comment1, parent,false);
			    		break;
			    	case TYPE_6:
			    		convertView=inflater_w.inflate(R.layout.l_activity_detail_comment1, parent,false);
			    		break;
			    	case TYPE_7:
			    		convertView=inflater_w.inflate(R.layout.l_activity_detail_comment1, parent,false);
			    		break;
			    	case TYPE_8:
			    		convertView=inflater_w.inflate(R.layout.l_activity_detail_comment_more,parent,false);
			    		
					    break;
		    	}
			    
				return convertView;
			}
      }
	class itemlistener implements OnClickListener{
		
		private int w_position;
		public itemlistener(int pos){
			w_position=pos;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
             intent.setClass(L_activity_detail.this,L_tab_hot.class);
             startActivity(intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.l_activity_detail, menu);
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
