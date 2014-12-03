package com.zouqi;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import com.zouqi.NetWorkX.HTTPMethod;
import com.zouqi.NetWorkX.JsonType;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Z_OrgDetail extends Activity {
	
	public String OrgId;
	private String UserToken;
	
	private ImageView ZOrgLogo;
	private TextView ZOrgNameTxt;
	private TextView ZOrgContentTxt;
	private TextView ZOrgSchoolTxt;
	private Button ZOActvtBtn;
	private Button ZOActionBtn;
	
	private OrgClass OrgInfo;
	private JSONObject RawOrgJData,OrgActionResult;
	private boolean Joined;

	View.OnClickListener OActionBtnLsner=new View.OnClickListener() {
		public void onClick(View v) {
			if(Joined){
				String TmpUrl="/organization_userships/"+OrgInfo.GetShipId_StringType()+".json?user_token="+UserToken;
				try {
					OrgActionResult=(JSONObject) new NetWorkX(TmpUrl,HTTPMethod.DELETE,"",ReceiveActionReply).execute(JsonType.JObject).get();
				} catch (InterruptedException e) {
					Log.e("OrgDetail-OActionBtnLsner",e.toString());
					e.printStackTrace();
				} catch (ExecutionException e) {
					Log.e("OrgDetail-OActionBtnLsner",e.toString());
					e.printStackTrace();
				}
			}
			else
			{
				String TmpUrl="/organization_userships.json?user_token="+UserToken;
				String PostData="{\"organization_usership\":{\"organization_id\":"+OrgInfo.GetId_StringType()+"}}";
				try {
					OrgActionResult=(JSONObject) new NetWorkX(TmpUrl,HTTPMethod.POST,PostData,ReceiveActionReply).execute(JsonType.JObject).get();
				} catch (InterruptedException e) {
					Log.e("OrgDetail-OActionBtnLsner",e.toString());
					e.printStackTrace();
				} catch (ExecutionException e) {
					Log.e("OrgDetail-OActionBtnLsner",e.toString());
					e.printStackTrace();
				}	
			}
		}
	};
	
	public Runnable ReceiveActionReply=new Runnable(){
		public void run(){
			Log.d("OrgDetail-ReceiveActionReply","Receive Json:"+OrgActionResult.toString());
			if(Joined){
				ZOActionBtn.setText("加入");
				ZOActionBtn.setBackgroundColor(Color.argb(0xff, 0x00, 0x96, 0x88));
			}
			else{
				ZOActionBtn.setText("离开");
				ZOActionBtn.setBackgroundColor(Color.argb(0xff, 0xff, 0x00, 0x00));
				try {
					OrgInfo.SetShipId(OrgActionResult.getInt("ship_id"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Joined=!Joined;
		}
	};
	public Runnable ReceiveComplete=new Runnable(){
		   public void run(){
			   Log.d("Z_OrgDetail","Receive OrgDetail OK");
			   try {
				ChangeView();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
	   };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_z__org_detail);
		InitView();
		Intent ExtraParams=getIntent();
		OrgId=ExtraParams.getStringExtra("Oid");
		SharedPreferences pfe=getSharedPreferences("mytoken",MODE_PRIVATE);
		UserToken=pfe.getString("token", "sdNr-dpcpsqSczLKMz1r");
		ZOActionBtn.setTextColor(Color.argb(0xff, 0xff, 0xff, 0xff));
		ZOActvtBtn.setBackgroundColor(Color.argb(0x00, 0xff, 0xff, 0xff));
		ZOActvtBtn.setTextColor(Color.argb(0xff, 0x00, 0x96, 0x88));
		ZOActionBtn.setOnClickListener(OActionBtnLsner);
	}

	@Override
	protected void onResume() {
		super.onResume();
		String URL="/organizations/"+OrgId+".json?user_token="+UserToken;
		try {
			RawOrgJData=(JSONObject) new NetWorkX(URL,HTTPMethod.GET,null,ReceiveComplete).execute(JsonType.JObject).get();
		} catch (InterruptedException e) {
			Log.e("OrgDetail-onResume-NetWorkX",e.toString());
			e.printStackTrace();
		} catch (ExecutionException e) {
			Log.e("OrgDetail-onResume-NetWorkX",e.toString());
			e.printStackTrace();
		}

	}
	
	private void ChangeView() throws JSONException{
		OrgInfo=new OrgClass(RawOrgJData.getJSONObject("organization"));
		new LoadImg(ZOrgLogo).execute(OrgInfo.GetLogoURL());
		ZOrgNameTxt.setText(OrgInfo.GetName());
		ZOrgContentTxt.setText(OrgInfo.GetContent());
		ZOrgSchoolTxt.setText(OrgInfo.GetSchoolName());
		
		if(OrgInfo.GetShipId_IntType()!=null)
		{
			ZOActionBtn.setText("离开");
			ZOActionBtn.setBackgroundColor(Color.argb(0xff, 0xff, 0x00, 0x00));
			Joined=true;
			Log.d("OrgDetail-ChangeView","Ship_id is"+OrgInfo.GetShipId_StringType());
		}
		else{
			ZOActionBtn.setText("加入");
			ZOActionBtn.setBackgroundColor(Color.argb(0xff, 0x00, 0x96, 0x88));
			Joined=false;
			Log.e("OrgDetail-ChangeView","Ship_id is null");
		}
	}
	
	private void InitView(){
		ZOrgLogo=(ImageView)findViewById(R.id.zorg_detailorglogo);
		ZOrgNameTxt=(TextView)findViewById(R.id.zorg_orgnametxt);
		ZOrgContentTxt=(TextView)findViewById(R.id.zorg_orgcontenttxt);
		ZOrgSchoolTxt=(TextView)findViewById(R.id.zorg_orgschoolnametxt);
		ZOActvtBtn=(Button)findViewById(R.id.zorg_vieworgact_btn);
		ZOActionBtn=(Button)findViewById(R.id.Org_Action_btn);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.z__org_detail, menu);
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
