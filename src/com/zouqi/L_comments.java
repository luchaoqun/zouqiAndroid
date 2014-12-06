package com.zouqi;

import java.util.concurrent.ExecutionException;

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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class L_comments extends Activity {

	JSONArray json_com=null;
	JSONObject comment=null;
	ActAdapterX capt;
	String token;
	String actId=null;
	ListView listview=null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_l_comments);
		SharedPreferences pfe=getSharedPreferences("mytoken",MODE_PRIVATE);
		token=pfe.getString("token", "sdNr-dpcpsqSczLKMz1r");
		json_com=new JSONArray();
		//获取活动ID
		Intent ExtraParams=getIntent();
		actId=ExtraParams.getStringExtra("Aid");
		
		listview=(ListView)findViewById(R.id.l_comment_listview);
		capt=new ActAdapterX(this);
		listview.setAdapter(capt);

	}

	protected void onResume() {
		super.onResume();
		String RequestURL="/activities/"+actId+"/comments.json?user_token="+token;
		new NetWorkX(RequestURL,HTTPMethod.GET,null,capt).execute();
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

	/*class ComAdapter extends BaseAdapter {
		private Context context;
		private LayoutInflater layoutInflater; 
		public CommentClass comments;
		class CommentClass{
			ImageView userImg;
			TextView userId;
			TextView userCom;
		};
		public ComAdapter(Context context) {
			this.context = context;
	        		this.layoutInflater = LayoutInflater.from(this.context);  
		}

		@Override
		public int getCount()
		{
			return json_com.length();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null)
			{
				convertView = layoutInflater.inflate(R.layout.l_tab_listview_item, null);
				comments=new CommentClass();
				comments.userImg=(ImageView)convertView.findViewById(R.id.l_activity_comment_user);
				comments.userId=(TextView)convertView.findViewById(R.id.l_activity_comment_id);
				comments.userCom=(TextView)convertView.findViewById(R.id.l_activity_comment_detail);
				convertView.setTag(comments);
			}
			try {
				comment = json_com.getJSONObject(position);
				comments.userId.setText(comment.getString("email"));
				comments.userCom.setText(comment.getString("comment_content"));
				new LoadImg(comments.userImg).execute(comment.getString("user_logo"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return convertView;
		}
		
	};*/
}
