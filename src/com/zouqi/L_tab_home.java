package com.zouqi;

import org.json.JSONArray;

import com.zouqi.NetWorkX.HTTPMethod;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class L_tab_home extends Activity {

	private JSONArray  json_joined =new JSONArray();
	private String UserToken=null;
	private String UserID;
	private MyActAdapterX mapt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_l_tab_home);
		SharedPreferences pfe=getSharedPreferences("mytoken",MODE_PRIVATE);
		UserToken=pfe.getString("token", "sdNr-dpcpsqSczLKMz1r");
		UserID=pfe.getString("userid", "");
		
		ListView lv_ma=(ListView)findViewById(R.id.l_home_list);
		mapt = new MyActAdapterX(this);
		lv_ma.setAdapter(mapt);
		
	}

	protected void onResume() {
		super.onResume();
		String RequestURL="/users/"+UserID+"/activities.json?user_token="+UserToken;
		new  NetWorkX(RequestURL,HTTPMethod.GET,null,mapt).execute();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.l_tab_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
