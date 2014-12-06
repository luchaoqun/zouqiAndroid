package com.zouqi;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zouqi.NetWorkX.HTTPMethod;
import com.zouqi.NetWorkX.JsonType;

public class W_personal extends Activity {
	   public myAdapter listAdapter;
	   ArrayList listString;
	   JSONArray registermeg=new JSONArray();
	   JSONObject total=new JSONObject();
	   JSONObject first=new JSONObject();
	   JSONObject second=new JSONObject();
	  String UserToken=null;
	  String UserID=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_w_personal);
		ListView lv=(ListView)findViewById(R.id.listView1);
		SharedPreferences pfe=getSharedPreferences("mytoken",MODE_PRIVATE);
		UserToken=pfe.getString("token", "");
		UserID=pfe.getString("userid", "");
		/*listString = new ArrayList();
		listString.add(Integer.toString(1));
		listString.add(Integer.toString(1));
		listString.add(Integer.toString(1));
		listString.add(Integer.toString(1));
		listString.add(Integer.toString(1));*/
		/*for(int i=0;i<registermeg.length();i++)
		{
			listString.add(Integer.toString(i));
		}*/
	   listAdapter = new myAdapter(this);
	   lv.setAdapter(listAdapter);
	}	   
	protected void onResume() {
		super.onResume();
		String RequestURL="/users/"+UserID+".json?user_token="+UserToken;
		new NetWorkX(RequestURL,HTTPMethod.GET,null,listAdapter).execute();
	}
