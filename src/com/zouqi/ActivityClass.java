package com.zouqi;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityClass {
	private String Title;
	private Integer Id;
	private String BeginTime,EndTime;
	private String Logo,Picture;
	private String Place;
	private double Lat,Lon;
	private Integer PeopleMax,PeopleNumber;
	private String Content;
	private boolean finished;
	private String OrganizationName;
	private Integer OrganizationId;
	private Integer OwnerId;
	private Integer ShipId;
	private boolean GetShipIdSuccess;
	
	
	ActivityClass(JSONObject NewActivity) throws JSONException{
		this.Title=NewActivity.getString("activity_title");
		this.Id=NewActivity.getInt("id");
		this.BeginTime=NewActivity.getString("activity_begin_time");
		this.EndTime=NewActivity.getString("activity_end_time");
		this.Logo=NewActivity.getString("activity_logo");
		this.Picture=NewActivity.getString("activity_pic");
		this.Place=NewActivity.getString("activity_place");
		this.Lat=NewActivity.getDouble("activity_place_lat");
		this.Lon=NewActivity.getDouble("activity_place_lon");
		this.PeopleMax=NewActivity.getInt("activity_people_max");
		this.PeopleNumber=NewActivity.getInt("activity_people_number");
		this.finished=NewActivity.getBoolean("finished");
		this.Content=NewActivity.getString("activity_content");
		this.OrganizationName=NewActivity.getString("organization_name");
		this.OrganizationId=NewActivity.getInt("organization_id");
		this.OwnerId=NewActivity.getInt("owner_id");
		try{
			this.ShipId=NewActivity.getInt("ship_id");
			this.GetShipIdSuccess=true;
		}
		catch (JSONException e){
			this.ShipId=0;
			this.GetShipIdSuccess=false;
		}
	}
	
	
	public String GetTitle(){
		return this.Title;
	}
	
	public String GetId_StringType(){
		return this.Id.toString();
	}
	
	public Integer GetId_IntType(){
		return this.Id;
	}
	
	public String GetBeginTime(){
		return this.BeginTime;
	}
	
	public String GetEndTime(){
		return this.EndTime;
	}
	
	public String GetLogoURL(){
		return this.Logo;
	}
	
	public String GetPictureURL(){
		return this.Picture;
	}
	
	public String GetPlace(){
		return this.Place;
	}
	
	public double GetLat(){
		return this.Lat;
	}
	
	public double GetLon(){
		return this.Lon;
	}
	
	public String GetPeopleMax_StringType(){
		return this.PeopleMax.toString();
	}

	public Integer GetPeopleMax_Inttype(){
		return this.PeopleMax;
	}
	
	public String GetPeopleNumber_StringType(){
		return this.PeopleNumber.toString();
	}
	
	public Integer GetPeopleNumber_IntType(){
		return this.PeopleNumber;
	}
	
	public String GetContent(){
		return this.Content;
	}
	
	public boolean Finished(){
		return this.finished;
	}
	
	public String GetOrganizationName(){
		return this.OrganizationName;
	}
	
	public String GetOrganizationName_IntType(){
		return this.OrganizationId.toString();
	}
	
	public Integer GetOrganizationName_StringType(){
		return this.OrganizationId;
	}
	
	public Integer GetOwnnerId_IntType(){
		return this.OwnerId;
	}
	
	public String GetOwnnerId_StringType(){
		return this.OwnerId.toString();
	}
	
	public String GetShipId_StringType(){
		return this.ShipId.toString();
	}
	
	public Integer GetShipId_IntType(){
		return this.ShipId;
	}
	public boolean Joined(){
		return this.GetShipIdSuccess;
	}
}
