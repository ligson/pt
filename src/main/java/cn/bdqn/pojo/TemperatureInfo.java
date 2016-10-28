package cn.bdqn.pojo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TemperatureInfo")
public class TemperatureInfo {
	private String city;
	private Date date;
	private int min;
	private int max;
	private int average;
	private String desc;
	
	public TemperatureInfo(){}
	public TemperatureInfo(String city,Date date, int min, int max, int average, String desc){
		this.city = city;
		this.date = date;
		this.min = min;
		this.max = max;
		this.average = average;
		this.desc = desc;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getAverage() {
		return average;
	}
	public void setAverage(int average) {
		this.average = average;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
