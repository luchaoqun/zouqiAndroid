package com.zouqi;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;


public class MainActivity extends TabActivity {

	private TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setContentView(R.layout.activity_main);
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar); 

        
       tabHost=this.getTabHost();
        TabHost.TabSpec spec;  
        Intent intent;  
  
        intent=new Intent().setClass(this, L_tab_hot.class);  
        spec=tabHost.newTabSpec("热门活动").setIndicator("热门活动").setContent(intent);  
        tabHost.addTab(spec);  
          
        intent=new Intent().setClass(this,L_tab_home.class);  
        spec=tabHost.newTabSpec("我的活动").setIndicator("我的活动").setContent(intent);  
        tabHost.addTab(spec);  
          
        intent=new Intent().setClass(this, Z_Organization.class);  
        spec=tabHost.newTabSpec("我的群组").setIndicator("我的群组").setContent(intent);  
        tabHost.addTab(spec);  
          
       
        intent=new Intent().setClass(this, W_personal.class);  
        spec=tabHost.newTabSpec("个人信息").setIndicator("个人信息").setContent(intent); 
        tabHost.addTab(spec);  
        
        tabHost.setCurrentTab(0);  
          
        RadioGroup radioGroup=(RadioGroup) this.findViewById(R.id.main_tab_radio);  
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {  
              
            @Override  
            public void onCheckedChanged(RadioGroup group, int checkedId) {  
                // TODO Auto-generated method stub  
                switch (checkedId) {  
                case R.id.main_tab_hot:
                    tabHost.setCurrentTabByTag("热门活动");  
                    break;  
                case R.id.main_tab_home:
                    tabHost.setCurrentTabByTag("我的活动");  
                    break;  
                case R.id.main_tab_group:
                    tabHost.setCurrentTabByTag("我的群组");  
                    break;  
                case R.id.main_tab_person:
                    tabHost.setCurrentTabByTag("个人信息");  
                    break;  
                default:  
                    break;  
                }  
            }
         });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
}
