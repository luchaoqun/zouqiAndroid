/**********************************************************************************
* Usage:
* 	NneWorkX eg_varible=new NetWorkX([RequetsPath],[POSTMethod],[PostContent],[Runnable]);
*	eg_varible.execute();
*
*	
*	ReuqsetPath:(String Type)  Like "/user.sign" ,"/activities.json",etc...
*	PostMethod:(HttpMethod Type)  HttpMethod.GET,HttpMethod.POST,HttpMethod.UPDATE,HttpMethod.DELETE.
*	PostContent:(String Type) The POST Content write here.If you GET,give this parameter (null).
*	Runnable:(Runnable Type) When the process has done,the next Runnable Function.
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

public class NetWorkX extends AsyncTask<Object, Void, String>{
	
	public static enum JsonType{
		JObject,JArray,JString;
	}
	public static enum HTTPMethod{
		GET,POST,DELETE,UPDATE;
	}

	static final String URLPrefix="http://rdd7cn.xicp.net:3000";
	private String TheUrl=null;
	private HTTPMethod TheHttpMethod=null;
	private String ThePostData=null;
	private String JData=null;
	private NetWorkInterface TheComponents;
	
	public interface NetWorkInterface{
		void ChangeForNewResult(String Result);
	}
	
	public NetWorkX(String RequestPath,HTTPMethod Method,String PostData,NetWorkInterface Components){
		SetPath(RequestPath);
		SetHttpMethod(Method);
		SetPostData(PostData);
		TheComponents=Components;
	}
	
	public void ConnectX() throws IOException, JSONException{
		StringBuilder response =new StringBuilder();
		Log.d("NetWork Debug Message","Url is "+TheUrl);
		URL url=new URL(TheUrl);
		HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();
		//httpconn.setConnectTimeout(1000);
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
		if(TheHttpMethod==HTTPMethod.POST||TheHttpMethod==HTTPMethod.UPDATE)
		{
			httpconn.setDoOutput(true);
			httpconn.setUseCaches(false);
		}
		httpconn.setDoInput(true);
		httpconn.setInstanceFollowRedirects(true);
		httpconn.connect();
		if(TheHttpMethod==HTTPMethod.POST||TheHttpMethod==HTTPMethod.UPDATE){
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
	protected String doInBackground(Object... params) {
		try {
			this.ConnectX();
		} catch (IOException e) {
			Log.e("NetWorkX-ConnectX",e.toString());
			e.printStackTrace();
		} catch (JSONException e) {
			Log.w("NetWorkX-ConnectX",e.toString());
			e.printStackTrace();
		}
		Log.d("NetWorkX","Receive Json Data OK,Result is"+JData.toString());
		return JData;
	}
	
	@Override
	protected void onPostExecute(String result){
		if(result!=null){
			Log.d("NetWorkX","Receive Finished");
			TheComponents.ChangeForNewResult(result);
		}
		else
		{
			Log.e("NetWorkX","Receive Blank Data!May be Error!!");
		}
	}
	
	
	/**
	 * 
	 * SetFunction
	 */
	public void SetPath(String NewPath){
		TheUrl=URLPrefix+NewPath;
	}
	
	public void SetHttpMethod(HTTPMethod NewMethod){
		TheHttpMethod=NewMethod;
	}
	
	public void SetPostData(String NewData){
		ThePostData=NewData;
	}
	
	
	
	/**
	 * 
	 * GetFunction
	 * 
	 */
	public JSONObject getJSONObject() throws JSONException{
		return new JSONObject(JData);
	}
	
	public JSONArray getJSONArray() throws JSONException{
		return new JSONArray(JData);
	}
	
	public String getString(){
		return new String(JData);
	}
	
}
