package com.zouqi;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;


import android.util.Log;

public class Z_NetWork extends Thread{

/*使用方法：实例化类后，GET调用PrepareGet方法，POST/CURD使用PrepagePost方法初始化，最后执行start即可
eg:
	Post:
		Z_network a=new Z_network();
		a.PreparePost("http://192.168.1.11:3000/users/sign_in", "POST", "{\"user\":{\"email\": \"q@q.q\",\"password\": \"11111111\"}}");
		a.start();
		
	Get:
		Z_network a=new Z_network();
		a.PrepareGet("http://www.baidu.com");
		a.start();
*/
	String TheUrl,TheHttpMethod,ThePostData;
	public void PreparePost(String NewUrl,String HttpMethod,String PostData){
		TheUrl=NewUrl;
		TheHttpMethod=HttpMethod;
		ThePostData=PostData;
		
	}
	
	public void PrepareGet(String NewUrl){
		TheUrl=NewUrl;
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
		Log.d("NetReceived",response.toString());
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


	
}
