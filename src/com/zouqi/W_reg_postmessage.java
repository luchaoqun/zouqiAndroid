package com.zouqi;


import android.app.Activity;
import android.webkit.WebView.FindListener;
import android.widget.EditText;

public class W_reg_postmessage extends Activity{
	public W_reg_postmessage(){}
    EditText text1=(EditText)findViewById(R.id.W_reg_editemail);
    EditText text2=(EditText)findViewById(R.id.W_reg_editmima);
    EditText text3=(EditText)findViewById(R.id.W_reg_editturepasswd);
    EditText text4=(EditText)findViewById(R.id.W_reg_editjiaowu);
    EditText text5=(EditText)findViewById(R.id.W_reg_editjiaowumima);
    public String str1=text1.getText().toString();
    public String str2=text2.getText().toString();
    public String str3=text3.getText().toString();
    public String str4=text4.getText().toString();
    public String str5=text5.getText().toString();
    
  
	//json 格式  {"user":{"password":"11111111","password_confirmation":"11111111","school_id":1,"student_id":77,"student_pwd":"1119","user_logo":"/uploads/picture/image/gravatar1.jpg","email": "q@q.q"}}
}
