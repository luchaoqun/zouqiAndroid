package com.zouqi;


import org.json.JSONArray;
import org.json.JSONException;

import com.zouqi.NetWorkX.HTTPMethod;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Z_Organization extends Activity {
	
	JSONArray OJsonArray;
	String UserToken;
	String UserID;
	OrgAdapterX Odpt;
	ListView Olv;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences pfe=getSharedPreferences("mytoken",MODE_PRIVATE);
		UserToken=pfe.getString("token", "");
		UserID=pfe.getString("userid", "");
		OJsonArray=new JSONArray();
		setContentView(R.layout.activity_z__organization);
		Olv=(ListView)findViewById(R.id.ZOrgList);
		Odpt=new OrgAdapterX(this);
		Olv.setAdapter(Odpt);
		Olv.setOnItemClickListener(new AdapterView.OnItemClickListener() { 
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent NextIntent = new Intent(Z_Organization.this, Z_OrgDetail.class);
				try {
					NextIntent.putExtra("Oid", Odpt.getItem(position).getString("id"));
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
		new NetWorkX(RequestURL,HTTPMethod.GET,null,Odpt).execute();
	}
	
	
//	public Runnable DataChanged=new Runnable(){
//		@Override
//		public void run() {
//			 Log.d("Organization-DataChanged","Result is "+OJsonArray.toString());
//			  Odpt.notifyDataSetChanged();
//		}
//	   };
//	  
//	class OAdapter extends BaseAdapter implements NetWorkInterface {
//		private Context context;
//		private LayoutInflater layoutInflater; 
//		public OrgIntroClass OrgIntro;
//		class OrgIntroClass{
//			ImageView OrgLogo;
//			TextView OrgNameTXT;
//			TextView OrgIntroTXT;
//		};
//		public OAdapter(Context context) {
//		    this.context = context;
//	        this.layoutInflater = LayoutInflater.from(this.context);  
//		}
//		@Override
//		public int getCount()
//		{
//			Log.d("Z_Organization-GetCount","OJsonArray.Length= "+OJsonArray.length());
//			return OJsonArray.length();
//		}
//
//		@Override
//		public Object getItem(int position) {
//			return null;
//		}
//
//		@Override
//		public long getItemId(int position) {
//			return position;
//		}
//
//		@SuppressLint("InflateParams") @Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			if(convertView==null)
//			{
//				convertView = layoutInflater.inflate(R.layout.z_org_cell, null);
//				OrgIntro=new OrgIntroClass();
//				OrgIntro.OrgLogo=(ImageView)convertView.findViewById(R.id.zorg_pic);
//				OrgIntro.OrgNameTXT=(TextView)convertView.findViewById(R.id.zorg_name);
//				OrgIntro.OrgIntroTXT=(TextView)convertView.findViewById(R.id.zorg_intro);
//				convertView.setTag(OrgIntro);
//				Log.d("Organization-getView","NO convertView,So Creat One");
//			}
//			try {
//				OrgClass OrgInfo=new OrgClass(OJsonArray.getJSONObject(position));
//				OrgIntro.OrgNameTXT.setText(OrgInfo.GetName());
//				OrgIntro.OrgIntroTXT.setText(OrgInfo.GetContent());
//				new LoadImg(OrgIntro.OrgLogo).execute(OrgInfo.GetLogoURL());
//			} catch (JSONException e) {
//				Log.e("Organization-getView",e.toString());
//				e.printStackTrace();
//			}
//			return convertView;
//		}
//		
//		@Override
//		public void ChangeForNewResult(Object Result) {
//			Log.d("NewResult",Result.toString());
//			this.notifyDataSetChanged();
//		}
//		
//	};
	   
}
