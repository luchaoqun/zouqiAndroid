/**********************************************************************************
* 使用方法：实例化类后，GET调用PrepareGet方法，POST/CURD使用PrepagePost方法初始化，最后执行start即可
* example:
*	Post:
*		Z_network a=new Z_network();
*		a.PreparePost("/users/sign_in", "POST", "{\"user\":{\"email\": \"q@q.q\",\"password\": \"11111111\"}}");
*		a.start();
*		
*	Get:
*		Z_network a=new Z_network();
*		a.PrepareGet("/activities");
*		a.start();
*
*
*获取JSONObject:JSONObject obj=a.GetJsonObject();
*获取JSONArray:JSONArray obj=a.GetJsonArray();
************************************************************************************/


package com.zouqi;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.util.Log;

public class Z_NetWork extends Thread{
static final String URLPrefix="http://192.168.1.11:3000";
	private String TheUrl=null;
	private String TheHttpMethod=null;
	private String ThePostData=null;
	private String JData=null;
	public JSONObject JObj=null;
	public JSONArray JArr=null;
	

	public void PreparePost(String RequestPath,String HttpMethod,String PostData){
		TheUrl=URLPrefix+RequestPath;
		TheHttpMethod=HttpMethod;
		ThePostData=PostData;
		
	}
	
	public void PrepareGet(String RequestPath){
		TheUrl=URLPrefix+RequestPath;
		TheHttpMethod="GET";
	}
	
	public void ConnectX() throws IOException, JSONException{
	
		StringBuilder response =new StringBuilder();
		Log.d("NetWork Debug Message","Url is "+TheUrl);
		URL url=new URL(TheUrl);
		HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();
		httpconn.setRequestMethod(TheHttpMethod);
		httpconn.setRequestProperty("Accept", "application/json");
		httpconn.setRequestProperty("Content-Type","application/json");
		if(TheHttpMethod.equals("POST"))
		{
			httpconn.setDoOutput(true);
			httpconn.setUseCaches(false);
		}
		httpconn.setDoInput(true);
		httpconn.setInstanceFollowRedirects(true);
		httpconn.connect();
		if(TheHttpMethod.equals("POST")){
			DataOutputStream PostData_Stream=new DataOutputStream(httpconn.getOutputStream());
			PostData_Stream.writeBytes(ThePostData);
			PostData_Stream.flush();
			PostData_Stream.close();
		}
		
		BufferedReader InputData=new BufferedReader(new InputStreamReader(httpconn.getInputStream()),8192);
		String TmpData=null;
		while((TmpData=InputData.readLine())!=null){
			response.append(TmpData);
		}
		InputData.close();
		httpconn.disconnect();
		JData=response.toString();
		Log.d("NetReceived",JData);
	}
	
	public void run() {
		try {
			this.ConnectX();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JSONObject GetJsonObject(){
		try {
			JObj = new JSONObject(JData);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JObj;
	}
	
	public JSONArray GetJsonArray(){
		try {
			JArr=new JSONArray(JData);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JArr;
	}
	
}
