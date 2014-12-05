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

public class Z_OrgAct extends Activity {

	ListView OAList;
	JSONArray OActArray;
	ActAdapterX OrgActAdpt;
	private String OrgId;
	String UserToken;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_z__org);
		OAList=(ListView) findViewById(R.id.zorgact_list);
		OActArray=new JSONArray();
		OrgActAdpt=new ActAdapterX(this);
		OAList.setAdapter(OrgActAdpt);
		OAList.setOnItemClickListener(new AdapterView.OnItemClickListener() { 
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent NextIntent = new Intent(Z_OrgAct.this, L_activity_detail.class);
				try {
					NextIntent.putExtra("Aid", OActArray.getJSONObject(position).getString("id"));
				} catch (JSONException e) {
					Log.e("Z_OrgAct","On OnItemClickListener:"+e);
					e.printStackTrace();
				}
				startActivity(NextIntent);
			}  
        });
		Intent ExtraParams=getIntent();
		OrgId=ExtraParams.getStringExtra("Oid");
		SharedPreferences pfe=getSharedPreferences("mytoken",MODE_PRIVATE);
		UserToken=pfe.getString("token", "");
	}

	@Override 
	protected void onResume() {
		super.onResume();
		String RequestURL="/organizations/"+OrgId+"/activities.json?user_token="+UserToken;
		new NetWorkX(RequestURL,HTTPMethod.GET,null,OrgActAdpt).execute();
	}
	
//
//	public Runnable DataChanged=new Runnable(){
//		   public void run(){
//			   Log.d("Organization-DataChanged","Result is "+OActArray.toString());
//			   OrgActAdpt.notifyDataSetChanged();
//		   }
//	   };
	
//	class OAdapter extends BaseAdapter {
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
//			Log.d("ZOrcAct","OJsonArray.Length= "+OActArray.length());
//			return OActArray.length();
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
//				ActivityClass AInfo=new ActivityClass(OActArray.getJSONObject(position));
//				OrgIntro.OrgNameTXT.setText(AInfo.GetTitle());
//				OrgIntro.OrgIntroTXT.setText(AInfo.GetBeginTime());
//				new LoadImg(OrgIntro.OrgLogo).execute(AInfo.GetLogoURL());
//			} catch (JSONException e) {
//				Log.e("Organization-getView",e.toString());
//				e.printStackTrace();
//			}
//			return convertView;
//		}
//		
//	};
}
