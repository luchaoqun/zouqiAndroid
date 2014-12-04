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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.SearchView.OnQueryTextListener;

public class Z_OrgSearchList extends Activity {
	
	SearchView Sorg;
	ListView Olist;
	JSONArray OrgArray;
	OAdapter Odpt;
	private String UserToken;
	private final String PathPreFix="/organizations/search?user_token=";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_z__org_search_list);
		Sorg=(SearchView) findViewById(R.id.zorgsearch_searchview);
		Olist=(ListView) findViewById(R.id.zorgsearch_listView);
		SharedPreferences pfe=getSharedPreferences("mytoken",MODE_PRIVATE);
		UserToken=pfe.getString("token", "sdNr-dpcpsqSczLKMz1r");
		OrgArray=new JSONArray();
		Odpt=new OAdapter(this);
		Olist.setAdapter(Odpt);
		Olist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent NextIntent = new Intent(Z_OrgSearchList.this, Z_OrgDetail.class);
					try {
						NextIntent.putExtra("Oid", OrgArray.getJSONObject(position).getString("id"));
					} catch (JSONException e) {
						Log.e("Z_OrgSearch","On OnItemClickListener:"+e);
						e.printStackTrace();
					}
					startActivity(NextIntent);
				}  
	        });
		Sorg.setOnQueryTextListener(new OnQueryTextListener(){
			
			@Override
		    public boolean onQueryTextChange(String newText)
		    {
				Log.d("ZOrgSearch","OnQueryTextChange");
		         if (newText.isEmpty()) 
		         {
		        	 Olist.clearAnimation();
		         }       
		         return true;
		    }

			@Override
			public boolean onQueryTextSubmit(String query) {
				String PostData="{\"organization_name\":\""+query+"\"}";
				Log.d("ZOrgSearch",PostData);
				try {
					OrgArray=(JSONArray) new NetWorkX(PathPreFix+UserToken,HTTPMethod.POST,PostData,ReceiveResult).execute(JsonType.JArray).get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
			});
		Sorg.setSubmitButtonEnabled(true);
	};
	
	


	public Runnable ReceiveResult=new Runnable(){
		public void run(){
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
			Log.d("Z_Organization-GetCount","OJsonArray.Length= "+OrgArray.length());
			return OrgArray.length();
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
				Log.d("ZOrgSearch","Will Add Item:"+OrgArray.getJSONObject(position).toString());
				OrgClass OrgInfo=new OrgClass(OrgArray.getJSONObject(position));
				OrgIntro.OrgNameTXT.setText(OrgInfo.GetName());
				OrgIntro.OrgIntroTXT.setText(OrgInfo.GetContent());
				new LoadImg(OrgIntro.OrgLogo).execute(OrgInfo.GetLogoURL());
			} catch (JSONException e) {
				Log.e("Organization-getView",e.toString());
				e.printStackTrace();
			}
			return convertView;
		}
		
	}



}
