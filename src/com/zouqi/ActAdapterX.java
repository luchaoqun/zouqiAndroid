package com.zouqi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zouqi.NetWorkX.NetWorkInterface;
import com.zouqi.OrgAdapterX.OrgIntroClass;

public class ActAdapterX extends AdapterX implements NetWorkInterface{
	
	private Context context;
	private LayoutInflater layoutInflater; 
	private JSONArray OrgList;
	
	
	public OrgIntroClass OrgIntro;
	
	
	class OrgIntroClass{
		ImageView OrgLogo;
		TextView OrgNameTXT;
		TextView OrgIntroTXT;
	};
	
	public OrgAdapterX(Context context) {
	    this.context = context;
        this.layoutInflater = LayoutInflater.from(this.context);  
        this.OrgList=new JSONArray();
	}
	
	
	@Override
	public int getCount()
	{
		Log.d("OrgAdapter","OJsonArray.Length= "+OrgList.length());
		return OrgList.length();
	}

	@Override
	public JSONObject getItem(int position) {
		JSONObject Tmp = null;
		try {
			Tmp=OrgList.getJSONObject(position);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Tmp;
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
			OrgClass OrgInfo=new OrgClass(OrgList.getJSONObject(position));
			OrgIntro.OrgNameTXT.setText(OrgInfo.GetName());
			OrgIntro.OrgIntroTXT.setText(OrgInfo.GetContent());
			new LoadImg(OrgIntro.OrgLogo).execute(OrgInfo.GetLogoURL());
		} catch (JSONException e) {
			Log.e("Organization-getView",e.toString());
			e.printStackTrace();
		}
		return convertView;
	}
	
	@Override
	public void ChangeForNewResult(Object Result) {
		Log.d("NewResult",Result.toString());
		OrgList=(JSONArray) Result;
		this.notifyDataSetChanged();
	}
	
}