//	public Runnable DataChanged=new Runnable(){
//		   public void run(){
//			   Log.d("regmessage","Result is "+total.toString());
//			   listAdapter.notifyDataSetChanged();
//		   }
//	   };
		class myAdapter extends AdapterX{
		Context mContext;
		LayoutInflater inflater;
		final int VIEW_TYPE = 2;
		final int TYPE_1 = 0;
		final int TYPE_2 = 1;
		final int TYPE_3 = 2;
		final int TYPE_4 = 3;
		final int TYPE_5 = 4;
		private ArrayList<Integer> TypeList = new ArrayList<Integer>();
		public myAdapter(Context context){
			                  mContext = context;
                              inflater = LayoutInflater.from(mContext);
		}
			public int getCount() {
				int a;
				a=registermeg.length();
				String s=""+a;
				Log.e("registerlength",s );
				return registermeg.length()+4;
				//return listString.size();
			}
			@Override
			public int getItemViewType(int position) {
				//int p = position%2;
				int p = position;
			    if(p == 0)
			      return TYPE_1;
			  else if(position==registermeg.length()+1)
				  return TYPE_3;
			  else if(position==registermeg.length()+2)
				  return TYPE_4;
			  else if(position==registermeg.length()+3)
				  return TYPE_5;
				else //if(listString.size()-position>=4)//position从零开始
	              return TYPE_2;
			
			}

			//@Override

			public int getViewTypeCount() {
             return 1;
			}
			public Object getItem(int arg0) {
			return null;
		   }
			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				viewHolder1 holder1 = null;
			    viewHolder2 holder2 = null;
			    int type=getItemViewType(position);
			    try {
					first=total.getJSONObject("user");
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			    Log.e("position", Integer.toString(position));
		  /*  if(convertView==null){
			    	Log.e("converview","NULL");
			        */
			    	switch(type)
			    	{
			    	case TYPE_1:
			    		convertView=inflater.inflate(R.layout.activity_w_listview_padding, parent, false);
			    		holder1=new viewHolder1();
			    		holder1.userimage=(ImageView)convertView.findViewById(R.id.W_per_iamgeuser);
			    		holder1.zanimage=(ImageView)convertView.findViewById(R.id.W_per_imagezan);
			    		holder1.caiimage=(ImageView)convertView.findViewById(R.id.W_per_imagecai);
			    		holder1.username=(TextView)convertView.findViewById(R.id.W_per_username);
			    		holder1.zantext=(TextView)convertView.findViewById(R.id.W_per_textzanshu);
			    		holder1.caitext=(TextView)convertView.findViewById(R.id.W_per_textcaishu);
			    		holder1.geqian=(TextView)convertView.findViewById(R.id.W_per_geqian);
			    		try {
							holder1.username.setText(first.getString("email"));
							//int a;
							//a=first.getInt("parise");
				    		holder1.geqian.setText(first.getString("school_name"));
				    		//holder1.zantext.setText(a);
				    		new LoadImg(holder1.userimage).execute(first.getString("user_logo"));
				    		break;
						} catch (JSONException e) {
							e.printStackTrace();
						}
			    		convertView.setTag(holder1);
			    		Log.e("holder1", "NULL "); 
			    		break;
			    		
			    	case TYPE_2:
			    	   convertView=inflater.inflate(R.layout.activity_w_listview_padding2, parent, false);
			    	   holder2=new viewHolder2();
			    	   holder2.activityname=(TextView)convertView.findViewById(R.id.W_listv_activityname);
			    	   holder2.timetext=(TextView)convertView.findViewById(R.id.W_listv_timetext);
			    	   holder2.loactiontext=(TextView)convertView.findViewById(R.id.W_listv_loactiontext);
			    	   holder2.userimage=(ImageView)convertView.findViewById(R.id.W_listv_userimage);
			    	   holder2.image2=(ImageView)convertView.findViewById(R.id.W_listv_timeimage);
			    	   holder2.image3=(ImageView)convertView.findViewById(R.id.W_listv_loactionimage);
			    	   try {
							second=registermeg.getJSONObject(position-1);//position=0时是个人信息
					    	new LoadImg(holder2.userimage).execute(second.getString("activity_logo"));
					    	holder2.activityname.setText(second.getString("activity_title"));
					    	holder2.loactiontext.setText(second.getString("activity_place"));
					    	holder2.timetext.setText(second.getString("activity_begin_time"));
						} catch (JSONException e) {
							e.printStackTrace();
						}
			    	   convertView.setTag(holder2);
			    	   Log.e("holder2", "NULL "); 
			    	   break;
			    	case TYPE_3:
			    		convertView=inflater.inflate(R.layout.activity_w_listview_padding3, parent,false);
			    		break;
			    	case TYPE_4:
			    		convertView=inflater.inflate(R.layout.activity_w_listview_padding4, parent,false);
			    		break;
			    	case TYPE_5:
			    		convertView=inflater.inflate(R.layout.activity_w_listview_padding5, parent,false);
			    		break;
			    	}
			//   }
		  /*else{
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
			    	/*switch(type){
			    	case TYPE_1:
						try {
							holder1.username.setText(first.getString("email"));
							//int a;
							//a=first.getInt("parise");
				    		holder1.geqian.setText(first.getString("school_name"));
				    		//holder1.zantext.setText(a);
				    		new LoadImg(holder1.userimage).execute(first.getString("user_logo"));
				    		break;
						} catch (JSONException e) {
							e.printStackTrace();
						}
			    	case TYPE_2:
						try {
							second=registermeg.getJSONObject(position-1);//position=0时是个人信息
					    	new LoadImg(holder2.userimage).execute(second.getString("activity_logo"));
					    	holder2.activityname.setText(second.getString("activity_title"));
					    	holder2.loactiontext.setText(second.getString("activity_place"));
					    	holder2.timetext.setText(second.getString("activity_begin_time"));
						} catch (JSONException e) {
							e.printStackTrace();
						}
			    		
			    	}*/
		/*	    switch (type){
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
			    }*/
				return convertView;
			}
			@Override
			public void ChangeForNewResult(Object Result) {
				 Log.d("regmessage","Result is "+total.toString());
				 total=(JSONObject)Result;
				 try {
					registermeg=total.getJSONObject("user").getJSONArray("myactivity");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 this.notifyDataSetChanged();
				
			}
      }
	/*	OrgIntroJ = OJsonArray.getJSONObject(position);
		OrgIntro.OrgNameTXT.setText(OrgIntroJ.getString("organization_name"));
		OrgIntro.OrgIntroTXT.setText(OrgIntroJ.getString("organization_content"));
		new LoadImg(OrgIntro.OrgLogo).execute(OrgIntroJ.getString("organization_logo"));*/
		class viewHolder1{
			 ImageView  userimage;
			 ImageView  zanimage;
			 ImageView  caiimage;
			 TextView username;
			 TextView zantext;
			 TextView caitext;
			 TextView geqian;
		 }
		 class viewHolder2{
			 ImageView userimage;
			 ImageView image2;
			 ImageView image3;
			 TextView activityname;
			 TextView timetext;
			 TextView loactiontext;
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
