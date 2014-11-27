/************************************************************
 * Usage:
 * 		new LoadImg([ImageView Imgv]).execute([String URL]);
 * 	
 * 
 * Created by Rdd7 on 14-11-26.
 * Copyright (c) 2014 Rdd7. All rights reserved.
 **********************************************************/

package com.zouqi;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;


public class LoadImg extends AsyncTask<String, Void, Bitmap>{
	private ImageView TheImgv;
	
	public LoadImg(ImageView Imgv){
		this.TheImgv=Imgv;
	}

	@Override
	protected void onPostExecute(Bitmap result){
		Log.d("LoadImg","Receive Img OK,will appear soon...");
		TheImgv.setImageBitmap(result);
	}
	
	@Override
	protected Bitmap doInBackground(String... params) {
		String ImgURL=params[0];
		Log.d("LoadImg","Start LoadImg,Request URL is "+ImgURL);
		Bitmap tmpmap=null;
		InputStream RcvData = null;
		try {
			RcvData = new java.net.URL(ImgURL).openStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tmpmap=BitmapFactory.decodeStream(RcvData);
		return tmpmap;
	}
	
}
