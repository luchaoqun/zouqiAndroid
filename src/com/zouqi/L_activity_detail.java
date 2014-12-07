package com.zouqi;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zouqi.NetWorkX.HTTPMethod;
import com.zouqi.NetWorkX.JsonType;
import com.zouqi.NetWorkX.NetWorkInterface;


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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class L_activity_detail extends Activity{

	public String ActId;
	private  JSONObject json_detail=new JSONObject();
	 private JSONArray comments=new JSONArray();
	 private ActDetailAdapterX dapt;
	 private String UserToken=null;
	 
	 View.OnClickListener ComListLsner=new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent NextIntent=new Intent(L_activity_detail.this,L_comments.class);
				NextIntent.putExtra("Oid",ActId);
				startActivity(NextIntent);
			}
		};
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_l_detail);
		SharedPreferences pfe=getSharedPreferences("mytoken",MODE_PRIVATE);
		UserToken=pfe.getString("token", "");
		
		Intent ExtraParams=getIntent();
		ActId=ExtraParams.getStringExtra("Aid");
		ListView lv_ad=(ListView)findViewById(R.id.L_activity_detail_listview);
		dapt = new ActDetailAdapterX(this);
		lv_ad.setAdapter(dapt);

		lv_ad.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
		
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int type = dapt.getItemViewType(position);
				Intent intent = new Intent();
				switch(type){
				case 5:
					intent.setClass(L_activity_detail.this, L_comments.class);
					intent.putExtra("Aid", ActId);
					startActivity(intent);
					break;
				case 2:
					intent.setClass(L_activity_detail.this, L_publish_comment.class);
					startActivity(intent);
					break;
				}		
				intent.setClass(L_activity_detail.this, L_comments.class);
				intent.putExtra("Aid", ActId);
				startActivity(intent);
			}  
        });
			
	}
	@Override
	protected void onResume() {
		super.onResume();
		String RequestURL="/activities/"+ActId+".json?user_token="+UserToken;
		new  NetWorkX(RequestURL,HTTPMethod.GET,null,dapt).execute();
	}

/*	class myadapter extends BaseAdapter{
		Context mContext;
		LayoutInflater inflater_w;
		final int TYPE_1 = 0;
		final int TYPE_2 = 1;
		final int TYPE_3 = 2;
		final int TYPE_4 = 3;
		final int TYPE_5 = 4;
		final int TYPE_6 = 5;
		final int TYPE_7 = 6;
		final int TYPE_8 = 7;
		private ArrayList<Integer> TypeList = new ArrayList<Integer>();
		class ActTitle{
			TextView acTitle;
			ImageView actLogo;
			TextView beginTime;
			TextView endTime;
			TextView actPlace;
			TextView actPerson;
		};
		class ActIntro{
			ImageView actImg;
			TextView actDescs;
		};
		class ActCom{
			ImageView userImg;
			TextView userId;
			TextView userCom;
		}
		public myadapter(Context context){
			                  mContext = context;
                              inflater_w = LayoutInflater.from(mContext);
		}
		
			
			public int getCount() {
				try {
					comments=json_detail.getJSONObject("activity").getJSONArray("comments");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return comments.length()+4;
			}
			@Override
			public int getItemViewType(int position) {		
				return position;
			}

			@Override

			public int getViewTypeCount() {
             return 5;
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
				
			    int type=getItemViewType(position);
			    ActTitle actTitle=null;
			    ActIntro actIntro=null;
			    ActCom actCom=null;
			    Log.e("position", Integer.toString(position));
		    	switch(type)
		    	{
		    	case TYPE_1:
		    		convertView=inflater_w.inflate(R.layout.l_activity_detail_title, parent, false);
		    		actTitle = new ActTitle();
		    		actTitle.acTitle=(TextView)convertView.findViewById(R.id.l_activity_detail_title);
		    		actTitle.actLogo=(ImageView)convertView.findViewById(R.id.detail_activity_logo);
		    		actTitle.beginTime=(TextView)convertView.findViewById(R.id.detail_begin_time);
		    		actTitle.endTime=(TextView)convertView.findViewById(R.id.detail_end_time);
		    		actTitle.actPlace=(TextView)convertView.findViewById(R.id.detail_place);
		    		actTitle.actPerson=(TextView)convertView.findViewById(R.id.detail_persons);
		    		convertView.setTag(actTitle);
		    		break;
		    		
		    	case TYPE_2:
		    	   convertView=inflater_w.inflate(R.layout.l_activity_detail_introduce, parent, false);
		    	   actIntro = new ActIntro();
		    	   actIntro.actImg=(ImageView)convertView.findViewById(R.id.l_activity_detail_bigimg);
		    	   actIntro.actDescs=(TextView)convertView.findViewById(R.id.l_activity_detail_text);
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
				default:
					convertView=inflater_w.inflate(R.layout.l_activity_detail_comment1, parent,false);
					actCom=new ActCom();
					actCom.userImg=(ImageView)convertView.findViewById(R.id.l_activity_comment_user);
		    		actCom.userId=(TextView)convertView.findViewById(R.id.l_activity_comment_id);
		    		actCom.userCom=(TextView)convertView.findViewById(R.id.l_activity_comment_detail);
		    		break;
		    	}
			    
		    	//载入数据 
		    	switch(type){
		    	case TYPE_1:
		    		try {
						actTitle.acTitle.setText(json_detail.getJSONObject("activity").getString("activity_title"));
						actTitle.beginTime.setText(json_detail.getJSONObject("activity").getString("activity_begin_time"));
						actTitle.endTime.setText(json_detail.getJSONObject("activity").getString("activity_end_time"));
						actTitle.actPlace.setText(json_detail.getJSONObject("activity").getString("activity_place"));
						actTitle.actPerson.setText(json_detail.getJSONObject("activity").getString("owner_name"));
						new LoadImg(actTitle.actLogo).execute(json_detail.getJSONObject("activity").getString("activity_logo"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
		    		break;
		    	case TYPE_2:
		    		try {
						actIntro.actDescs.setText(json_detail.getJSONObject("activity").getString("activity_content"));
						new LoadImg(actIntro.actImg).execute(json_detail.getJSONObject("activity").getString("activity_pic"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
		    		break;
		    	default:
		    		try {
						actCom.userId.setText(json_detail.getJSONArray("comments").getJSONObject(position-4).getString("email"));
						actCom.userCom.setText(json_detail.getJSONArray("comments").getJSONObject(position-4).getString("comment_content"));
						new LoadImg(actCom.userImg).execute(json_detail.getJSONArray("comments").getJSONObject(position-4).getString("user_logo"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
		    	}
			    
				return convertView;
			}
      }*/
	/*class itemlistener implements OnClickListener{
		
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
	@Override
	public void ChangeForNewResult(Object Result) {
		// TODO Auto-generated method stub
		
	}*/
}
