package com.zouqi;

import org.json.JSONException;
import org.json.JSONObject;

public class OrgClass {
	private Integer Id;
	private String Name;
	private String LogoURL;
	private String Content;
	private String SchoolName;
	private Integer SchoolId;
	private Integer Shipid;
	private Integer PeopleNumber;
	
	OrgClass(JSONObject OrgJData) throws JSONException{
		LogoURL=OrgJData.getString("organization_logo");
		Id=OrgJData.getInt("id");
		Name=OrgJData.getString("organization_name");
		Content=OrgJData.getString("organization_content");
		SchoolName=OrgJData.getString("school_name");
		SchoolId=OrgJData.getInt("school_id");
		Shipid=OrgJData.getInt("ship_id");
		PeopleNumber=OrgJData.getInt("organization_people_number");
	}
	
	public void SetShipId(Integer NewShipId){
		Shipid=NewShipId;
	}
	
	public String GetLogoURL(){
		return this.LogoURL;
	}
	
	public Integer GetId_IntType(){
		return this.Id;
	}
	
	public String GetId_StringType(){
		return this.Id.toString();
	}
	
	public String GetName(){
		return this.Name;
	}
	
	public String GetContent(){
		return this.Content;
	}
	
	public String GetSchoolName(){
		return this.SchoolName;
	}
	
	public Integer GetSchoolId_IntType(){
		return this.SchoolId;
	}
	
	public String GetSchoolId_StringType(){
		return this.SchoolId.toString();
	}
	
	public Integer GetShipId_IntType(){
		return this.Shipid;
	}
	
	public String GetShipId_StringType(){
		return this.Shipid.toString();
	}
	
	public Integer GetPeopleNumber_IntType(){
		return this.PeopleNumber;
	}
	
	public String GetPeopleNumber_StringType(){
		return this.PeopleNumber.toString();
	}
}
