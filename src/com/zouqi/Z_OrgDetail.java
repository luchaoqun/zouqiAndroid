package com.zouqi;

import org.json.JSONException;
import org.json.JSONObject;

import com.zouqi.NetWorkX.HTTPMethod;
import com.zouqi.NetWorkX.NetWorkInterface;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Z_OrgDetail extends Activity implements NetWorkInterface {
	
	public String OrgId;
	private String UserToken;
	
	private ImageView ZOrgLogo;
	private TextView ZOrgNameTxt;
	private TextView ZOrgContentTxt;
	private TextView ZOrgSchoolTxt;
	private Button ZOActvtBtn;
	private Button ZOActionBtn;
	
	private OrgClass OrgInfo;
	private JSONObject RawOrgJData;
	private boolean Joined;
	private ReceiveActionReply BtnClick;
	
	View.OnClickListener OActListLsner=new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent NextIntent=new Intent(Z_OrgDetail.this,Z_OrgAct.class);
			NextIntent.putExtra("Oid",OrgId);
			startActivity(NextIntent);
		}
	};
	
	View.OnClickListener OActionBtnLsner=new View.OnClickListener() {
		public void onClick(View v) {
			if(Joined){
				String TmpUrl="/organization_userships/"+OrgInfo.GetShipId_StringType()+".json?user_token="+UserToken;
				new NetWorkX(TmpUrl,HTTPMethod.DELETE,"",BtnClick).execute();
			}
			else
			{
				String TmpUrl="/organization_userships.json?user_token="+UserToken;
				String PostData="{\"organization_usership\":{\"organization_id\":"+OrgInfo.GetId_StringType()+"}}";
				new NetWorkX(TmpUrl,HTTPMethod.POST,PostData,BtnClick).execute();
			}
		}
	};
	
	class  ReceiveActionReply implements NetWorkInterface{
		@Override
		public void ChangeForNewResult(String Result) {
			Log.d("OrgDetail-ReceiveActionReply","Receive Json:"+Result);
			JSONObject Tmp=null;
			if(Joined){
				ZOActionBtn.setText("加入");
				ZOActionBtn.setBackgroundColor(Color.argb(0xff, 0x00, 0x96, 0x88));
			}
			else{
				try {
					Tmp=new JSONObject(Result);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ZOActionBtn.setText("离开");
				ZOActionBtn.setBackgroundColor(Color.argb(0xff, 0xff, 0x00, 0x00));
				try {
					OrgInfo.SetShipId(Tmp.getInt("ship_id"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Joined=!Joined;
		}
	}
	
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
		ZOActvtBtn.setOnClickListener(OActListLsner);
		BtnClick=new ReceiveActionReply();
	}

	@Override
	protected void onResume() {
		super.onResume();
		String URL="/organizations/"+OrgId+".json?user_token="+UserToken;
		new NetWorkX(URL,HTTPMethod.GET,null,this).execute();

	}
	
	private void ChangeView() throws JSONException{
		OrgInfo=new OrgClass(RawOrgJData.getJSONObject("organization"));
		new LoadImg(ZOrgLogo).execute(OrgInfo.GetLogoURL());
		ZOrgNameTxt.setText(OrgInfo.GetName());
		ZOrgContentTxt.setText(OrgInfo.GetContent());
		ZOrgSchoolTxt.setText(OrgInfo.GetSchoolName());
		
		if(OrgInfo.GetShipId_IntType()!=-1)
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
	public void ChangeForNewResult(String Result) {
		try {
			this.RawOrgJData=new JSONObject(Result);
			ChangeView();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
