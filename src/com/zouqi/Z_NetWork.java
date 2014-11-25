package com.zouqi;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;



public class Z_NetWork {
	
	public String ConnectX(String NewUrl,String HttpMethod,String PostData) throws MalformedURLException,IOException{
		StringBuilder response =new StringBuilder();
		Log.d("NetWork Debug Message","Url is "+NewUrl);
		URL url=new URL(NewUrl);
		
		HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();
		httpconn.setRequestMethod(HttpMethod);
		httpconn.setRequestProperty("Content-Type","application/json");
		if(HttpMethod=="POST")
		{
			httpconn.setDoOutput(true);
			httpconn.setUseCaches(false);
		}
		httpconn.setDoInput(true);
		httpconn.setInstanceFollowRedirects(true);//是否遵循重定向
		httpconn.connect();
		
		if(HttpMethod=="POST"){
			DataOutputStream PostData_Stream=new DataOutputStream(httpconn.getOutputStream());
			PostData_Stream.writeBytes(PostData);
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
		return response.toString();
	}
}
