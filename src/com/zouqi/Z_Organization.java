package com.zouqi;

import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zouqi.NetWorkX.HTTPMethod;
import com.zouqi.NetWorkX.JsonType;

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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Z_Organization extends Activity {
	JSONArray OJsonArray=null;
	String UserToken="sdNr-dpcpsqSczLKMz1r";
	String UserID="3";
	OAdapter Odpt;
	ListView Olv=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v("ZOrganization","on Create");
		super.onCreate(savedInstanceState);
		SharedPreferences pfe=getSharedPreferences("mytoken",MODE_PRIVATE);
		UserToken=pfe.getString("token", "sdNr-dpcpsqSczLKMz1r");
		UserID=pfe.getString("userid", "3");
		OJsonArray=new JSONArray();
		setContentView(R.layout.activity_z__organization);
		ListView Olv=(ListView)findViewById(R.id.ZOrgList);
		Odpt=new OAdapter(this);
		Olv.setAdapter(Odpt);
	}
	
	@Override 
	protected void onResume() {
		super.onResume();
		Log.v("ZOrganization","onresume");
		String RequestURL="/users/"+UserID+"/organizations.json?user_token="+UserToken;
		try {
			OJsonArray=(JSONArray) new NetWorkX(RequestURL,HTTPMethod.GET,null,DataChanged).execute(JsonType.JArray).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
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
			   Log.d("Z_Organization","Result is "+OJsonArray.toString());
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
			Log.d("Z_Organization-GetCount`","OJsonArray.Length= "+OJsonArray.length());
			return OJsonArray.length();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null)
			{
				convertView = layoutInflater.inflate(R.layout.z_org_cell, null);
				OrgIntro=new OrgIntroClass();
				OrgIntro.OrgLogo=(ImageView)convertView.findViewById(R.id.zorg_pic);
				OrgIntro.OrgNameTXT=(TextView)convertView.findViewById(R.id.zorg_name);
				OrgIntro.OrgIntroTXT=(TextView)convertView.findViewById(R.id.zorg_intro);
				convertView.setTag(OrgIntro);
				Log.d("Z_Organization","NO convertView,So Creat One");
			}
			JSONObject OrgIntroJ;
			try {
				OrgIntroJ = OJsonArray.getJSONObject(position);
				OrgIntro.OrgNameTXT.setText(OrgIntroJ.getString("organization_name"));
				OrgIntro.OrgIntroTXT.setText(OrgIntroJ.getString("organization_content"));
				new LoadImg(OrgIntro.OrgLogo).execute(OrgIntroJ.getString("organization_logo"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return convertView;
		}
		
	};
	   
}
