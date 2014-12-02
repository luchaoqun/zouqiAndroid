/**********************************************************************************
* Usage:
* 	[JSONObject/JSONArray/String] eg_varible=new NetWorkX([RequetsPath],[POSTMethod],[PostContent],[Runnable]).execute([Result_JsonType]);
*	
*	ReuqsetPath:(String Type)  Like "/user.sign" ,"/activities.json",etc...
*	PostMethod:(HttpMethod Type)  HttpMethod.GET,HttpMethod.POST,HttpMethod.UPDATE,HttpMethod.DELETE.
*	PostContent:(String Type) The POST Content write here.If you GET,give this parameter (null).
*	Runnable:(Runnable Type) When the process has done,the next Runnable Function.
*	Result_JsonType:(JsonType Type) JsonType.JObject,JsonType.JArray,JsonType.JString
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


public class NetWorkX extends AsyncTask<Object, Void, Object>{
	
	public static enum JsonType{
		JObject,JArray,JString;
	}
	public static enum HTTPMethod{
		GET,POST,DELETE,UPDATE;
	}

	static final String URLPrefix="http://10.0.16.200:3000";
	private String TheUrl=null;
	private HTTPMethod TheHttpMethod=null;
	private String ThePostData=null;
	private String JData=null;
	private Object ResultData=null;
	private Runnable NextFunction=null;
	
	public NetWorkX(String RequestPath,HTTPMethod Method,String PostData,Runnable OnSuccess){
		TheUrl=URLPrefix+RequestPath;
		TheHttpMethod=Method;
		ThePostData=PostData;
		ResultData=new Object();
		NextFunction=OnSuccess;
	}
	
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
		BufferedReader InputData=new BufferedReader(new InputStreamReader(httpconn.getInputStream()),8192);
		String TmpData=null;
		while((TmpData=InputData.readLine())!=null){
			response.append(TmpData);
		}
		InputData.close();
		httpconn.disconnect();
		JData=response.toString();
	}
	
	@Override
	protected Object doInBackground(Object... params) {
		try {
			this.ConnectX();
		} catch (IOException e) {
			Log.e("NetWorkX-ConnectX",e.toString());
			e.printStackTrace();
		} catch (JSONException e) {
			Log.e("NetWorkX-ConnectX",e.toString());
			e.printStackTrace();
		}
		JsonType JType=(JsonType) params[0];
		if(JType==JsonType.JObject){
			try {
				ResultData=new JSONObject(JData);
			} catch (JSONException e) {
				Log.e("NetWorkX-doInbackGround","Catch JSONException");
				e.printStackTrace();
			}
		}
		else if(JType==JsonType.JArray){
			try {
				ResultData=new JSONArray(JData);
			} catch (JSONException e) {
				Log.e("NetWorkX-doInbackGround","Catch JSONException");
				e.printStackTrace();
			}
		}
		else{
			ResultData=new String(JData);
		}
		Log.d("NetWorkX","Receive Json Data OK,Result is"+ResultData.toString());
		return ResultData;
	}
	
	@Override
	protected void onPostExecute(Object result){
		if(result!=null){
			Log.d("NetWorkX","Receive Finished");
			NextFunction.run();
		}
		else
		{
			Log.e("NetWorkX","Receive Blank Data!May be Error!!");
		}
	}
	
}
