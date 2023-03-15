package com.example.datingapp.post_models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PostAuth implements Serializable {

	@SerializedName("image")
	private String image;

	@SerializedName("device_id")
	private String deviceId;

	@SerializedName("phone")
	private String phone;

	@SerializedName("timezone")
	private String timezone;

	@SerializedName("name")
	private String name;

	@SerializedName("device_type")
	private String deviceType;

	public PostAuth() {
	}

	public PostAuth(String image, String deviceId, String phone, String timezone, String name, String deviceType) {
		this.image = image;
		this.deviceId = deviceId;
		this.phone = phone;
		this.timezone = timezone;
		this.name = name;
		this.deviceType = deviceType;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setDeviceId(String deviceId){
		this.deviceId = deviceId;
	}

	public String getDeviceId(){
		return deviceId;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setTimezone(String timezone){
		this.timezone = timezone;
	}

	public String getTimezone(){
		return timezone;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDeviceType(String deviceType){
		this.deviceType = deviceType;
	}

	public String getDeviceType(){
		return deviceType;
	}

	@Override
 	public String toString(){
		return 
			"PostAuth{" + 
			"image = '" + image + '\'' + 
			",device_id = '" + deviceId + '\'' + 
			",phone = '" + phone + '\'' + 
			",timezone = '" + timezone + '\'' + 
			",name = '" + name + '\'' + 
			",device_type = '" + deviceType + '\'' + 
			"}";
		}
}