package com.zouqi;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class W_buildactivity extends Activity {
	
	   final int  RESULT_LOAD_IMAGE = 1;
	   ArrayList listString;
	   myadapter listAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_w_buildactivity);
		ListView lv=(ListView)findViewById(R.id.W_buildactivitylistview);
		listString = new ArrayList();
	/*	listString.add(Integer.toString(1));
		listString.add(Integer.toString(2));
		listString.add(Integer.toString(3));
		listString.add(Integer.toString(4));*/
		for(int i=0;i<8;i++){
			listString.add(Integer.toString(i));
		}
		listAdapter = new myadapter(this);
		   lv.setAdapter(listAdapter);
		   listAdapter.notifyDataSetChanged();
		   lv.setAdapter(listAdapter);
	}
	class myadapter extends BaseAdapter{
		Context mContext;
		LayoutInflater inflater_w;
	    TextView tex;
		final int VIEW_TYPE = 2;
		final int TYPE_1 = 0;
		final int TYPE_2 = 1;
		final int TYPE_3 = 2;
		final int TYPE_4 = 3;
		final int TYPE_5 = 4;
		final int TYPE_6 = 5;
		final int TYPE_7 = 6;
		final int TYPE_8 = 7;
		private ArrayList<Integer> TypeList = new ArrayList<Integer>();
		public myadapter(Context context){
			                  mContext = context;
                              inflater_w = LayoutInflater.from(mContext);
		}
		
			
			public int getCount() {
				// TODO Auto-generated method stub
				return listString.size();
			}
			@Override
			public int getItemViewType(int position) {
			// TODO Auto-generated method stub
				//int p = position%2;
				return position;
			}

			@Override

			public int getViewTypeCount() {
			// TODO Auto-generated method stub
             return listString.size();
			}
			public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return listString.get(arg0);
		   }
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				
			/*	viewHolder1 holder1 = null;
				viewHolder1 holder2 = null;
				viewHolder2 holder2=null;
			    viewHolder3 holder3 = null;
			    viewHolder4 holder4 = null;
			    viewHolder5 holder5=null;*/
				//viewHolder8 holder8=null;
			    int type=getItemViewType(position);
			    Log.e("position", Integer.toString(position));
			    	switch(type)
			    	{
			    	case TYPE_1:
			    		convertView=inflater_w.inflate(R.layout.activity_w_buildactivity_padding, parent, false);
			    		/*holder1=new viewHolder1();
			    		holder1.text=(TextView)convertView.findViewById(R.id.W_bulidactiviname);
			    	    holder1.edittext=(EditText)convertView.findViewById(R.id.W_editactivityname);
			    		convertView.setTag(holder1);
			    		Log.e("holder1", "NULL "); */
			    		break;
			    		
			    	case TYPE_2:
			    	   convertView=inflater_w.inflate(R.layout.activity_w_buildactivity_padding2, parent, false);
			    	
			    	 /*  convertView.setTag(holder2);
			    	   Log.e("holder2", "NULL "); */
			    	   break;
			    	case TYPE_3:
			    		convertView=inflater_w.inflate(R.layout.activity_w_buildactivity_padding3, parent, false);
			    		/*holder3=new viewHolder3();
			    		convertView.setTag(holder3);*/
			    		break;
			    	case TYPE_4:
			    		convertView=inflater_w.inflate(R.layout.activity_w_buildactivity_padding4, parent,false);
			    		/*holder4=new viewHolder4();
			    		holder4.text=(TextView)convertView.findViewById(R.id.W_buildactivityendtime);
			    		holder4.edittext=(EditText)convertView.findViewById(R.id.W_editbuildactivityendtime);
			    		convertView.setTag(holder4);*/
			    		break;
			    	case TYPE_5:
			    		convertView=inflater_w.inflate(R.layout.activity_w_buildactivity_padding5, parent,false);
			    		break;
			    	case TYPE_6:
			    		convertView=inflater_w.inflate(R.layout.activity_w_buildactivity_padding7, parent,false);
			    		break;
			    	case TYPE_7:
			    		convertView=inflater_w.inflate(R.layout.activity_w_buildactivity_padding6, parent,false);
			    		break;
			    	case TYPE_8:
			    		convertView=inflater_w.inflate(R.layout.activity_w_buildactivity_padding8,parent,false);
			    		/*holder8=new viewHolder8();
			    		holder8.btn=(Button)findViewById(R.id.W_buildactivity_btnxiaotu);
			    	     itemlistener listen=new itemlistener(position);
					   	holder8.btn.setOnClickListener(listen);*/
					    	

		    	}
			    	/*Button btn=(Button)findViewById(R.id.W_buildactivity_textxiaotu);
			    	btn.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent pic=new Intent(Intent.ACTION_GET_CONTENT);
							pic.setType("image/*");
						}
					});*/
			
			    
			    /*switch (type){
			    case TYPE_1:
			    	holder1.text.setText("活动名称");
                    holder1.edittext.append("");
			    	break;
			    case TYPE_2:
			    	holder2.text.setText("活动地点");
			        holder2.edittext.append("");
			    	break;
			    case TYPE_3:
			    	holder3.text.setText("开始时间");
			    	
			    }*/
				return convertView;
			}
      }
	class itemlistener implements OnClickListener{
		
		private int w_position;
		public itemlistener(int pos){
			w_position=pos;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
             intent.setClass(W_buildactivity.this,W_personal.class);
             startActivity(intent);
		}
	}
	 public static final String IMAGE_UNSPECIFIED = "image/*";
	 public final static int PHOTO_ZOOM = 0;
	 public final static int PHOTO_RESULT = 2;
	public void btnclick(View v){
	    
		  Intent intent = new Intent(
                  Intent.ACTION_PICK,
                  android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
  startActivityForResult(intent, 1);
	    }
	   private byte[] mContent;
	   private Bitmap mybitmap;
	   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		   ContentResolver resolver =getContentResolver();
		   if(data!=null){
			   if(requestCode==1){
				   try{
				   Uri uri=data.getData();
				   mContent = readStream(resolver.openInputStream(Uri
                           .parse(uri.toString())));
				   mybitmap = getPicFromBytes(mContent, null);
				   ImageView image=(ImageView) findViewById(R.id.W_buildactivity_imgxiaotu);
                  image.setImageBitmap(mybitmap);
				   }
				   catch (Exception e) {
                       System.out.println(e.getMessage());
                     }
			   }
		   }
	   }
	   public static Bitmap getPicFromBytes(byte[] bytes,
               BitmapFactory.Options opts) {
       if (bytes != null)
               if (opts != null)
                       return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
                                       opts);
               else
                       return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
       return null;
}

public static byte[] readStream(InputStream inStream) throws Exception {
       byte[] buffer = new byte[1024];
       int len = -1;
       ByteArrayOutputStream outStream = new ByteArrayOutputStream();
       while ((len = inStream.read(buffer)) != -1) {
               outStream.write(buffer, 0, len);
       }
       byte[] data = outStream.toByteArray();
       outStream.close();
       inStream.close();
       return data;

}

/*	class viewHolder1{
		TextView text;
		EditText edittext;
	}
	class viewHolder2{
		TextView text;
		EditText edittext;
	}
	class viewHolder3{
		TextView text;
		EditText  edittext;
	}
    class viewHolder4{
       TextView text;
       EditText edittext;
    }
    class viewHolder5{
    	
    }*/
	/*class viewHolder8{
		Button btn;
	}*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.w_buildactivity, menu);
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
