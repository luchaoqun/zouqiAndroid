package com.zouqi;

import org.json.JSONException;
import org.json.JSONObject;

import com.zouqi.NetWorkX.HTTPMethod;
import com.zouqi.NetWorkX.NetWorkInterface;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class W_login extends Activity implements NetWorkInterface{
	JSONObject aacc=null;
	 String strtoken;
	 String strid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_w_login);
		Button btn=(Button) findViewById(R.id.W_log_load);
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
				//aacc=(JSONObject) new NetWorkX("/users/sign_in",HTTPMethod.POST, strjson.toString(), DataChanged).execute(JsonType.JObject).get();
				new NetWorkX("/users/sign_in",HTTPMethod.POST, strjson.toString(),W_login.this).execute();
			}
		});
	}
	public void savemessage(){   //用于存储token和userid
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
	public void ChangeForNewResult(Object Result) {
		aacc=(JSONObject) Result;
		if(!aacc.has("error")){
			   savemessage();//封装，用于存储token和userid   函数见下
			   Intent intent = new Intent();
			   intent.setClass(W_login.this, MainActivity.class);
			   startActivity(intent);   //跳转到主活动
			   }
			else{
				Log.e("run","failed");
			}
	}
}
