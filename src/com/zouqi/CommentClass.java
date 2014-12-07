package com.zouqi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zouqi.ActivityClass;

public class CommentClass{

	private String UserLogo;
	private String UserId;
	private String UserCom;
	private int ComLen;
	
	CommentClass(JSONObject NewComment) throws JSONException {
		this.UserLogo=NewComment.getString("user_logo");
		this.UserId=NewComment.getString("email");
		this.UserCom=NewComment.getString("comment_content");
		this.ComLen=NewComment.length();
	}
	
	public String GetUserLogo(){
		return this.UserLogo;
	}
	
	public String GetUserId(){
		return this.UserId;
	}
	
	public String GetUserCom(){
		return this.UserCom;
	}

}
