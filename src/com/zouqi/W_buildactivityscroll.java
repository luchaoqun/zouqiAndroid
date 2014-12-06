package com.zouqi;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.zouqi.NetWorkX.HTTPMethod;
import com.zouqi.NetWorkX.JsonType;
import com.zouqi.NetWorkX.NetWorkInterface;

public class W_buildactivityscroll extends Activity implements NetWorkInterface{
	 public static final String IMAGE_UNSPECIFIED = "image/*";
	 public final static int PHOTO_ZOOM = 0;
	 public final static int PHOTO_RESULT = 2;
	 private byte[] mContent;
	 private Bitmap mybitmap1;
	 private Bitmap mybitmap2;
	 String  logopoststr=new String();
	 String picpoststr=new String();
	 String  totalstr=new String();
	 JSONObject jsonxiaotu;
	 JSONObject jsondatu;
	 JSONObject total;
	 String base64small;
	 String base64big;
	 String smallroad=null;
	 String bigroad=null;
	 String UserToken;
	 String UserID;
	 UpLoadImg bigpic,smallpic;
	 Runnable success=new  Runnable() {
		@Override
		public void run() {}
	};
	Runnable totalsuccess=new Runnable() {
		
		@Override
		public void run() {
			Intent personal=new Intent();
			personal.setClass(W_buildactivityscroll.this, W_personal.class);
			startActivity(personal);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_w_buildactivityscroll);
		SharedPreferences pfe=getSharedPreferences("mytoken",MODE_PRIVATE);
		UserToken=pfe.getString("token", "");
		UserID=pfe.getString("userid", "");
		  final EditText  text1=(EditText)findViewById(R.id.W_editactivityname);
	      final EditText  text2=(EditText)findViewById(R.id.W_buildactivityeditlocation);
	      final EditText  text3=(EditText)findViewById(R.id.W_editbuildactivitytime);
	      final EditText  text4=(EditText)findViewById(R.id.W_editbuildactivityendtime);
	      final EditText  text5=(EditText)findViewById(R.id.W_buildactivity_editmaxpeople);
	      final EditText  text6=(EditText)findViewById(R.id.W_buildactivityeditteam);
	      final EditText  text7=(EditText)findViewById(R.id.W_buildactivityeditintroduce);
	     /* String str1;
		  String str2;
		  String str3;
		  String str4;
		  String str5;*/
		/*  logopoststr.append("{\"picture\":{\"picdata\":\""+base64small+"\"}}");
		  picpoststr.append("{\"picture\":{\"picdata\":\""+base64big+"\"}}");
		  Log.e("xiaotu",logopoststr.toString());
		  Log.e("datu",picpoststr.toString());
		  try {
			jsonxiaotu=(JSONObject) new NetWorkX("/pictures.json", HTTPMethod.POST, logopoststr.toString(), success).execute(JsonType.JObject).get();
			jsondatu=(JSONObject) new NetWorkX("/pictures.json", HTTPMethod.POST, picpoststr.toString(), success).execute(JsonType.JObject).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}*/
		Button xiaotu=(Button)findViewById(R.id.W_buildactivity_btnxiaotu);
		xiaotu.setOnClickListener(new OnClickListener() { //监听小图按钮
			@Override
			public void onClick(View v) {
				 Intent intent = new Intent(
		         Intent.ACTION_PICK,
		         android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		         startActivityForResult(intent, 1);
			}
		});
		Button datu=(Button)findViewById(R.id.W_buildactivity_btndatu);
		datu.setOnClickListener(new OnClickListener() {//监听大图按钮
			
			@Override
			public void onClick(View v) {
				 Intent intent = new Intent(
				         Intent.ACTION_PICK,
				         android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				         startActivityForResult(intent, 2);
			}
		});
		/*  logopoststr="{\"picture\":{\"picdata\":\""+base64small+"\"}}";
		  picpoststr="{\"picture\":{\"picdata\":\""+base64big+"\"}}";
		  Log.e("xiaotu",logopoststr.toString());
		  Log.e("datu",picpoststr.toString());
		  try {
			jsonxiaotu=(JSONObject) new NetWorkX("/pictures.json", HTTPMethod.POST, logopoststr.toString(), success).execute(JsonType.JObject).get();
			jsondatu=(JSONObject) new NetWorkX("/pictures.json", HTTPMethod.POST, picpoststr.toString(), success).execute(JsonType.JObject).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}*/
		Button build=(Button)findViewById(R.id.W_buildactivity_truebuild);
		build.setOnClickListener(new OnClickListener() { //监听发起活动按钮
			@Override
			public void onClick(View v) {
				
				logopoststr="{\"picture\":{\"picdata\":\""+base64small+"\"}}";
				Log.e("xiaotu",logopoststr.toString());
				smallpic=new UpLoadImg(false);
				new NetWorkX("/pictures.json", HTTPMethod.POST, logopoststr.toString(), smallpic).execute();				
			}
		});
	}
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		   ContentResolver resolver =getContentResolver();
		   if(data!=null){
			   if(requestCode==1){
				   try{
				   Uri uri=data.getData();
				   mContent = readStream(resolver.openInputStream(Uri
                         .parse(uri.toString())));
				   mybitmap1 = getPicFromBytes(mContent, null);
				   ImageView image=(ImageView) findViewById(R.id.W_buildactivity_imgxiaotu);
                   image.setImageBitmap(mybitmap1);
                   base64small=imagechange(mybitmap1);
				   }
				   catch (Exception e) {
                     System.out.println(e.getMessage());
                   }
			  }
			   else{
				   try{
					   Uri uri=data.getData();
					   mContent = readStream(resolver.openInputStream(Uri
	                         .parse(uri.toString())));
					   mybitmap2 = getPicFromBytes(mContent, null);
					   ImageView image=(ImageView) findViewById(R.id.W_buildactivity_imgdatu);
	                   image.setImageBitmap(mybitmap2);
	                   base64big=imagechange(mybitmap2);
					   }  catch (Exception e) {
		                     System.out.println(e.getMessage());
	                   }
				  }
			   }
		   }

	 public static Bitmap getPicFromBytes(byte[] bytes,
             BitmapFactory.Options opts) {
     if (bytes != null)
             if (opts != null)
                     return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
                                     opts);
             else
                     return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
     return null;
}

public static byte[] readStream(InputStream inStream) throws Exception {
     byte[] buffer = new byte[1024];
     int len = -1;
     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
     while ((len = inStream.read(buffer)) != -1) {
             outStream.write(buffer, 0, len);
     }
     byte[] data = outStream.toByteArray();
     outStream.close();
     inStream.close();
     return data;

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
		getMenuInflater().inflate(R.menu.w_buildactivityscroll, menu);
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
	@Override
	public void ChangeForNewResult(Object Result) {
		Intent personal=new Intent();
		personal.setClass(W_buildactivityscroll.this, W_personal.class);
		startActivity(personal);
	}
	
	class UpLoadImg implements NetWorkInterface{
		boolean BigPic;
		UpLoadImg(boolean isBigpic){
			BigPic=isBigpic;
		}
		@Override
		public void ChangeForNewResult(Object Result) {
			if(BigPic){
				jsondatu=(JSONObject) Result;
				try {
					bigroad=jsondatu.getJSONObject("picture").getString("url");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Log.e("bigroad", bigroad);
				
				
				EditText  text1=(EditText)findViewById(R.id.W_editactivityname);
		         EditText  text2=(EditText)findViewById(R.id.W_buildactivityeditlocation);
		         EditText  text3=(EditText)findViewById(R.id.W_editbuildactivitytime);
		         EditText  text4=(EditText)findViewById(R.id.W_editbuildactivityendtime);
		         EditText  text5=(EditText)findViewById(R.id.W_buildactivity_editmaxpeople);
		         EditText  text6=(EditText)findViewById(R.id.W_buildactivityeditteam);
		         EditText  text7=(EditText)findViewById(R.id.W_buildactivityeditintroduce);
			     String str1=text1.getText().toString();
			     String str2=text2.getText().toString();
			     String str3=text3.getText().toString();
			     String str4=text4.getText().toString();
			     String str5=text5.getText().toString();
			     String str6=text6.getText().toString();
			     String str7=text7.getText().toString();
			     int max=Integer.parseInt(str5);
			     totalstr="{\"activity\":{\"activity_title\":\""+str1+"\",\"activity_content\":\""+str7+"\",\"activity_place\":\""+str2+"\",\"activity_logo\":\""+smallroad+"\",\"activity_pic\":\""+
			     bigroad+"\",\"activity_place_lat\":2.0,\"activity_place_lon\":2.0,\"activity_people_max\":"+max+",\"activity_begin_time\":\""+str3+"\",\"activity_end_time\":\""+str4+"\"}}";
			     Log.e("totalstr", totalstr);
			     //String totalfinal=URLEncoder.encode(totalstr, "utf-8");
		       	   try {
					String totalfinal=URLEncoder.encode(totalstr, "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				new NetWorkX("/activities.json?user_token="+UserToken, HTTPMethod.POST, totalstr, W_buildactivityscroll.this).execute();
				}
			}
			else{
				jsonxiaotu=(JSONObject) Result;
				try {
					smallroad=jsonxiaotu.getJSONObject("picture").getString("url");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.e("smallroad", smallroad);
				picpoststr="{\"picture\":{\"picdata\":\""+base64big+"\"}}";
				Log.e("datu",picpoststr.toString());
				bigpic=new UpLoadImg(true);
				new NetWorkX("/pictures.json", HTTPMethod.POST, picpoststr.toString(),bigpic).execute();
			}
		}
		
	}
}
