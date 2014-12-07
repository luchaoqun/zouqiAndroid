package com.zouqi;

import org.json.JSONArray;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zouqi.NetWorkX.NetWorkInterface;

public class MyActAdapterX extends AdapterX implements NetWorkInterface{

	private JSONArray myAct;
	private Context mContext;
	private LayoutInflater inflater_w;
	
	final int TYPE_1 = 0;
	final int TYPE_2 = 1;
	final int TYPE_3 = 2;
	final int TYPE_4 = 3;
	
	class ActIntroClass{
		ImageView ActLogo;
		TextView ActNameTXT;
		TextView ActPlaceTXT;
		TextView ActTimeTXT;
	};
	
	public MyActAdapterX(Context context){
        mContext = context;
        inflater_w = LayoutInflater.from(mContext);     
	}

	@Override
	public int getCount() {
		return myAct.length()+2;
	}
	
	@Override
	public int getItemViewType(int position) {
		if(position==0) 
			return TYPE_1;
		else if(position==1)
			return TYPE_2;
		else if(position==2)
			return TYPE_3;
		else
			return TYPE_4;
	}
	@Override
	public int getViewTypeCount() {
     return 4;
	}
	
	public Object getItem(int arg0) {
	return null;
   }
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		int type=getItemViewType(position);
		switch(type)
		{
		case TYPE_1:
			break;
		case TYPE_2:
			break;
		case TYPE_3:
			break;
		case TYPE_4:
			break;
		default:
			break;
		}
		return convertView;
	}
	 
	@Override
	public void ChangeForNewResult(Object Result) {
		myAct=(JSONArray)Result;
		this.notifyDataSetChanged();
	}

}
