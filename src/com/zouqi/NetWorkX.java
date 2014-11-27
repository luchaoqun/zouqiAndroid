/**********************************************************************************
* Usage:
* 	new NetWorkX([RequetsPath],[POSTMethod],[PostContent],[Result],[TheAdapter]).execute([Result_JsonType]);
* 	
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


import android.os.AsyncTask;
import android.util.Log;
import android.widget.BaseAdapter;



public class NetWorkX extends AsyncTask<Object, Void, Object>{
	
	public static enum JsonType{
		JObject,Jarray,JString;
	}
	public static enum HTTPMethod{
		GET,POST,DELETE,UPDATE;
	}

	static final String URLPrefix="http://rdd7cn.xicp.net:3000";
	private String TheUrl=null;
	private HTTPMethod TheHttpMethod=null;
	private String ThePostData=null;
	private String JData=null;
	private BaseAdapter TheAdapter=null;
	//private JSONObject JObj=null;
	//private JSONArray JArr=null;
	//private InputStream RcvData=null;
	private Object ResultData=null;
	
	
	public NetWorkX(String RequestPath,HTTPMethod Method,String PostData,Object JResult,BaseAdapter AimAdapter){
		TheUrl=URLPrefix+RequestPath;
		TheHttpMethod=Method;
		ThePostData=PostData;
		ResultData=JResult;
		TheAdapter=AimAdapter;
	}
	/*
	public void PrepareGet(String RequestPath){
		TheUrl=URLPrefix+RequestPath;
		TheHttpMethod="GET";
	}*/
	
	public void ConnectX() throws IOException, JSONException{
	
		StringBuilder response =new StringBuilder();
		Log.d("NetWork Debug Message","Url is "+TheUrl);
		URL url=new URL(TheUrl);
		HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();
		String TmpMethod=null;
		switch (TheHttpMethod){
			case GET:
				TmpMethod="GET";
				break;
			case POST:
				TmpMethod="POST";
				break;
			case DELETE:
				TmpMethod="DELETE";
				break;
			case UPDATE:
				TmpMethod="UPDATE";
				break;
		}
		httpconn.setRequestMethod(TmpMethod);
		httpconn.setRequestProperty("Accept", "application/json");
		httpconn.setRequestProperty("Content-Type","application/json");
		if(TheHttpMethod==HTTPMethod.POST||TheHttpMethod==HTTPMethod.DELETE||TheHttpMethod==HTTPMethod.UPDATE)
		{
			httpconn.setDoOutput(true);
			httpconn.setUseCaches(false);
		}
		httpconn.setDoInput(true);
		httpconn.setInstanceFollowRedirects(true);
		httpconn.connect();
		if(TheHttpMethod==HTTPMethod.POST||TheHttpMethod==HTTPMethod.DELETE||TheHttpMethod==HTTPMethod.UPDATE){
			DataOutputStream PostData_Stream=new DataOutputStream(httpconn.getOutputStream());
			PostData_Stream.writeBytes(ThePostData);
			PostData_Stream.flush();
			PostData_Stream.close();
		}
		//RcvData=httpconn.getInputStream();
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
	
/*
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
	
	
*/
	@Override
	protected Object doInBackground(Object... params) {
		try {
			this.ConnectX();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonType JType=(JsonType) params[0];
		if(JType==JsonType.JObject){
			try {
				ResultData=new JSONObject(JData);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(JType==JsonType.Jarray){//Json Array
			try {
				ResultData=new JSONArray(JData);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			ResultData=null;
		}
		return ResultData;
	}
	
	@Override
	protected void onPostExecute(Object result){
		if(result!=null){
			Log.d("NetWorkX","Receive Json Data OK,Result is"+result.toString());
			TheAdapter.notifyDataSetChanged();
			}
		else
		{
			Log.e("NetWorkX","Receive Blank Data!May be Error!!");
		}
	}
	
}
