package com.zouqi;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zouqi.NetWorkX.HTTPMethod;
import com.zouqi.NetWorkX.JsonType;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class L_tab_hot extends Activity {

	JSONArray hotjson=null;
	JSONObject activity=null;
	ActAdapterX hapt;
	String token;
	ListView listview=null;
	
	Runnable DataChanged=new Runnable(){

		@Override
		public void run() {
			System.out.println("networkx get success!");
			hapt.notifyDataSetChanged();
		}
		
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v("L_tab_hot","on Create");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_l_tab_hot);
		SharedPreferences pfe=getSharedPreferences("mytoken",MODE_PRIVATE);
		token=pfe.getString("token", "sdNr-dpcpsqSczLKMz1r");
		hotjson = new JSONArray();
		listview=(ListView)findViewById(R.id.l_hot_list);
		hapt=new ActAdapterX(this);
		listview.setAdapter(hapt);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent NextIntent = new Intent(L_tab_hot.this, L_activity_detail.class);
                try {
                    NextIntent.putExtra("Aid", hotjson.getJSONObject(position).getString("id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(NextIntent);
                
            }
        });
	}

	protected void onResume() {
		super.onResume();
		Log.v("L_tab_hot","onresume");
		String RequestURL="/activities.json?user_token="+token;
		new NetWorkX(RequestURL,HTTPMethod.GET,null,hapt).execute();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.l_tab_hot, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	//适配器
//	class HotAdapter extends BaseAdapter {
//		private Context context;
//		private LayoutInflater layoutInflater; 
//		public HotActClass hotact;
//		class HotActClass{
//			ImageView actLogo;
//			TextView actName;
//			TextView actTime;
//			TextView actLocate;
//		};
//		public HotAdapter(Context context) {
//		    this.context = context;
//	        this.layoutInflater = LayoutInflater.from(this.context);  
//		}
//
//		@Override
//		public int getCount()
//		{
//			Log.d("Z_Organization-GetCount`","OJsonArray.Length= "+hotjson.length());
//			return hotjson.length();
//		}
//
//		@Override
//		public Object getItem(int position) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public long getItemId(int position) {
//			// TODO Auto-generated method stub
//			return position;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			if(convertView==null)
//			{
//				convertView = layoutInflater.inflate(R.layout.l_tab_listview_item, null);
//				hotact=new HotActClass();
//				hotact.actLogo=(ImageView)convertView.findViewById(R.id.actlogo);
//				hotact.actName=(TextView)convertView.findViewById(R.id.actname);
//				hotact.actTime=(TextView)convertView.findViewById(R.id.hot_time_text);
//				hotact.actLocate=(TextView)convertView.findViewById(R.id.hot_locate_text);
//				convertView.setTag(hotact);
//			}
//			try {
//				activity = hotjson.getJSONObject(position);
//				hotact.actName.setText(activity.getString("activity_title"));
//				hotact.actTime.setText(activity.getString("activity_begin_time"));
//				hotact.actLocate.setText(activity.getString("activity_place"));
//				new LoadImg(hotact.actLogo).execute(activity.getString("activity_logo"));
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//			return convertView;
//		}
//		
//	};
	
	
}
