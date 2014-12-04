package com.zouqi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import com.zouqi.NetWorkX.HTTPMethod;
import com.zouqi.NetWorkX.JsonType;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class W_login extends Activity {
	JSONObject aacc=null;
	Runnable DataChanged=new Runnable(){
		   String strtoken;
		   String strid;
		   public void run(){
			   if(!aacc.has("error")){
			   savemessage(strtoken,strid);//封装，用于存储token和userid   函数见下
			   Intent intent = new Intent();
			   intent.setClass(W_login.this, MainActivity.class);
			   startActivity(intent);   //跳转到主活动
			   }
			else{
				Log.e("run","failed");
			}
		   }
	   };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_w_login);
		Button btn=(Button) findViewById(R.id.W_log_load);
		JSONObject TestJson;
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText text1=(EditText)findViewById(R.id.W_log_editname);//用户名
				String str1 = text1.getText().toString();
				EditText text2=(EditText)findViewById(R.id.W_log_editpasswd);//密码
				String str2 = text2.getText().toString();
				StringBuffer strjson= new StringBuffer();
				strjson.append("{\"user\":{\"email\": \""+str1+"\",\"password\": \""+str2+"\"}}");
				System.out.println(strjson);
				try {
					aacc=(JSONObject) new NetWorkX("/users/sign_in",HTTPMethod.POST, strjson.toString(), DataChanged).execute(JsonType.JObject).get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void savemessage(String strtoken ,String strid){   //用于存储token和userid
		  try {
				strtoken=aacc.getString("user_token");
				strid=aacc.getString("user_id");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			   SharedPreferences prefs= getSharedPreferences("mytoken", MODE_PRIVATE);
			   Editor myeditor=prefs.edit();
			   myeditor.putString("token", strtoken);
			   myeditor.putString("userid", strid);
			   myeditor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.w_login, menu);
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
