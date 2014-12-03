package com.zouqi;

import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;

import com.zouqi.NetWorkX.HTTPMethod;
import com.zouqi.NetWorkX.JsonType;

import android.annotation.SuppressLint;
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
import android.widget.TextView;

public class Z_Organization extends Activity {
	
	JSONArray OJsonArray;
	String UserToken;
	String UserID;
	OAdapter Odpt;
	ListView Olv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences pfe=getSharedPreferences("mytoken",MODE_PRIVATE);
		UserToken=pfe.getString("token", "sdNr-dpcpsqSczLKMz1r");
		UserID=pfe.getString("userid", "3");
		Log.d("!!!!!",UserID.toString());
		OJsonArray=new JSONArray();
		setContentView(R.layout.activity_z__organization);
		Olv=(ListView)findViewById(R.id.ZOrgList);
		Odpt=new OAdapter(this);
		Olv.setAdapter(Odpt);
		Olv.setOnItemClickListener(new AdapterView.OnItemClickListener() { 
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent NextIntent = new Intent(Z_Organization.this, Z_OrgDetail.class);
				try {
					NextIntent.putExtra("Oid", OJsonArray.getJSONObject(position).getString("id"));
				} catch (JSONException e) {
					Log.e("Z_Organization","On OnItemClickListener:"+e);
					e.printStackTrace();
				}
				startActivity(NextIntent);
				
			}  
        });
	}
	
	@Override 
	protected void onResume() {
		super.onResume();
		String RequestURL="/users/"+UserID+"/organizations.json?user_token="+UserToken;
		try {
			OJsonArray=(JSONArray) new NetWorkX(RequestURL,HTTPMethod.GET,null,DataChanged).execute(JsonType.JArray).get();
		} catch (InterruptedException e) {
			Log.e("Organization-onResume",e.toString());
			e.printStackTrace();
		} catch (ExecutionException e) {
			Log.e("Organization-onResume",e.toString());
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.z__organization, menu);
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
	
	
	public Runnable DataChanged=new Runnable(){
		   public void run(){
			   Log.d("Organization-DataChanged","Result is "+OJsonArray.toString());
			   Odpt.notifyDataSetChanged();
		   }
	   };
	  
	class OAdapter extends BaseAdapter {
		private Context context;
		private LayoutInflater layoutInflater; 
		public OrgIntroClass OrgIntro;
		class OrgIntroClass{
			ImageView OrgLogo;
			TextView OrgNameTXT;
			TextView OrgIntroTXT;
		};
		public OAdapter(Context context) {
		    this.context = context;
	        this.layoutInflater = LayoutInflater.from(this.context);  
		}

		@Override
		public int getCount()
		{
			Log.d("Z_Organization-GetCount","OJsonArray.Length= "+OJsonArray.length());
			return OJsonArray.length();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@SuppressLint("InflateParams") @Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null)
			{
				convertView = layoutInflater.inflate(R.layout.z_org_cell, null);
				OrgIntro=new OrgIntroClass();
				OrgIntro.OrgLogo=(ImageView)convertView.findViewById(R.id.zorg_pic);
				OrgIntro.OrgNameTXT=(TextView)convertView.findViewById(R.id.zorg_name);
				OrgIntro.OrgIntroTXT=(TextView)convertView.findViewById(R.id.zorg_intro);
				convertView.setTag(OrgIntro);
				Log.d("Organization-getView","NO convertView,So Creat One");
			}
			try {
				OrgClass OrgInfo=new OrgClass(OJsonArray.getJSONObject(position));
				OrgIntro.OrgNameTXT.setText(OrgInfo.GetName());
				OrgIntro.OrgIntroTXT.setText(OrgInfo.GetContent());
				new LoadImg(OrgIntro.OrgLogo).execute(OrgInfo.GetLogoURL());
			} catch (JSONException e) {
				Log.e("Organization-getView",e.toString());
				e.printStackTrace();
			}
			return convertView;
		}
		
	};
	   
}
