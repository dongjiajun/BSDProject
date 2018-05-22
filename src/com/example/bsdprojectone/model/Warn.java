package com.example.bsdprojectone.model;

public class Warn {

	private String deviceId;
	private int warnFlag;
	private String warnText;
	public Warn()
	{}
	
	public Warn(String deviceId,int warnFlag,String warnText)
	{
		this.deviceId=deviceId;
		this.warnFlag=warnFlag;
		this.warnText=warnText;
	}
	public void setDeviceId(String deviceId)
	{
		this.deviceId=deviceId;
	}
	public String getDeviceId()
	{
		return this.deviceId;
	}
	public void setWarnFlag(int warnFlag)
	{
		this.warnFlag=warnFlag;
	}
	public int getWarnFlag()
	{
		return this.warnFlag;
	}
	public void setWarnText(String warnText)
	{
		this.warnText=warnText;
	}
	public String getWarnText()
	{
		return this.warnText;
	}

}
