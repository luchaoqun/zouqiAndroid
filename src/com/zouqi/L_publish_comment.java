package com.zouqi;

import com.zouqi.NetWorkX.HTTPMethod;
import com.zouqi.NetWorkX.NetWorkInterface;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Path;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class L_publish_comment extends Activity implements NetWorkInterface{
	String CommentContent,PathPreFix,Token;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_l_publish_comment);
		CommentContent="{\"comment\":{\"comment_content\":\"test\",\"activity_id\":1}}";//AID /COONTENT
		PathPreFix="/comments.json?user_token=";
		SharedPreferences pfe=getSharedPreferences("mytoken",MODE_PRIVATE);
		Token=pfe.getString("token", "sdNr-dpcpsqSczLKMz1r");
		
	}

	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.l_publish_comment_menu, menu);
	        return true;
	    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.l_publish_item) {
			new NetWorkX(PathPreFix+Token,HTTPMethod.POST, CommentContent, this).execute();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void ChangeForNewResult(String Result) {
		//chuqu
	}
}
