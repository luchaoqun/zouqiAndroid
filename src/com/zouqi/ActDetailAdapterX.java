package com.zouqi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zouqi.NetWorkX.NetWorkInterface;

public class ActDetailAdapterX extends AdapterX implements NetWorkInterface {
		private Context mContext;
		private LayoutInflater inflater_w;
		private JSONObject ActDetail;
		private JSONArray ActComment;
		
		final int TYPE_1 = 0;
		final int TYPE_2 = 1;
		final int TYPE_3 = 2;
		final int TYPE_4 = 3;
		final int TYPE_5 = 4;
		final int TYPE_6 = 5;//ActComment.length()+5;
		class ActTitle{
			TextView acTitle;
			ImageView actLogo;
			TextView beginTime;
			TextView endTime;
			TextView actPlace;
			TextView actPerson;
		};
		class ActIntro{
			ImageView actImg;
			TextView actDescs;
		};
		class ActCom{
			ImageView userImg;
			TextView userId;
			TextView userCom;
		}
		public ActDetailAdapterX(Context context){
              mContext = context;
              inflater_w = LayoutInflater.from(mContext);     
		}
		
			
		public int getCount() {

			return ActComment.length()+5;
		}
		@Override
		public int getItemViewType(int position) {
			if(position==0) 
				return TYPE_1;
			else if(position==1)
				return TYPE_2;
			else if(position==2)
				return TYPE_3;
			else if(position==3)
				return TYPE_4;
			else if(position==(ActComment.length()+5))
				return TYPE_6;
			else
				return TYPE_5;
		}

		@Override
		public int getViewTypeCount() {
         return 6;
		}
		
		public Object getItem(int arg0) {
		return null;
	   }
		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			int type=getItemViewType(position);//获取列表类型
			ActTitle actTitle=null;
		    ActIntro actIntro=null;
		    ActCom actCom=null;
	    	switch(type)
	    	{
	    	case TYPE_1:
	    		convertView=inflater_w.inflate(R.layout.l_activity_detail_title, parent, false);
	    		actTitle = new ActTitle();
	    		actTitle.acTitle=(TextView)convertView.findViewById(R.id.l_activity_detail_title);
	    		actTitle.actLogo=(ImageView)convertView.findViewById(R.id.detail_activity_logo);
	    		actTitle.beginTime=(TextView)convertView.findViewById(R.id.detail_begin_time);
	    		actTitle.endTime=(TextView)convertView.findViewById(R.id.detail_end_time);
	    		actTitle.actPlace=(TextView)convertView.findViewById(R.id.detail_place);
	    		actTitle.actPerson=(TextView)convertView.findViewById(R.id.detail_persons);
	    		convertView.setTag(actTitle);
	    		break;
	    		
	    	case TYPE_2:
	    	   convertView=inflater_w.inflate(R.layout.l_activity_detail_introduce, parent, false);
	    	   actIntro = new ActIntro();
	    	   actIntro.actImg=(ImageView)convertView.findViewById(R.id.l_activity_detail_bigimg);
	    	   actIntro.actDescs=(TextView)convertView.findViewById(R.id.l_activity_detail_text);
	    	   break;
	    	case TYPE_3:
	    		convertView=inflater_w.inflate(R.layout.l_activity_detail_pub_button, parent, false);

	    		break;
	    	case TYPE_4:
	    		convertView=inflater_w.inflate(R.layout.l_activity_detail_comment, parent,false);

	    		break;
	    	case TYPE_5:
	    		convertView=inflater_w.inflate(R.layout.l_activity_detail_comment1, parent,false);
				actCom=new ActCom();
				actCom.userImg=(ImageView)convertView.findViewById(R.id.l_activity_comment_user);
	    		actCom.userId=(TextView)convertView.findViewById(R.id.l_activity_comment_id);
	    		actCom.userCom=(TextView)convertView.findViewById(R.id.l_activity_comment_detail);
	    		break;
	    	case TYPE_6:
	    		convertView=inflater_w.inflate(R.layout.l_activity_detail_comment_more,parent,false);
			    break;
			default:
				
	    		break;
	    	}
	    	
	    	//载入数据 
	    	ActivityClass ActDetailInfo = null;
			try {
				ActDetailInfo = new ActivityClass(ActDetail.getJSONObject("activity"));
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
	    	CommentClass CommentInfo=null;
			try {
				CommentInfo = new CommentClass(ActComment.getJSONObject(position));
			} catch (JSONException e) {
				e.printStackTrace();
			}
	    	switch(type){
	    	case TYPE_1:
	    		actTitle.acTitle.setText(ActDetailInfo.GetTitle());
				actTitle.beginTime.setText(ActDetailInfo.GetBeginTime());
				actTitle.endTime.setText(ActDetailInfo.GetEndTime());
				actTitle.actPlace.setText(ActDetailInfo.GetPlace());
				actTitle.actPerson.setText(ActDetailInfo.GetOwnnerId_StringType());
				new LoadImg(actTitle.actLogo).execute(ActDetailInfo.GetLogoURL());
	    		break;
	    	case TYPE_2:
	    		actIntro.actDescs.setText(ActDetailInfo.GetContent());
				new LoadImg(actIntro.actImg).execute(ActDetailInfo.GetPictureURL());
	    		break;
	    	default:
	    		actCom.userId.setText(CommentInfo.GetUserId());
				actCom.userCom.setText(CommentInfo.GetUserCom());
				new LoadImg(actCom.userImg).execute(CommentInfo.GetUserLogo());
	    	}
	    	
			return convertView;
		}


		@Override
		public void ChangeForNewResult(Object Result) {
			ActDetail=(JSONObject)Result;
			ActComment=(JSONArray)Result;
			this.notifyDataSetChanged();
		}
}
