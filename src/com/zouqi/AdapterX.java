package com.zouqi;

import com.zouqi.NetWorkX.NetWorkInterface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

abstract class AdapterX extends BaseAdapter implements NetWorkInterface {

	@Override
	public Object getItem(int position) {
		Log.d("AdapterX","AdapterX default getItem: null ");
		return null;
	}

	abstract public long getItemId(int position);

	abstract public View getView(int position, View convertView, ViewGroup parent);
	
	abstract public int getCount();

	abstract public void ChangeForNewResult(Object Result);
	
};