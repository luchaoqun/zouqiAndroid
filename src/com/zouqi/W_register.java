package com.zouqi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import com.zouqi.NetWorkX.HTTPMethod;
import com.zouqi.NetWorkX.JsonType;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class W_register extends Activity {
	public static final int CAMERA_REQUEST = 1888; 
	public Bitmap photo;
	StringBuffer registerimage=new StringBuffer();
	StringBuffer registerjson=new StringBuffer();
	JSONObject image;
	String imagecontent = null;
	final W_reg_postmessage  me = null;
	    public String str1;
	    public String str2;
	    public String str3;
	    public String str4;
	    public String str5;
	Runnable imgpostsuccess=new Runnable(){   //图片post完成后post注册信息
		public void run(){
			try {
				imagecontent = image.getJSONObject("picture").getString("url");
				Log.e("image",imagecontent );
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		/*	registerjson.append(" {\"user\":{\"password\":\""+me.str2+"\",\"password_confirmation\":\""+me.str3+"\",\"school_id\":1,\"student_id\":"+me.str4+",\"student_pwd\":\""+me.str5+"\",\"user_logo\":\""
			+imagecontent.toString()+"\",\"email\":\""+me.str1+"\"}}");*/
			registerjson.append(" {\"user\":{\"password\":\""+str2+"\",\"password_confirmation\":\""+str3+"\",\"school_id\":1,\"student_id\":"+str4+",\"student_pwd\":\""+str5+"\",\"user_logo\":\""+imagecontent+"\",\"email\":\""+str1+"\"}}");
			Log.e("registerjson", registerjson.toString());
			try {
				JSONObject messg=(JSONObject)new NetWorkX("/users.json", HTTPMethod.POST,registerjson.toString(), totalpossuccess).execute(JsonType.JObject).get();
				Log.d("registerjson",registerjson.toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	Runnable totalpossuccess=new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Intent a=new Intent();
			a.setClass(W_register.this,W_login.class);
			startActivity(a);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_w_register);
	    Button btn=(Button)findViewById(R.id.W_reg_zhuce);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {    //注册post信息
				// TODO Auto-generated method stub
				//String base64image=imagechange(photo);
				//registerimage.append("{\"picture\":{\"picdata\":\""+base64image+"\"}}");
				    EditText text1=(EditText)findViewById(R.id.W_reg_editemail);
				    EditText text2=(EditText)findViewById(R.id.W_reg_editmima);
				    EditText text3=(EditText)findViewById(R.id.W_reg_editturepasswd);
				    EditText text4=(EditText)findViewById(R.id.W_reg_editjiaowu);
				    EditText text5=(EditText)findViewById(R.id.W_reg_editjiaowumima);
				     str1=text1.getText().toString();
				     str2=text2.getText().toString();
				     str3=text3.getText().toString();
				     str4=text4.getText().toString();
				     str5=text5.getText().toString();
				registerimage.append("{\"picture\":{\"picdata\":\""+"iVBORw0KGgoAAAANSUhEUgAAACYAAAAgCAIAAADxBp3kAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH3gsMBjcpD94dvAAAAWBJREFUSMft1rtKA0EUBuAzl90xm5hojCnUFIpoIVqkEAs767yChVjZiKXv4gMIgmI6BRHEwkIrCSJWIqK5SYzZZGd3LhaCxVZZyASRPeXPwMfM4cwM0lrDcAvD0Avt7u3//13GZEzGZEzGpLmikVYzhBeIk8VWR4tn6TVUYJacJ4lNZ8pB5E3yEYRz2L7xW0deVRkiZ0liJ1m45q0yrwvQALBInC1nuqdVmdeN9LLEJp9E95jXfjwAeJTdS/9jzc4QQIPfZQbROZo46L6G8jPePOdNIwebxzYAVBUP5RpAGxoShjAAyAH9BfsiG8oHgCy2QvkEtkosl0LEBBm4WhatdChfpql1e7yn1eBJAfqCN1ftzBJN/oZjiG6w7F3QlhEb2u9cXvmtPGbbzsyD6LxInkK4aKXryj+NOJQRSAH60Hu/F18rdLRA2KcSJ17tNmgroxceAFSEWxFu/Hj9TfIbZ2mBCDPDKdgAAAAASUVORK5CYII="+"\"}}");
				Log.e("imagejson", registerimage.toString());
				try {
					image = (JSONObject)new NetWorkX("/pictures.json", HTTPMethod.POST, registerimage.toString(), imgpostsuccess).execute((JsonType.JObject)).get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			/*	registerjson.append(" {\"user\":{\"password\":\""+me.str2+"\",\"password_confirmation\":\""+me.str3+"\",\"school_id\":1,\"student_id\":"+me.str4+",\"student_pwd\":\""+me.str5+"\",\"user_logo\":\""
				+imagecontent.toString()+"\",\"email\":\""+me.str1+"\"}}");
				String a=registerjson.toString();
				Log.d("registerjson",a);*/
				//json 格式  {"user":{"password":"11111111","password_confirmation":"11111111","school_id":1,"student_id":77,"student_pwd":"1119","user_logo":"/uploads/picture/image/gravatar1.jpg","email": "q@q.q"}}
			/*	try {
					JSONObject messg=(JSONObject)new NetWorkX("/users.json", HTTPMethod.POST,registerjson.toString(), totalpossuccess).execute(JsonType.JObject).get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		});
		ImageButton ibtn=(ImageButton)findViewById(R.id.W_reg_useriamgephoto);
		ibtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {   //用户头像调用照相机
				// TODO Auto-generated method stub
				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
                startActivityForResult(cameraIntent, CAMERA_REQUEST); 
			}
		});
	}
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {  //设置照片
	        super.onActivityResult(requestCode, resultCode, data);
	        if (requestCode == 1888 && resultCode == RESULT_OK) {  
	            photo = (Bitmap) data.getExtras().get("data");
	            ImageButton ibtn = (ImageButton) findViewById(R.id.W_reg_useriamgephoto);
	            ibtn.setImageBitmap(photo);
	        }  
	    } 
	 public  String imagechange(Bitmap photo){    //将图片转换为base64
	        	String s = null;
	        	ByteArrayOutputStream bStream = new ByteArrayOutputStream();
	        	photo.compress(Bitmap.CompressFormat.PNG, 100, bStream);
	        	byte[] bytes = bStream.toByteArray();
	        	s = Base64.encodeToString(bytes, Base64.NO_WRAP);
	        	return s;   
	        }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.w_register, menu);
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
