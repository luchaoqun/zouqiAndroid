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

public class ActAdapterX extends AdapterX implements NetWorkInterface{
	
	private Context context;
	private LayoutInflater layoutInflater; 
	private JSONArray ActList;
	
	
	public ActIntroClass ActIntro;
	
	
	class ActIntroClass{
		ImageView ActLogo;
		TextView ActNameTXT;
		TextView ActPlaceTXT;
		TextView ActTimeTXT;
	};
	
	public ActAdapterX(Context context) {
	    this.context = context;
        this.layoutInflater = LayoutInflater.from(this.context);  
        this.ActList=new JSONArray();
	}
	
	
	@Override
	public int getCount()
	{
		Log.d("OrgAdapter","OJsonArray.Length= "+ActList.length());
		return ActList.length();
	}

	@Override
	public JSONObject getItem(int position) {
		JSONObject Tmp = null;
		try {
			Tmp=ActList.getJSONObject(position);
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
			ActIntro=new ActIntroClass();
			ActIntro.ActLogo=(ImageView)convertView.findViewById(R.id.actlogo);
			ActIntro.ActNameTXT=(TextView)convertView.findViewById(R.id.actname);
			ActIntro.ActPlaceTXT=(TextView)convertView.findViewById(R.id.hot_locate);
			ActIntro.ActTimeTXT=(TextView)convertView.findViewById(R.id.hot_time_text);
			convertView.setTag(ActIntro);
			Log.d("ActAdapter","NO convertView,So Creat One");
		}
		try {
			ActivityClass ActInfo=new ActivityClass(ActList.getJSONObject(position));
			ActIntro.ActNameTXT.setText(ActInfo.GetTitle());
			ActIntro.ActPlaceTXT.setText(ActInfo.GetPlace());
			ActIntro.ActTimeTXT.setText(ActInfo.GetBeginTime());
			new LoadImg(ActIntro.ActLogo).execute(ActInfo.GetLogoURL());
		} catch (JSONException e) {
			Log.e("Organization-getView",e.toString());
			e.printStackTrace();
		}
		return convertView;
	}
	
	@Override
	public void ChangeForNewResult(Object Result) {
		Log.d("NewResult",Result.toString());
		ActList=(JSONArray) Result;
		this.notifyDataSetChanged();
	}
	
}
